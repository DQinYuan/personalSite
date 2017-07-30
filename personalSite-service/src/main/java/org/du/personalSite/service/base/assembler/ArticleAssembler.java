package org.du.personalSite.service.base.assembler;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.vo.ArticleInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/3.
 */
public class ArticleAssembler {
    public static List<ArticleInfo> articlesToInfo(List<Article> artList){
        List<ArticleInfo> infoList = new ArrayList<ArticleInfo>();
        for ( Article art : artList ){
            ArticleInfo artInfo = new ArticleInfo();
            BeanUtils.copyProperties(art, artInfo);
            infoList.add(artInfo);
        }
        return infoList;
    }

    public static Article getFromInfo(ArticleInfo articleInfo){
        Article article = new Article();
        BeanUtils.copyProperties(articleInfo,article);

        return article;
    }
}
