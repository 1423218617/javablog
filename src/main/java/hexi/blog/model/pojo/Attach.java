package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_attach")
@Data
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fname;
    private String ftype;
    private String fkey;
    private Integer authorId;
    private Integer created;

}
