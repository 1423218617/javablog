package hexi.blog.model.vo;

import hexi.blog.emun.ResultEnum;
import lombok.Data;

@Data
public class ResultVo {
    private Boolean code;
    private String message;

    public ResultVo(Boolean code, String message) {
        this.code=code;
        this.message = message;
    }

}
