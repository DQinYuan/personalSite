package org.du.personalSite.service.base;

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
public class MarkdownResolver {

    Markdown4jProcessor markdownResolver = new Markdown4jProcessor();

    public String resolve(String input) throws IOException {
        return markdownResolver.process(input);
    }

}
