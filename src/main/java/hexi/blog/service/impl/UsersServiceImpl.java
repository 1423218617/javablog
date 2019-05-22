package hexi.blog.service.impl;

import hexi.blog.dao.UsersDao;
import hexi.blog.model.pojo.Users;
import hexi.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;
    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        return usersDao.findByUsernameAndPassword(username,password);
    }
}
