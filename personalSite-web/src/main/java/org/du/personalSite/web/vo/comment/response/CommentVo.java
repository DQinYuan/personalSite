package org.du.personalSite.web.vo.comment.response;

import org.du.personalSite.domain.vo.CommentCustom;
import org.du.personalSite.web.vo.ConventionLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/10/28.
 */
public class CommentVo {
    Long commentId;
    String username;
    String replyUsername;
    String content;

    public static CommentVo custom2vo(CommentCustom custom, long artOwnerId){
        CommentVo comment = new CommentVo();
        comment.setContent(custom.getComment().getContent());
        comment.setCommentId(custom.getComment().getId());
        comment.setUsername(ConventionLogic.customUserName(custom, artOwnerId));
        comment.setReplyUsername(
                custom.getParentCustom() == null ? "作者" :
                        ConventionLogic.customUserName(custom.getParentCustom(), artOwnerId)
        );
        return comment;
    }

    public static List<CommentVo> customs2vos(List<CommentCustom> customs, long artOwnerId){
        List<CommentVo> comments = new ArrayList<CommentVo>();
        for ( CommentCustom custom: customs ){
            comments.add(custom2vo(custom, artOwnerId));
        }
        return comments;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
