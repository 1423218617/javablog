package hexi.blog.dao;

import hexi.blog.model.pojo.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDao extends JpaRepository<Comments,Integer> {
    public Page<Comments> findAllByCidOrderByCreatedDesc(Pageable pageable,Integer cid);
    public Page<Comments> findAllByStatusOrderByCreatedDesc(Pageable pageable,String status);
    public Page<Comments> findAllByTypeOrderByCreatedDesc(Pageable pageable,String type);
    public int countByStatus(String status);
    public void deleteByCoid(Integer coid);
}
