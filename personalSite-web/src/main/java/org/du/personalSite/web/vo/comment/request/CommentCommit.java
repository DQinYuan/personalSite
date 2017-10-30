package org.du.personalSite.web.vo.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by 燃烧杯 on 2017/10/28.
 */
public class CommentCommit {
    @NotNull(message = "{notnull}")
    String articleId;
    @NotNull(message = "{notnull}")
    @NotEmpty(message = "评论内容不能为空")
    String originalContent;
    String responseCommentId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getResponseCommentId() {
        return responseCommentId;
    }

    public void setResponseCommentId(String responseCommentId) {
        this.responseCommentId = responseCommentId;
    }
}
