package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_contents")
public class Contents implements Serializable {


    @Id
    @GeneratedValue
    //文章id
    @Column(name = "cid")
    private Integer cid;

    //文章标题
    private String title;


    private String slug;

    //创建时间
    private long created;

    //修改时间
    private long modified;

    //文章内容
    private String content;

    //作者id
    private Integer authorId;

    //状态
    private String Status;

    //标签
    private String tags;

    //分类
    private String categories;

    //点击量
    private Integer hits;

    //评论数
    private Integer commentsNum;

    private Integer allowComment;

    private Integer allowPing;

    private Integer allow_feed;


}
