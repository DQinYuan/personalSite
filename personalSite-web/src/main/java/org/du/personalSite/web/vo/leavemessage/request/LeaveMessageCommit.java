package org.du.personalSite.web.vo.leavemessage.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by 燃烧杯 on 2017/10/29.
 */
public class LeaveMessageCommit {
    @NotNull(message = "{notnull}")
    @NotEmpty(message = "留言不能为空")
    String originalContent;

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
}
