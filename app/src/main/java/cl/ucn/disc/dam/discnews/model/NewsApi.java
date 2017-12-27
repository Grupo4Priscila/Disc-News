package cl.ucn.disc.dam.discnews.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by PriscilaGonzalez on 26-10-2017.
 */
@Builder
public final class NewsApi {

    @Getter
    private String status;

    @Getter
    private String source;

    @Getter
    private String sortBy;

    @Getter
    private List<Article> articles;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
