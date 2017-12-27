package cl.ucn.disc.dam.discnews.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.UUID;

import cl.ucn.disc.dam.discnews.dao.AppDatabase;
import cl.ucn.disc.dam.discnews.dao.SourceConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Created by PriscilaGonzalez on 26-10-2017.
 */
@Builder
@Table(database = AppDatabase.class)
@AllArgsConstructor
@NoArgsConstructor
public final class Article {

    /**
     * id
     */
    @PrimaryKey
    @Getter
    UUID id;

    /**
     * autor
     */
    @Getter
    @Column
    String author;

    /**
     * titulo
     */
    @Getter
    @Column
    String title;

    /**
     * descripcion
     */
    @Getter
    @Column
    String description;

    /**
     * URL noticia
     */
    @Getter
    @Column
    String url;

    /**
     * URL imagen
     */
    @Getter
    @Column
    String urlToImage;

    /**
     * fecha
     */
    @Getter
    @Column
    Date publishedAt;

    /**
     * Source
     */
    @Getter
    @Column(typeConverter = SourceConverter.class)
    Source source;

    /**
     * @return the String representation.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Fix the article
     *
     * @param article to fix.
     */
    public static void fix(final Article article) {

        final StringBuilder sb = new StringBuilder();
        sb.append(article.title);
        sb.append("-");
        sb.append(article.publishedAt);

        // Calculate ID from title + publishedAt
        article.id = UUID.nameUUIDFromBytes(sb.toString().getBytes());

        if (article.author == null) {
            article.author = "unknow";
        }
    }


    /**
     * Internal article source.
     */
    @Builder
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static final class Source {


        @Getter
        String id;

        @Getter
        String name;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

    }

}