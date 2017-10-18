package org.du.personalSite.web.controller;

import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.LeaveMessageCustom;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.LeaveMessageService;
import org.du.personalSite.utils.MyStringUtils;
import org.du.personalSite.web.utils.AjaxUtils;
import org.du.personalSite.web.utils.CheckUtils;
import org.du.personalSite.web.utils.Generator;
import org.du.personalSite.web.utils.MvUtils;
import org.du.personalSite.web.vo.response.OriginalContentVo;
import org.du.personalSite.web.vo.response.leavemessage.ModifyLeaveMessageInfo;
import org.du.personalSite.web.vo.response.leavemessage.SaveLeaveMessagesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/7/12.
 */
@Controller
@RequestMapping("leaveMessages")
public class LeaveMessageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    LeaveMessageService leaveMessageService;

    @RequestMapping(value = "saveLeaveMessage", method = RequestMethod.POST)
    public void saveLeaveMessage(String originalContent, HttpServletResponse response
        , HttpServletRequest request, HttpSession session){
        if (MyStringUtils.isBlank(originalContent)){
            AjaxUtils.reponseAjax(response, SaveLeaveMessagesInfo.getErrorEntity("留言不能为空"));
            return;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        if ( userInfo == null ){
            userInfo = Generator.generateUser(request, session);
            session.setAttribute(WebConstant.USER, userInfo);
            session.setAttribute(WebConstant.NICKNAME, userInfo.getNickname());
        }


        LeaveMessageCustom custom = leaveMessageService.saveLeaveMessage(originalContent,
                request.getRemoteAddr(), Generator.generateSessionNum(session), userInfo);

        logger.info("用户" + userInfo.getNickname() + "留言成功");
        AjaxUtils.reponseAjax(response, SaveLeaveMessagesInfo.getSuccessEntity(custom));
    }

    @RequestMapping(value = "getAllLeaveMessages", method = RequestMethod.POST)
    public void getAllLeaveMessages(HttpServletResponse response, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        List<LeaveMessageCustom> customs = leaveMessageService.getAllLeaveMessageCustoms(userInfo);
        AjaxUtils.reponseAjax(response, customs);
    }

    @RequestMapping(value = "originalContent", method = RequestMethod.POST)
    public void getOriginalContent(String leaveMessageId, HttpServletResponse response){
        if ( !MyStringUtils.isNum(leaveMessageId) ){
            AjaxUtils.reponseAjax(response, OriginalContentVo.getErrorEntity("参数格式不正确"));
            return;
        }

        try {
            String originalContent = leaveMessageService.getOriginalContent(Long.parseLong(leaveMessageId));
            AjaxUtils.reponseAjax(response, OriginalContentVo.getSuccessEntity(originalContent));
        } catch (PersonalSiteException e){
            AjaxUtils.reponseAjax(response, OriginalContentVo.getErrorEntity(e.getMessage()));
        }

    }

    @RequestMapping(value = "modifyLeaveMessage", method = RequestMethod.POST)
    public void modifyLeaveMessage(String leaveMessageId, String originalContent,
                                   HttpServletResponse response, HttpSession session){
        if ( !MyStringUtils.isNum(leaveMessageId) ){
            AjaxUtils.reponseAjax(response, ModifyLeaveMessageInfo.getErrorEntity("参数格式不正确"));
            return;
        }

        if ( MyStringUtils.isBlank(originalContent) ){
            AjaxUtils.reponseAjax(response, ModifyLeaveMessageInfo.getErrorEntity("留言不能为空"));
            return;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);

        try {
            String content = leaveMessageService.modifyLeaveMessage(userInfo,
                    Long.parseLong(leaveMessageId), originalContent);
            AjaxUtils.reponseAjax(response, ModifyLeaveMessageInfo.getSuccessEntity(content));
            logger.info("用户" + userInfo.getNickname() + "修改留言" + leaveMessageId + "成功");
        } catch (PersonalSiteException e){
            AjaxUtils.reponseAjax(response, ModifyLeaveMessageInfo.getErrorEntity(e.getMessage()));
        }
    }

    @RequestMapping("manage")
    public ModelAndView manageLeaveMessage(HttpSession session){
        if (!CheckUtils.checkLoginAndLevel(session)){
            return MvUtils.getIllgalRequestMv();
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(WebConstant.USER);
        List<LeaveMessageCustom> customs = leaveMessageService.getAllLeaveMessageCustoms(userInfo);

        ModelAndView mv = new ModelAndView();
        mv.addObject("leaveMessageCustoms", customs);
        mv.setViewName("privilegePages/leaveMessagesList");

        return mv;
    }

    @RequestMapping("delete")
    public void deleteLeaveMessage(String leaveMessageId,
                                   HttpServletResponse response, HttpSession session){
        if ( !CheckUtils.checkLoginAndLevel(session) ){
            return;
        }
        if ( !MyStringUtils.isNum(leaveMessageId) ){
            return;
        }

        leaveMessageService.deleteLeaveMessage(Long.parseLong(leaveMessageId));

        return;
    }
}
