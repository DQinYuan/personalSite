package org.du.personalSite.web.vo.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by 燃烧杯 on 2017/10/29.
 */
public class ModifyCommentCommit {
    @NotNull(message = "{notnull}")
    @NotEmpty(message = "评论内容不能为空")
    String newOriginalContent;

    public String getNewOriginalContent() {
        return newOriginalContent;
    }

    public void setNewOriginalContent(String newOriginalContent) {
        this.newOriginalContent = newOriginalContent;
    }
}
