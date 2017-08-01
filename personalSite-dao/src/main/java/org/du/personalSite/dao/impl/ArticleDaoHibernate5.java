package org.du.personalSite.dao.impl;

import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Repository("articleDao")
public class ArticleDaoHibernate5 extends BaseDaoHibernate5<Article> implements ArticleDao {

    public Article getByTitle(String title){
        List<Article> artList = find("select a from Article a where a.title = ?0 order by latestModifTime desc"
                , title);
        if ( artList.size() == 0 ) return null;
        return artList.get(0);
    }

    public List<Article> getByCate(Integer cateId) throws Exception {
        return find("select a from Article a where a.category = ?0 order by latestModifTime desc", cateId);
    }

    public List<Article> getByUser(User user) throws Exception {
        return find("select a from Article a where a.owner = ?0 order by latestModifTime desc", user);
    }

    public List<Article> findByIsPublished(boolean isPublished) throws Exception {
        return find("select a from Article a where a.isPublished = ?0 order by latestModifTime desc", isPublished);
    }

    public List<Article> getByCateAndIsPublished(Integer cateId, boolean isPublished) throws Exception {
        return find("select a from Article a where a.category = ?0 and a.isPublished = ?1 order by latestModifTime desc", cateId, isPublished);
    }

    public List<Article> getByPageAndIsPublished(int pageNum, boolean isPublished) {
        String hql = "select a from Article a where a.isPublished = ?0 order by latestModifTime desc";
        return findByPage(hql, pageNum, PAGE_SIZE, isPublished);
    }

    public List<Article> getByPageAndCateAndIsPublished(int pageNum,int cateId ,boolean isPublished) {
        String hql = "select a from Article a where a.category = ?0 and a.isPublished = ?1 order by latestModifTime desc";
        return findByPage(hql, pageNum, PAGE_SIZE, cateId, isPublished);
    }
}
