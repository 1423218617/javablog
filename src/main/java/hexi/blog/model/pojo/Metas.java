package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_metas")
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    private String name;

    private String slug;

    private String type;

    private String description;

    private Integer sort;

    private Integer parent;
}
