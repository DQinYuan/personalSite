import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by 燃烧杯 on 2017/6/3.
 */
public class LittleTest {
    public static void main(String[] args) {
        TimeZone tz = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(tz);
        System.out.println(new Date());
    }
}
