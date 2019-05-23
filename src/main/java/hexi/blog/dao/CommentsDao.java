package hexi.blog.dao;

import hexi.blog.model.pojo.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDao extends JpaRepository<Comments,Integer> {
    public Page<Comments> findAllByCid(Pageable pageable,Integer cid);
    public Page<Comments> findAllByStatusOrderByCreatedDesc(Pageable pageable,String status);
    public int countByStatus(String status);
}
