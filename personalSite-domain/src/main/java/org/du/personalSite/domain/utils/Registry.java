package org.du.personalSite.domain.utils;

/**
 * Created by 燃烧杯 on 2017/7/28.
 */
public class Registry {
    private static Registry instance;

    public static void setInstance(Registry ainstance){
        instance = ainstance;
    }

    public static Registry getInstance() {
        return instance;
    }

    public static <T> T query(Class<T> t){
        return getInstance().getBean(t);
    }

    public <T> T getBean(Class<T> t){
        return null;
    }
}
