package hexi.blog.emun;

public enum CommentsStatusEnum {
    APPROVED(1,"approved")
    ;
    private int code;
    private String type;

    CommentsStatusEnum(int code, String type) {
        this.code = code;
        this.type = type;
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
}
