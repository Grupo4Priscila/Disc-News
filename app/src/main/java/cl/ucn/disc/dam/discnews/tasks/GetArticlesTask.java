package cl.ucn.disc.dam.discnews.tasks;

import android.os.AsyncTask;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dam.discnews.adapters.ArticleAdapter;
import cl.ucn.disc.dam.discnews.controller.ArticleController;
import cl.ucn.disc.dam.discnews.model.Article;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by PriscilaGonzalez on 09-11-2017.
 */
@Slf4j
public final class GetArticlesTask extends AsyncTask<Void,Void,List<Article>> {

    /**
     * Adaptador del articulo.
     */
    private ArticleAdapter articleAdapter;


    /**
     * @param articleAdapter adaptador donde iran a parar los articulos.
     */
    public GetArticlesTask(@NonNull  final ArticleAdapter articleAdapter) {
        this.articleAdapter = articleAdapter;
    }




    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param articles The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(List<Article> articles) {

        // FIXME: Agregado para tener mas datos de los que se despliegan en la ventana
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param articleAdapters The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     *
     * Este metodo se ejecuta en segundo plano, no esta conectado con el hilo principal de ejecucion
     */
    @Override
    protected List<Article> doInBackground(Void... voids) {

        //cronometro
        final StopWatch stopWatch=StopWatch.createStarted();
        log.debug("Running in backgraund ..");

        //FIXME: sera atributo de la clase?
        final ArticleController articleController = new ArticleController();

        try {
            // Obtengo los articles desde internet via el controlador
            return articleController.getArticles();
        } catch (IOException e) {
            return null;
        } finally {
            // Cuanto tiempo demoro?
            log.debug("Background time: {}", stopWatch);
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param articles The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(List<Article> articles) {

        // FIXME: Agregado para tener mas datos de los que se despliegan en la ventana
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
        this.articleAdapter.addAll(articles);
    }






}
