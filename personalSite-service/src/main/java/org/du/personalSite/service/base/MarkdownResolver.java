package org.du.personalSite.service.base;

import org.apache.commons.lang3.StringEscapeUtils;
import org.du.personalSite.domain.utils.MarkdowmInter;
import org.du.personalSite.utils.MyStringUtils;
import org.markdown4j.Markdown4jProcessor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by duqinyuan on 2017/5/13.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Service
public class MarkdownResolver implements MarkdowmInter{

    Markdown4jProcessor markdownResolver = new Markdown4jProcessor();

    public String resolve(String input) {
        try {
            String[] inputs = input.split("```");
            for ( int i = 0; i < inputs.length; i++ ){
                if ( i % 2 == 0 ){
                    inputs[i] = StringEscapeUtils.escapeHtml4(inputs[i]);
                }
            }
            String temp = MyStringUtils.joinAll(inputs, "```");
            String[] temps = temp.split("`");
            for ( int i = 0; i < temps.length; i++ ){
                if ( i % 2 != 0 ){
                    temps[i] = StringEscapeUtils.unescapeHtml4(temps[i]);
                }
            }
            return markdownResolver.process(MyStringUtils.joinAll(temps, "`"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
