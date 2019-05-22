package hexi.blog.service;

import hexi.blog.model.pojo.Users;

public interface UsersService {
    public Users findByUsernameAndPassword(String username,String password);
}
