package org.du.personalSite.dao;

import org.du.personalSite.dao.base.BaseDao;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.User;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface ArticleDao extends BaseDao<Article> {

    public static final int PAGE_SIZE = 20;

    public Article getByTitle(String title);

    List<Article> getByCate(Integer cateId) throws Exception;

    List<Article> getByUser(User user) throws Exception;

    List<Article> findByIsPublished(boolean isPublished) throws Exception;

    List<Article> getByCateAndIsPublished(Integer cateId, boolean isPublished) throws Exception;

    List<Article> getByPageAndIsPublished(int pageNum, boolean isPublished);

    List<Article> getByPageAndCateAndIsPublished(int pageNum,int cateId ,boolean isPublished);
}
