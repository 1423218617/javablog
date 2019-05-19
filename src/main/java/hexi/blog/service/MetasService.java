package hexi.blog.service;

import hexi.blog.model.pojo.Metas;

import java.util.List;

public interface MetasService {

    public Metas findByNameAndType(String name,String tag);
    public List<Metas> findAllByType(String type);
}
