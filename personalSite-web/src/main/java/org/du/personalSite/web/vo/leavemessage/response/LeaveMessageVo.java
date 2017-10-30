package org.du.personalSite.web.vo.leavemessage.response;

import org.du.personalSite.domain.vo.LeaveMessageCustom;
import org.du.personalSite.web.vo.ConventionLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/10/29.
 */
public class LeaveMessageVo {
    Long leaveMessageId;
    String content;
    String username;

    public static List<LeaveMessageVo> customs2vos(List<LeaveMessageCustom> customs){
        List<LeaveMessageVo> vos = new ArrayList<LeaveMessageVo>();
        for ( LeaveMessageCustom custom : customs ){
            LeaveMessageVo vo = new LeaveMessageVo();
            vo.setContent(custom.getLeaveMessage().getContent());
            vo.setLeaveMessageId(custom.getLeaveMessage().getId());
            vo.setUsername(ConventionLogic.customUserName(custom));
            vos.add(vo);
        }
        return vos;
    }

    public Long getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(Long leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
