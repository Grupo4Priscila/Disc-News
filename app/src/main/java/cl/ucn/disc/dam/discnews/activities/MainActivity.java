package cl.ucn.disc.dam.discnews.activities;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

import cl.ucn.disc.dam.discnews.adapters.ArticleDBFlowAdapter;
import cl.ucn.disc.dam.discnews.tasks.GetSaveArticlesTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MainActivity extends ListActivity implements GetSaveArticlesTask.TaskListener, ShakeDetector.Listener {

    private BaseAdapter articleAdapter;

    /**
     * Running background task
     */
    private GetSaveArticlesTask getSaveArticlesTask;

    /**
     * Shake Detector
     */
    private ShakeDetector shakeDetector;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mostrar la barrita
        final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            // actionBar.setLogo(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xff00ff00, 0};
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(5);

        // Adaptador de articles
        this.articleAdapter = new ArticleDBFlowAdapter(this);
        super.setListAdapter(this.articleAdapter);

        // Si no hay articulos en el adaptador (y por lo tanto en la base de datos) ..
        if (this.articleAdapter.isEmpty()) {
            // .. ejecuto la tarea para obtenerlas.
            this.runGetAndSaveArticlesTask();
        }

        // Detector de terremotos
        this.shakeDetector = new ShakeDetector(this);

    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Al iniciar la aplicación, inicio la deteccion de terremotos
        final SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector.start(sensorManager);

        // Show the database size!
        Toast.makeText(this, "Articles in BD: " + this.articleAdapter.getCount(), Toast.LENGTH_SHORT).show();

    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
     * depending on later user activity.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestart
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onDestroy
     */
    @Override
    protected void onStop() {
        super.onStop();

        // Detengo el detector de sismos
        shakeDetector.stop();
    }

    /**
     * Metodo que realiza la ejecucion en segundo plano de la tarea que obtiene los
     *
     */
    private void runGetAndSaveArticlesTask() {

        // Si ya hay una tarea de obtencion de articulos corriendo no ejecuto una nueva!
        if (this.getSaveArticlesTask != null) {
            Toast.makeText(this, "Already downloading Articles ..", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show little message
        Toast.makeText(this, "Downloading Articles ..", Toast.LENGTH_LONG).show();

        // Inicio la tarea
   //     log.debug("Starting GetSaveArticlesTask ..");
        this.getSaveArticlesTask = new GetSaveArticlesTask(this);
        this.getSaveArticlesTask.execute();

    }

    /**
     *
     * @param newsArticles numero de articulos nuevos obtenidos.
     */
    @Override
    public void taskFinished(Integer newsArticles) {

        // Show little message
        Toast.makeText(this, "New Articles: " + newsArticles, Toast.LENGTH_LONG).show();

        //log.debug("Finished!");
        this.articleAdapter.notifyDataSetChanged();

        // Clean the task!
        this.getSaveArticlesTask = null;

    }

    /**
     * Called on the main thread when the device is shaken.
     */
    @Override
    public void hearShake() {

        // Vibro para indicar que se detecto el terremoto
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(150);
        }

        this.runGetAndSaveArticlesTask();
    }

}
