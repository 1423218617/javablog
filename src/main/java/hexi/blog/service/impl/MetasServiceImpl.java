package hexi.blog.service.impl;

import hexi.blog.dao.MetasDao;
import hexi.blog.dao.RelationshipsDao;
import hexi.blog.model.pojo.Metas;
import hexi.blog.service.MetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MetasServiceImpl implements MetasService {

    @Autowired
    private MetasDao metasDao;

    @Autowired
    private MetasService metasService;

    @Autowired
    private RelationshipsDao relationshipsDao;
    @Override
    public Metas findByNameAndType(String name, String tag) {
        return metasDao.findByNameAndType(name,tag);
    }
}
