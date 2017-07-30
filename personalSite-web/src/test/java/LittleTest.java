import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/3.
 */
public class LittleTest {
    public static void main(String[] args) {
        //System.out.printf(JSON.toJSONString(true));
        //System.out.println(UUID.randomUUID().toString());

//        OriginalContentVo vo = new OriginalContentVo();
//        vo.setIsSuccess(true);
//        vo.setErrorInfo("曹");
//        vo.setOriginalContent("777777777");
//        System.out.println(JSON.toJSONString(vo));

//        List<Integer> integers = new ArrayList<Integer>();
//        integers.add(2);
//        integers.add(3);
//        integers.add(5);
//        System.out.println(JSON.toJSONString(integers));

        Long l = 3l;
        System.out.println(l == null);
    }
}
