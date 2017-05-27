package org.du.personalSite.service.impl;

import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.vo.ArticleInfo;
import org.du.personalSite.service.ArticleService;
import org.du.personalSite.service.base.MarkdownResolver;
import org.du.personalSite.service.base.Registry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     *
     * @param articleInfo
     * @param iscovered       是否使用强制覆盖的方式
     * @return   是否保存成功，目前保存不成功的唯一可能是新文章会覆盖旧文章内容
     * @throws Exception
     */
    @Transactional
    public Boolean saveUnpublishedArt(ArticleInfo articleInfo, Boolean iscovered) throws Exception {
        //markdown解析
        articleInfo.setContent(Registry.query(MarkdownResolver .class).resolve(articleInfo.getOriginalContent()));

        Article article = getFromInfo(articleInfo);

        Article oldArticle = articleDao.getByTitle(article);

        if ( oldArticle == null ){
            articleDao.save(article);
        } else {

            if ( !iscovered && !articleInfo.getOriginalContent().contains(oldArticle.getOriginalContent()) ){
                return false;
            }

            oldArticle.setContent(articleInfo.getContent());
            oldArticle.setOriginalContent(articleInfo.getOriginalContent());
            oldArticle.setCategory(articleInfo.getCategory());
            oldArticle.setArtAbstract(articleInfo.getArtAbstract());
            articleDao.update(oldArticle);
        }

        return true;
    }

    @Transactional
    public List<ArticleInfo> getArticles(Integer cateId) throws Exception {
        if ( cateId == null || cateId == -1 ){
            List<Article> artList = articleDao.findAll(Article.class);
            return articlesToInfo(artList);
        }

        List<Article> artList = articleDao.getByCate(cateId);
        return articlesToInfo(artList);
    }

    public List<ArticleInfo> articlesToInfo(List<Article> artList){
        List<ArticleInfo> infoList = new ArrayList<ArticleInfo>();
        for ( Article art : artList ){
            ArticleInfo artInfo = new ArticleInfo();
            artInfo.setTitle(art.getTitle());
            artInfo.setArtAbstract(art.getArtAbstract());
            artInfo.setId(art.getId());
            infoList.add(artInfo);
        }
        return infoList;
    }

    public Article getFromInfo(ArticleInfo articleInfo){
        Article article = new Article();
        BeanUtils.copyProperties(articleInfo,article);

        return article;
    }

}
