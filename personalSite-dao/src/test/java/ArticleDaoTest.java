import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.domain.Article;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 燃烧杯 on 2017/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ac_personalSite_dao.xml"})
public class ArticleDaoTest {

    @Resource
    ArticleDao articleDao;

    @Ignore
    @Test
    @Transactional
    public void test() throws Exception {
        Article article = articleDao.getByTitle("Java");
        System.out.println(article.getIsPublished() + "");
    }
}
