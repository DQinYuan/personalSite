package org.du.personalSite.web.controller;

import org.du.personalSite.domain.FriendLink;
import org.du.personalSite.service.FriendLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 燃烧杯 on 2018/3/9.
 */
@Controller
@RequestMapping("friendly-links")
public class FriendLinkController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    FriendLinkService friendLinkService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<FriendLink> getAll(){
        return friendLinkService.getAllFriendLinks();
    }

}
