package hexi.blog.service;

import hexi.blog.model.pojo.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentsService {
    public Page<Contents> contentsPage(Pageable pageable);
    public Contents findContentsByCid(Integer cid);
}
