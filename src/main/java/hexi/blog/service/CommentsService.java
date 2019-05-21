package hexi.blog.service;

import hexi.blog.model.pojo.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentsService {
    public Page<Comments> commentsPageByCid(Pageable pageable, Integer cid);
    public Comments save(Comments comments);
}
