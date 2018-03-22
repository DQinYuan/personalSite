package org.du.personalSite.service;

import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.domain.vo.UserInfo;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface ArticleService {
    Boolean saveUnpublishedArt(ArticleInfo articleInfo, Boolean iscovered) throws Exception;

    ArticleInfo readArticle(User user , String articleId) throws Exception;

    List<ArticleInfo> getAllArticlesByUser(UserInfo userInfo) throws Exception;

    boolean publishToggle(String articleId) throws Exception;

    boolean isArticleOwner(User user, String title) throws PersonalSiteException;

    Long getIdFromTitle(String title) throws PersonalSiteException;

    List<ArticleInfo> getArticlesByPage(Integer cateId, int pageNum);

    List<ArticleInfo> getAllArticles(Integer cateId) throws Exception;

    ArticleInfo getById(long id);
}
