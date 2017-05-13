package org.du.personalSite.service.base;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by duqinyuan on 2017/5/13.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Service
public class Registry {

    private static Registry instance;

    private static ApplicationContext acNow;

    @Resource
    private ApplicationContext ac;

    @PostConstruct
    public void init(){
        acNow = ac;
        Registry.setInstance(this);
    }

    public static <T> T query(Class<T> t){
        return acNow.getBean(t);
    }

    public static Registry getInstance() {
        return instance;
    }

    public static void setInstance(Registry instance) {
        Registry.instance = instance;
    }
}
