package org.du.personalSite.service.impl;

import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.CommentDao;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.Comment;
import org.du.personalSite.domain.User;
import org.du.personalSite.domain.exception.PersonalSiteException;
import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.domain.vo.UserInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.service.CommentService;
import org.du.personalSite.service.base.assembler.ArticleAssembler;
import org.du.personalSite.service.base.customer.CommentCustomer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.du.personalSite.utils.TimeUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duqinyuan on 2017/5/11.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleDao articleDao;

    @Resource
    CommentDao commentDao;

    @Resource
    CommentService commentService;

    /**
     *
     * @param articleInfo
     * @param iscovered       是否使用强制覆盖的方式
     * @return   是否保存成功，目前保存不成功的唯一可能是新文章会覆盖旧文章内容
     * @throws Exception
     */
    @Transactional
    public Boolean saveUnpublishedArt(ArticleInfo articleInfo, Boolean iscovered) throws Exception {

        Article article = ArticleAssembler.getFromInfo(articleInfo);

        Article oldArticle = articleDao.getByTitle(article.getTitle());

        if ( oldArticle == null ){            //存储新文章
            article.setCreateTime(TimeUtils.getNowTime());
            article.setLatestModifTime(TimeUtils.getNowTime());
            article.generateContent(article.getOriginalContent());
            article.setIsPublished(false);
            articleDao.save(article);
        } else {   //存储以前的文章

            if ( !iscovered && !articleInfo.getOriginalContent().contains(oldArticle.getOriginalContent()) ){
                return false;
            }

            oldArticle.generateContent(articleInfo.getOriginalContent());
            oldArticle.setCategory(articleInfo.getCategory());
            oldArticle.setArtAbstract(articleInfo.getArtAbstract());
            article.setLatestModifTime(TimeUtils.getNowTime());
            articleDao.update(oldArticle);
        }

        return true;
    }

    @Transactional
    public List<ArticleInfo> getArticles(Integer cateId) throws Exception {
        if ( cateId == null || cateId == -1 ){
            List<Article> artList = articleDao.findByIsPublished(true);
            return ArticleAssembler.articlesToInfo(artList);
        }

        List<Article> artList = articleDao.getByCateAndIsPublished(cateId, true);
        return ArticleAssembler.articlesToInfo(artList);
    }

    @Transactional
    public ArticleInfo readArticle(User user , String articleId) throws Exception {
        Article art = articleDao.get(Article.class, Long.parseLong(articleId));

        //文章不存在或者尚未发表的情况
        if ( art == null || !art.getIsPublished() ){
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setTitle("未发表文章");
            articleInfo.setContent("该文章尚未发表，无法查看");
            return articleInfo;
        }


        //记录文章被阅读次数
        art.beReaded(1);      //被阅读一次
        articleDao.update(art);

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(art, articleInfo);
        //获得文章相关评论
        List<Comment> comments = commentDao.getCommentsByArticle(Long.parseLong(articleId));
       if ( comments == null ){
           comments = new ArrayList<Comment>();
       }

        articleInfo.setCommentCustoms(CommentCustomer.batchCustom(user, comments));

        return articleInfo;
    }

    @Transactional
    public List<ArticleInfo> getAllArticlesByUser(UserInfo userInfo) throws Exception {
        List<Article> articles = articleDao.getByUser(userInfo);
        List<ArticleInfo> articleInfos =  ArticleAssembler.articlesToInfo(articles);
        for ( ArticleInfo articleInfo : articleInfos ){
            Long commentNum = commentDao.getCommentsNumByArticle(articleInfo.getId());
            articleInfo.setCommentNum(commentNum);
        }
        return articleInfos;
    }

    @Transactional
    public ArticleInfo getByTitle(String title) {
        Article article = articleDao.getByTitle(title);

        if ( article == null ){
            return null;
        }

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(article, articleInfo);
        return articleInfo;
    }

    @Transactional
    public boolean publishToggle(String articleId) throws Exception {
        Article article = articleDao.get(Article.class, Long.parseLong(articleId));
        if ( article == null ){
            return false;
        }

        article.publishToggle();
        articleDao.update(article);

        return true;
    }

    @Transactional
    public List<ArticleInfo> getArticlesByPage(Integer cateId, int pageNum) {
        if ( cateId == null || cateId == -1 ){
            List<Article> articles = articleDao.getByPageAndIsPublished(pageNum, true);
            return ArticleAssembler.articlesToInfo(articles);
        }

        List<Article> articles = articleDao.getByPageAndCateAndIsPublished(pageNum,cateId , true);
        return ArticleAssembler.articlesToInfo(articles);
    }

    @Transactional
    public ArticleInfo getById(long id) {
        Article art = articleDao.get(Article.class, id);
        if ( art == null ){
            return null;
        }

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(art, articleInfo);
        return articleInfo;
    }

    public boolean isArticleOwner(User user, String title) throws PersonalSiteException {
        Article article = articleDao.getByTitle(title);
        if ( article == null ){
            throw new PersonalSiteException("该文章不存在");
        }

        if ( article.getOwner().getNickname().equals(user.getNickname()) ){
            return true;
        }
        return false;
    }

    public Long getIdFromTitle(String title) throws PersonalSiteException {
        Article article = articleDao.getByTitle(title);
        if ( article == null ){
            throw new PersonalSiteException("该文章不存在");
        }

        return article.getId();
    }
}
