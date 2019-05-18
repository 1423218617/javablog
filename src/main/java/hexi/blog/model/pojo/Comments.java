package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_comments")
@Data
public class Comments {

    @Id
    @GeneratedValue
    //评论id
    private Integer coId;
    //文章id
    private Integer cId;
    //作者
    private String author;
    //作者id
    private String authorId;
    private String ownerId;
    private String mail;
    private String url;
    private String ip;
    private String agent;
    private String content;
    private String type;
    private String status;
    private Integer parent;
    private Integer created;
}
