package org.du.personalSite.web.utils;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 燃烧杯 on 2017/6/14.
 */
public class MvUtils {
    public static ModelAndView getIllgalRequestMv(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/illegalRequest");
        return mv;
    }
}
