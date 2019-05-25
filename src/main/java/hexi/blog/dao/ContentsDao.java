package hexi.blog.dao;

import hexi.blog.model.pojo.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsDao extends JpaRepository<Contents,Integer> {
    public Page<Contents> findAllByType(Pageable pageable,String type);
    public Contents findBySlug(String slug);
    public List<Contents> findAllByCidIn(List<Integer> list);
    public List<Contents> findAllByCategories(String categories);
    public Contents findBySlugAndType(String slug,String type);
    public int countByStatusAndType(String status,String type);
    public Page<Contents> findAllByStatusAndTypeOrderByCreatedDesc(String status,String type, Pageable pageable);
}
