package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_comments")
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //评论id
    private Integer coid;
    //文章id
    private Integer cid;
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
