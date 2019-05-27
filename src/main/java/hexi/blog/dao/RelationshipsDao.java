package hexi.blog.dao;

import hexi.blog.model.pojo.Relationships;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface RelationshipsDao extends JpaRepository<Relationships,Integer> {
    public List<Relationships> findAllByMid(Integer mid);
    public void deleteByCidAndMidNotIn(Integer cid,List<Integer> mid);
    public void deleteByCid(Integer cid);
    public void deleteByMid(Integer mid);
}
