package hexi.blog.dao;

import hexi.blog.model.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<Users,Integer> {
    public Users findByUsernameAndPassword(String username,String password);
}
