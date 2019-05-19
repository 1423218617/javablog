package hexi.blog.dao;

import hexi.blog.model.pojo.Metas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetasDao extends JpaRepository<Metas,Integer> {
    public Metas findByNameAndType(String name,String type);
}
