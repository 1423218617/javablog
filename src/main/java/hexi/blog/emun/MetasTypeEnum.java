package hexi.blog.emun;

public enum MetasTypeEnum {
    LINK(1,"link"),
    CATEGORY(1,"category")
    ;
    private int code;
    private String type;

    MetasTypeEnum(int code, String type) {
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
