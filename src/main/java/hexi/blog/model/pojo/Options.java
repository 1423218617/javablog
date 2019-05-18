package hexi.blog.model.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_options")
@Data
public class Options {

    @Id
    private String name;

    private String value;

    private String description;
}
