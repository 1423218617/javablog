package hexi.blog.emun;

public enum ContentsStatusEnum {
    PAGE(1,"page","publish"),
    POST(2,"post","publish"),
    DRAFT(3,"post","draft"),;
    private int code;
    private String type;
    private String status;
    ContentsStatusEnum(int  code,String type,String status){
        this.code=code;
        this.type=type;
        this.status=status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
