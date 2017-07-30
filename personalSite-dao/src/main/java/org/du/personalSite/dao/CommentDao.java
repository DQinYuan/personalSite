package org.du.personalSite.dao;

import org.du.personalSite.dao.base.BaseDao;
import org.du.personalSite.domain.Comment;

import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/23.
 */
public interface CommentDao extends BaseDao<Comment> {

    public List<Comment> getCommentsByArticle(Long articleId);

    public Long getCommentsNumByArticle(Long articleId);
}
