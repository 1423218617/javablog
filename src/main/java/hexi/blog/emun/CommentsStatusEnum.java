package hexi.blog.emun;

public enum CommentsStatusEnum {
    APPROVED(1,"comment","approved")
    ;
    private int code;
    private String type;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    CommentsStatusEnum(int code, String type, String status) {
        this.code = code;
        this.type = type;
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
}
