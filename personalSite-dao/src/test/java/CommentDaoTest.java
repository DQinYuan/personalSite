import org.du.personalSite.dao.CommentDao;
import org.du.personalSite.domain.Comment;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 燃烧杯 on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ac_personalSite_dao.xml"})
public class CommentDaoTest {
    @Resource
    CommentDao commentDao;

    @Ignore
    @Test
    @Transactional
    public void insertTest() throws Exception{
        Comment comment = new Comment();
        comment.setCreateTime(new Date());
        comment.setContent("测试");
        comment.setOriginalContent("测试");
        comment.setLatestModifTime(new Date());
        comment.setArticleId(new Long(0));
        commentDao.save(comment);
    }

    @Ignore
    @Test
    @Transactional
    public void getNumByArtTest() throws Exception{
        System.out.println(commentDao.getCommentsNumByArticle(2L));
    }
}
