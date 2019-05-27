package hexi.blog.service.impl;

import hexi.blog.dao.ContentsDao;
import hexi.blog.dao.MetasDao;
import hexi.blog.dao.RelationshipsDao;
import hexi.blog.emun.ContentsStatusEnum;
import hexi.blog.emun.MetasTypeEnum;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.pojo.Metas;
import hexi.blog.model.pojo.Relationships;
import hexi.blog.service.ContentsService;
import hexi.blog.service.MetasService;
import hexi.blog.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ContentsServiceImpl implements ContentsService {


    @Autowired
    private ContentsDao contentsDao;

    @Autowired
    private RelationshipsDao relationshipsDao;

    @Autowired
    private MetasService metasService;


    @Override
    public Page<Contents> contentsPage(Pageable pageable,String type,String status) {
        return contentsDao.findAllByTypeAndStatus(pageable,type,status);
    }

    @Override
    public Contents findContentsByCid(Integer cid) {
        return contentsDao.getOne(cid);
    }

    @Override
    public Contents findContentsBySlug(String slug) {
        return contentsDao.findBySlug(slug);
    }

    @Override
    public List<Contents> findAll() {
        return contentsDao.findAll();
    }

    @Override
    public List<Contents> findAllContentsByTagAndName(String tag,String name) {
        Metas metas=metasService.findByNameAndType(name,tag);
        List<Relationships> relationshipsList=relationshipsDao.findAllByMid(metas.getMid());
        List<Integer> contentsCidList=relationshipsList.stream().
                map(relationships -> relationships.getCid()).collect(Collectors.toList());
        List<Contents> articles=contentsDao.findAllByCidIn(contentsCidList);
        Collections.sort(articles);
        return articles;
    }

    @Override
    public List<Contents> findAllContentsByCategories(String category) {
        List<Contents> articles=contentsDao.findAllByCategories(category);
        Collections.sort(articles);
        return articles;
    }

    @Override
    public Contents findContentsBySlugAndType(String slug, String type) {
        return contentsDao.findBySlugAndType(slug,type);
    }

    @Override
    public void saveNew(Contents contents) {
        int time= DateUtil.getUnixTime();
        contents.setHits(0);
        contents.setCreated(time);
        contents.setModified(time);
        contents.setCommentsNum(0);
        contentsDao.save(contents);
        String[] tags=StringUtils.split(contents.getTags(),",");
        for (String t:tags){
            Metas metas=new Metas();
            metas.setName(t);
            metas.setSlug(t);
            metas.setType(MetasTypeEnum.TAG.getType());
            metasService.save(metas);
            Relationships relationships=new Relationships();
            relationships.setCid(contents.getCid());
            relationships.setMid(metas.getMid());
            relationshipsDao.save(relationships);
        }


    }

    @Override
    public void saveOld(Contents contents) {
        Contents contentsOld=contentsDao.getOne(contents.getCid());
        int modifyTime= DateUtil.getUnixTime();
        contents.setModified(modifyTime);
        contents.setHits(contentsOld.getHits());
        contents.setCreated(contentsOld.getCreated());
        contents.setCommentsNum(contentsOld.getCommentsNum());
        contentsDao.save(contents);
        String[] tags=StringUtils.split(contents.getTags(),",");
        List<Integer> mids=new ArrayList<>();
        for (String t:tags){
            Metas metas=new Metas();
            metas.setName(t);
            metas.setSlug(t);
            metas.setType(MetasTypeEnum.TAG.getType());
            metasService.save(metas);
            Relationships relationships=new Relationships();
            relationships.setCid(contents.getCid());
            relationships.setMid(metas.getMid());
            relationshipsDao.save(relationships);
            mids.add(relationships.getMid());
        }
        relationshipsDao.deleteByCidAndMidNotIn(contents.getCid(),mids);
        System.out.println(contents);
    }

    @Override
    public void update(Contents contents) {
        contentsDao.save(contents);
    }

    @Override
    public Page<Contents> findAllContentsByTypeOrderByCreatedDesc(String type,Pageable pageable) {
        return contentsDao.findAllByTypeOrderByCreatedDesc(type,pageable);
    }

    @Override
    public void deleteContents(Integer cid) {
        relationshipsDao.deleteByCid(cid);
        contentsDao.deleteByCid(cid);
    }
}
