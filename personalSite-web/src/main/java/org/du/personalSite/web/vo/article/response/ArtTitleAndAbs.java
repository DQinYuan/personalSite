package org.du.personalSite.web.vo.article.response;

import org.du.personalSite.domain.vo.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/10/19.
 */
public class ArtTitleAndAbs {
    private Long id;
    private Integer category;
    private String title;
    private String artAbstract;

    public static List<ArtTitleAndAbs> infos2its(List<ArticleInfo> infos){
        List<ArtTitleAndAbs> titleList = new ArrayList<ArtTitleAndAbs>();
        for ( ArticleInfo info : infos ){
            ArtTitleAndAbs title = new ArtTitleAndAbs();
            title.setTitle(info.getTitle());
            title.setId(info.getId());
            title.setCategory(info.getCategory());
            title.setArtAbstract(info.getArtAbstract());
            titleList.add(title);
        }
        return titleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtAbstract() {
        return artAbstract;
    }

    public void setArtAbstract(String artAbstract) {
        this.artAbstract = artAbstract;
    }
}
