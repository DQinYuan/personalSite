package org.du.personalSite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 燃烧杯 on 2017/10/27.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value={"/","/index"})
    public ModelAndView userIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/static/index.html");
        return mv;
    }
}
