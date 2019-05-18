package hexi.blog.dao;

import hexi.blog.model.pojo.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsDao extends JpaRepository<Contents,Integer> {
    public Contents findBySlug(String slug);

}
