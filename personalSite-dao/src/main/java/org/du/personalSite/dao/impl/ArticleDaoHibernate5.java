package org.du.personalSite.dao.impl;

import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.domain.Article;
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

    @Override
    public Serializable save(Article article) throws Exception {
        article.setCreateTime(new Date());
        article.setLatestModifTime(new Date());
        return super.save(article);
    }

    @Override
    public void update(Article article) throws Exception {
        article.setLatestModifTime(new Date());
        super.update(article);
    }

    public Article getByTitle(Article article) throws Exception {
        List<Article> artList = find("select a from Article a where a.title = ?0"
                , article.getTitle());
        if ( artList.size() == 0 ) return null;
        return artList.get(0);
    }
}
