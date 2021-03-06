package hexi.blog.service.impl;

import hexi.blog.dao.CommentsDao;
import hexi.blog.model.pojo.Comments;
import hexi.blog.service.CommentsService;
import hexi.blog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;
    @Override
    public Page<Comments> commentsPageByCid(Pageable pageable, Integer cid) {
        return commentsDao.findAllByCidOrderByCreatedDesc(pageable,cid);
    }

    @Override
    public Comments save(Comments comments) {
        comments.setCreated(DateUtil.getUnixTime());
        return commentsDao.save(comments);
    }
}
