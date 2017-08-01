package org.du.personalSite.dao.impl;

import org.du.personalSite.dao.CommentDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/23.
 */
@Repository("commentDao")
public class CommentDaoHibernate5 extends BaseDaoHibernate5<Comment> implements CommentDao {
    public List<Comment> getCommentsByArticle(Long articleId){
        return find("select c from Comment c where c.articleId = ?0 order by latestModifTime desc", articleId);
    }

    public Long getCommentsNumByArticle(Long articleId){
        return findNum("select count(*) from Comment c where c.articleId = ?0 order by latestModifTime desc", articleId);
    }
}
