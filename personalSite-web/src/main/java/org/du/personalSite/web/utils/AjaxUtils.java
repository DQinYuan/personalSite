package org.du.personalSite.web.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by duqinyuan on 2017/5/18.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class AjaxUtils {

    public static void reponseAjax(HttpServletResponse response, Object o) throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(o));
    }
}
