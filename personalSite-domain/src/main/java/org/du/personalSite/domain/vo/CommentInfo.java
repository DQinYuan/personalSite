package org.du.personalSite.domain.vo;


/**
 * Created by 燃烧杯 on 2017/6/23.
 */
public class CommentInfo {
    private Long articleId;
    private String originalContent;
    private Long responseUserId;            //回复的用户id，如果为null则表示回复的是文章作者
    private Long responseCommentId;            //回复的评论id，如果为null则表示回复的是文章作者


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public Long getResponseUserId() {
        return responseUserId;
    }

    public void setResponseUserId(Long responseUserId) {
        this.responseUserId = responseUserId;
    }

    public Long getResponseCommentId() {
        return responseCommentId;
    }

    public void setResponseCommentId(Long responseCommentId) {
        this.responseCommentId = responseCommentId;
    }
}
