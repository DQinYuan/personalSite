package org.du.personalSite.web.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by duqinyuan on 2017/5/18.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class AjaxUtils {

    public static void reponseAjax(HttpServletResponse response, Object o){
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(o));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
