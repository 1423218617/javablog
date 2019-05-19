package hexi.blog.service;

import hexi.blog.model.pojo.Metas;

public interface MetasService {

    public Metas findByNameAndType(String name,String tag);
}
