package org.du.personalSite.service.base;

import org.du.personalSite.domain.utils.Registry;
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
public class RegistryWithAC extends Registry{

    private static ApplicationContext acNow;

    @Resource
    private ApplicationContext ac;

    @PostConstruct
    public void init(){
        acNow = ac;
        setInstance(this);
    }

    @Override
    public <T> T getBean(Class<T> t) {
        return acNow.getBean(t);
    }
}
