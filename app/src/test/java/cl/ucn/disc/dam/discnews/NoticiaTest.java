package cl.ucn.disc.dam.discnews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NoticiaTest {

    /**
     * Des-Serializador Gson
     */
    private static final Gson gson= new GsonBuilder()
            .serializeNulls() // todos los atributos aun que sean nulos
            .setPrettyPrinting() //imprime bonito, TODO: eliminar en modo produccion
            .create();

    @Test
    public void testConstructor(){
        final Noticia noticia = Noticia.builder()
                .titulo("Titulo de noticia")
                .autor("PriscilaGonzalez")
                .resumen("resumiendo la noticia")
                .build();

        //Serializar a json
        final String json =gson.toJson(noticia);
        System.out.print(json);

        Assertions.assertThat(json).isNotBlank();
        Assertions.assertThat(json).isNotNull();

        //Des-serializar
        final Noticia noti = gson.fromJson(json, Noticia.class);

        Assertions.assertThat(noti).isNotNull();
        Assertions.assertThat(noti).isEqualToComparingFieldByField(noticia);
     //   Assertions.assertThat(noti).isEqualTo(noticia); no son iguales por ser 2 instancias distintas


        //    Assertions.assertThat(noticia).isNotNull();
        //     Assertions.assertThat(noticia.getTitulo()).isNull();


    }
}