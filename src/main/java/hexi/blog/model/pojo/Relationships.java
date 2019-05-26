package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_relationships")
@IdClass(RelationshipsKey.class)
public class Relationships {
    @Id
    @Column(name = "cid")
    private Integer cid;
    @Id
    @Column(name = "mid")
    private Integer mid;

}
@Embeddable
class RelationshipsKey implements Serializable {
    private Integer cid;

    private Integer mid;
}
