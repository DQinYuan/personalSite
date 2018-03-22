package org.du.personalSite.dao.impl;

import com.alibaba.fastjson.JSON;
import org.du.personalSite.dao.ArticleDao;
import org.du.personalSite.dao.base.impl.BaseDaoHibernate5;
import org.du.personalSite.dao.utils.RedisUtils;
import org.du.personalSite.domain.Article;
import org.du.personalSite.domain.User;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by duqinyuan on 2017/5/6.
 *
 * @author duqinyuan
 * @version 1.0
 */
@Repository("articleDao")
public class ArticleDaoHibernate5 extends BaseDaoHibernate5<Article> implements ArticleDao {

    private static final String TABLE_NAME = "SYS_ART_TABLE_PUBLISHED";
    private static final String ASSIST_TABLE = "ID_SCORE_HASH";
    private static final String UPDATE_SHA = "c5ba0106a8334a61dd51da917ee43184925c20ef";

    public Article getByTitle(String title){
        List<Article> artList = find("select a from Article a where a.title = ?0 order by latestModifTime desc"
                , title);
        if ( artList.size() == 0 ) return null;
        return artList.get(0);
    }

    public List<Article> getByCate(Integer cateId) throws Exception {
        return find("select a from Article a where a.category = ?0 order by latestModifTime desc", cateId);
    }

    public List<Article> getByUser(User user) throws Exception {
        return find("select a from Article a where a.owner = ?0 order by latestModifTime desc", user);
    }

    public List<Article> findByIsPublished(boolean isPublished) throws Exception {
        return find("select a from Article a where a.isPublished = ?0 order by latestModifTime desc", isPublished);
    }

    public List<Article> getByCateAndIsPublished(Integer cateId, boolean isPublished) throws Exception {
        return find("select a from Article a where a.category = ?0 and a.isPublished = ?1 order by latestModifTime desc", cateId, isPublished);
    }

    public List<Article> getByPageAndIsPublished(int pageNum, boolean isPublished) {
        String hql = "select a from Article a where a.isPublished = ?0 order by latestModifTime desc";
        return findByPage(hql, pageNum, PAGE_SIZE, isPublished);
    }

    public List<Article> getByPageAndCateAndIsPublished(int pageNum,int cateId ,boolean isPublished) {
        String hql = "select a from Article a where a.category = ?0 and a.isPublished = ?1 order by latestModifTime desc";
        return findByPage(hql, pageNum, PAGE_SIZE, cateId, isPublished);
    }

    public List<Article> getPublishedCache() {
        Jedis j = RedisUtils.getJedis();
        Set<String> jsons = j.zrevrange(TABLE_NAME, 0, -1);
        RedisUtils.returnResource(j);
        List<Article> articles = new ArrayList<Article>();
        for ( String json : jsons ){
            Article art = JSON.parseObject(json, Article.class);
            if ( art.getIsPublished() ){
                articles.add(art);
            }
        }
        return articles;
    }

    @Override
    public Serializable save(Article entity) {
        Long id = (Long) super.save(entity);
        Jedis j  = RedisUtils.getJedis();
        Transaction tx = j.multi();
        Article cache  = entity.generateCacheObj();
        tx.zadd(TABLE_NAME, cache.getLatestModifTime().getTime(), JSON.toJSONString(entity));
        tx.hset(ASSIST_TABLE, id + "", entity.getLatestModifTime().getTime() + "");
        tx.exec();
        RedisUtils.returnResource(j);
        return id;
    }


    @Override
    public void update(Article entity) {
        super.update(entity);
        Jedis j = RedisUtils.getJedis();

        Article cache = entity.generateCacheObj();
        String json = JSON.toJSONString(cache);
        //更新文章的lua脚本的sha，参数：2 id 新的latestModifTime 新的json
        j.evalsha(UPDATE_SHA, 2
                , cache.getId() + "",
                cache.getLatestModifTime().getTime() + "", json);
        RedisUtils.returnResource(j);
    }
}
