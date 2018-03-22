import com.alibaba.fastjson.JSON;
import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.UserDao;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 燃烧杯 on 2017/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ac_personalSite_dao.xml"})
public class ArticleDaoTest {

    @Resource
    ArticleDao articleDao;

    @Resource
    UserDao userDao;


    @Ignore
    @Test
    @Transactional
    public void testUpdata() throws Exception {
        Article art = JSON.parseObject(
                "{title:'aaab',artAbstract:'aaaaaaaaaa',category:0,latestModifTime:" +
                        "1501688014111,isPublished:true,id:1}",
                Article.class);
        art.setLatestModifTime(new Date(1501688014222L));
        art.setTitle("aaac");
        articleDao.update(art);
    }

    @Test
    @Transactional
    public void testSave(){
        Article art = new Article();
        art.setTitle("2018-3-1111日的新文章");
        art.setArtAbstract("2018-3-1111日的新文章");
        art.setOriginalContent("#2018-3-1111日的新文章");
        art.setContent("<h1>2018-3-1111日的新文章</h1>");
        art.setLatestModifTime(new Date());
        art.setCreateTime(new Date());
        art.setIsPublished(true);
        User dqy = userDao.get(User.class, 1L);
        art.setOwner(dqy);
        articleDao.save(art);
    }
}
