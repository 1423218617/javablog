package hexi.blog.model.pojo;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_contents")
public class Contents implements Serializable,Comparable<Contents> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String status;

    //标签
    private String tags;

    //分类
    private String categories;

    //点击量
    private Integer hits;

    //评论数
    private Integer commentsNum;

    private Boolean allowComment;

    private Boolean allowPing;

    private Boolean allowFeed;

    private String type;


    @Override
    public int compareTo(Contents contents) {
        return contents.getHits()-this.getHits();
    }
}
