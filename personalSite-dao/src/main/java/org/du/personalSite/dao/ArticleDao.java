package org.du.personalSite.dao;

import org.du.personalSite.dao.base.BaseDao;
import org.du.personalSite.domain.Article;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
public interface ArticleDao extends BaseDao<Article> {
    public Article getByTitle(Article article) throws Exception;

    List<Article> getByCate(Integer cateId) throws Exception;
}
