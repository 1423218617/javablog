package hexi.blog.emun;

public enum ResultEnum {
    SUCCESS(1,"操作成功"),
    FAIL(2,"操作失败");
    private Integer code;
    private String message;
    ResultEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
