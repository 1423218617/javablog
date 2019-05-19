package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_relationships")
public class Relationships {
    @Id
    private Integer cid;

    private Integer mid;
}
