package org.du.personalSite.service;

import org.du.personalSite.domain.vo.ArticleInfo;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface ArticleService {
    Boolean saveUnpublishedArt(ArticleInfo articleInfo, Boolean iscovered) throws Exception;

    List<ArticleInfo> getArticles(Integer cateId) throws Exception;
}
