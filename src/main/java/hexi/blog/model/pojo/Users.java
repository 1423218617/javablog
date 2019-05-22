package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_users")
public class Users {
    @Id
    @GeneratedValue
    private Integer uid;
    private String username;
    private String password;

}
