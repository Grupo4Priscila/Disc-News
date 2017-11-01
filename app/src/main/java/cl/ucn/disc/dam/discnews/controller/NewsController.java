package cl.ucn.disc.dam.discnews.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dam.discnews.model.Article;
import cl.ucn.disc.dam.discnews.model.NewsApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PriscilaGonzalez on 26-10-2017.
 */

public final class NewsController {
    /**
     * Des-serializar
     */
    private static final Gson gson= new GsonBuilder()
            .serializeNulls() // todos los atributos aun que sean nulos
            .setPrettyPrinting() //imprime bonito, TODO: eliminar en modo produccion
            .create();

    /**
     * Url donde se obtienen los articulos
     */
    final String url ="https://newsapi.org/v1/articles?source=mtv-news&sortBy=latest&apiKey=2ab0d82206134319b9ea35ac43db8e9f";

    /**
     * Cliete okHttp
     */
    private final OkHttpClient okHttpClient = new OkHttpClient();
    /**
     * Obtencion de articulos de internet
     *
     * @return la lista de articulos
     */
    public List<Article> getArticles() throws IOException {
        //Peticion
        final Request request = new Request.Builder()
                .url(url)
                .build();

        //Respuesta
        final Response response = okHttpClient.newCall(request).execute();
        final String json= response.body().string();
        final NewsApi newsApi = gson.fromJson(json,NewsApi.class);
        return newsApi.getArticles();

   }

}
