package hexi.blog.dao;

import hexi.blog.model.pojo.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsDao extends JpaRepository<Contents,Integer> {
    public Contents findBySlug(String slug);
    public List<Contents> findAllByTags(String tag);
}
