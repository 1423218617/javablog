package hexi.blog.dao;

import hexi.blog.model.pojo.Metas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetasDao extends JpaRepository<Metas,Integer> {
    public Metas findByNameAndType(String name,String type);
    public List<Metas> findAllByType(String type);
    public int countByType(String type);
}
