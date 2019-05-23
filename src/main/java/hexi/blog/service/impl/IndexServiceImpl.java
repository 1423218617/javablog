package hexi.blog.service.impl;

import hexi.blog.dao.CommentsDao;
import hexi.blog.dao.ContentsDao;
import hexi.blog.dao.MetasDao;
import hexi.blog.model.pojo.Comments;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.vo.StatisticalVo;
import hexi.blog.service.IndexService;
import hexi.blog.utils.UserCommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private ContentsDao contentsDao;

    @Autowired
    private MetasDao metasDao;

    @Override
    public StatisticalVo getStatistical() {
        commentsDao.findAllByStatusOrderByCreatedDesc(new PageRequest(0,5),"approved").getContent().forEach(comments -> System.out.println("  "+ UserCommonMethod.fmtdate(comments.getCreated())));
        System.out.println(commentsDao.countByStatus("approved"));
        return null;
    }

    @Override
    public Page<Comments> getRecentComments(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Contents> getRecentContents(Pageable pageable) {
        return null;
    }
}
