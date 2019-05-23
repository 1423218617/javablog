package hexi.blog.service;

import hexi.blog.model.pojo.Comments;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.vo.StatisticalVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IndexService {
    public StatisticalVo getStatistical();
    public Page<Comments> getRecentComments(Pageable pageable);
    public Page<Contents> getRecentContents(Pageable pageable);
}
