package cl.ucn.disc.dam.discnews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;

import cl.ucn.disc.dam.discnews.model.NewsApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PriscilaGonzalez on 26-10-2017.
 */




public class TestNewsWeb {

    private static final Gson gson= new GsonBuilder()
            .serializeNulls() // todos los atributos aun que sean nulos
            .setPrettyPrinting() //imprime bonito, TODO: eliminar en modo produccion
            .create();

@Test
public void TestConeccion() throws IOException {
    final String url ="https://newsapi.org/v1/articles?source=mtv-news&sortBy=top&apiKey=2ab0d82206134319b9ea35ac43db8e9f";
    final OkHttpClient client = new OkHttpClient();
    Assertions.assertThat(client).isNotNull();

    final Request request = new Request.Builder()
            .url(url)
            .build();
    Assertions.assertThat(request).isNotNull();

    Response response = client.newCall(request).execute();
    final String json= response.body().string();

    //Des-serializar
    final NewsApi newsApi = gson.fromJson(json, NewsApi.class);

    Assertions.assertThat(newsApi).isNotNull();

   // System.out.print(newsApi);

    Assertions.assertThat(json).isNotBlank();

    }
}
