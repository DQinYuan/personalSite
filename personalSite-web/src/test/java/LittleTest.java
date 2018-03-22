import com.alibaba.fastjson.JSON;
import org.du.personalSite.domain.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by 燃烧杯 on 2017/6/3.
 */
public class LittleTest {
    public static void main(String[] args) {
//        TimeZone tz = TimeZone.getTimeZone("GMT+8");
//        TimeZone.setDefault(tz);
//        System.out.println(new Date());
//        String json = "{title:'aaaa',artAbstract:'aaaaaaaaaa',category:'0',isPublished:'1',latestModifTime:'1501688014'}";
//        Article test = JSON.parseObject(json, Article.class);
//        System.out.println(1);
        Article art = new Article();
        art.setIsPublished(false);
        System.out.println(JSON.toJSONString(art));
    }
}
