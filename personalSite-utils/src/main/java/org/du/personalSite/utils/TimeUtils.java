package org.du.personalSite.utils;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by 燃烧杯 on 2017/8/1.
 */
public class TimeUtils {

    public static Date getNowTime(){
        TimeZone tz = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(tz);
        return new Date();
    }
}
