package hexi.blog.service.impl;

import hexi.blog.dao.OptionsDao;
import hexi.blog.model.pojo.Options;
import hexi.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionsImpl implements OptionsService {
    @Autowired
    private OptionsDao optionsDao;

    @Override
    public List<Options> findAll() {
        return optionsDao.findAll();
    }
}
