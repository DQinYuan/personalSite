package org.du.personalSite.domain.vo;

import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.User;

/**
 * Created by 燃烧杯 on 2017/6/30.
 */
public class CommentCustom {
    Comment comment;

    User user;

    CommentCustom parentCustom;

    Boolean canModify;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CommentCustom getParentCustom() {
        return parentCustom;
    }

    public void setParentCustom(CommentCustom parentCustom) {
        this.parentCustom = parentCustom;
    }

    public Boolean getCanModify() {
        return canModify;
    }

    public void setCanModify(Boolean canModify) {
        this.canModify = canModify;
    }
}
