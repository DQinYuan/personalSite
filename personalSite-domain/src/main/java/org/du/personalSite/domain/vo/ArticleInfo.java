package org.du.personalSite.domain.vo;

import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.Comment;

import java.util.List;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class ArticleInfo extends Article {
    Long commentNum;
    List<CommentCustom> commentCustoms;

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public List<CommentCustom> getCommentCustoms() {
        return commentCustoms;
    }

    public void setCommentCustoms(List<CommentCustom> commentCustoms) {
        this.commentCustoms = commentCustoms;
    }
}
