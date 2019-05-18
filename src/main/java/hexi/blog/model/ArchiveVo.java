package hexi.blog.model;


import hexi.blog.model.pojo.Contents;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArchiveVo {
    private String date;

    private String count;

    private List<Contents> articles;

}
