package hexi.blog.service.impl;

import hexi.blog.dao.ContentsDao;
import hexi.blog.dao.MetasDao;
import hexi.blog.dao.RelationshipsDao;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.pojo.Metas;
import hexi.blog.model.pojo.Relationships;
import hexi.blog.service.ContentsService;
import hexi.blog.service.MetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public Page<Contents> contentsPage(Pageable pageable) {
        return contentsDao.findAll(pageable);
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
    public List<Contents> findContentsByTagAndName(String tag,String name) {
        Metas metas=metasService.findByNameAndType(name,tag);
        List<Relationships> relationshipsList=relationshipsDao.findAllByMid(metas.getMid());
        List<Integer> contentsCidlist=relationshipsList.stream().
                map(relationships -> relationships.getCid()).collect(Collectors.toList());
        List<Contents> articles=contentsDao.findAllByCidIn(contentsCidlist);
        return articles;
    }
}
