package org.du.personalSite.service;

import org.du.personalSite.domain.vo.ArticleInfo;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface ArticleService {
    public Boolean saveUnpublishedArt(ArticleInfo articleInfo, Boolean iscovered) throws Exception;
}
