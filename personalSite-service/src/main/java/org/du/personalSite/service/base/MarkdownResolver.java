package org.du.personalSite.service.base;

import org.du.personalSite.domain.utils.MarkdowmInter;
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
            return markdownResolver.process(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
