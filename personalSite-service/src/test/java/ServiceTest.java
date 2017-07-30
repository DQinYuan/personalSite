import org.junit.Test;
import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;

/**
 * Created by duqinyuan on 2017/5/13.
 *
 * @author duqinyuan
 * @version 1.0
 */
public class ServiceTest {

    @Test
    public void markdownTest(){
        String markdownString = "<script>alert('aaa')</script>";
        try {
            String result = new Markdown4jProcessor().process(markdownString);
            System.out.println(markdownString);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
