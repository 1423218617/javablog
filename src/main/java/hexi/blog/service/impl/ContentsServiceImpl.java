package hexi.blog.service.impl;

import hexi.blog.dao.ContentsDao;
import hexi.blog.model.pojo.Contents;
import hexi.blog.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentsServiceImpl implements ContentsService {


    @Autowired
    private ContentsDao contentsDao;


    @Override
    public Page<Contents> contentsPage(Pageable pageable) {
        return contentsDao.findAll(pageable);
    }

    @Override
    public Contents findContentsByCid(Integer cid) {
        return contentsDao.getOne(cid);
    }

    @Override
    public Contents findContentsBySlug(String slug) {
        return contentsDao.findBySlug(slug);
    }

    @Override
    public List<Contents> findAll() {
        return contentsDao.findAll();
    }
}
