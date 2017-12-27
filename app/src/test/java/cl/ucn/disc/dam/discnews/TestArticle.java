package cl.ucn.disc.dam.discnews;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import cl.ucn.disc.dam.discnews.model.Article;

/**
 * Created by PriscilaGonzalez on 15-11-2017.
 */

public final class TestArticle {

/**
 +     * Simple test del constructor via patron builder.
 +    */
@Test
public void testConstructor() {
    final Article article = Article.builder()
            .author("Priscila Gonzalez")
            .title("tutilo")
            .description("alguna descripcion")
            .url("http://www.lalala.com")
            .build();

    Assertions.assertThat(article).isNotNull();
    Assertions.assertThat(article.getAuthor()).isNotBlank();
    Assertions.assertThat(article.getPublishedAt()).isNull();
     }


}
