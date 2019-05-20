package hexi.blog.model.vo;

import hexi.blog.emun.ResultEnum;
import lombok.Data;

@Data
public class ResultVo {
    private Integer code;
    private String message;

    public ResultVo(ResultEnum resultEnum) {
        this.code=resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

}
