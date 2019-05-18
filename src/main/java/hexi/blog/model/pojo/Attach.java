package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_attach")
@Data
public class Attach {
    @Id
    @GeneratedValue
    private Integer id;
    private String fName;
    private String fType;
    private String fKey;
    private Integer authorId;
    private Integer created;

}
