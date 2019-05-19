package hexi.blog.service.impl;

import hexi.blog.dao.MetasDao;
import hexi.blog.model.pojo.Metas;
import hexi.blog.service.MetasService;
import org.springframework.beans.factory.annotation.Autowired;

public class MetasServiceImpl implements MetasService {

    @Autowired
    private MetasDao metasDao;
    @Override
    public Metas findByNameAndType(String name, String tag) {
        return metasDao.findByNameAndType(name,tag);
    }
}
