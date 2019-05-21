package hexi.blog.exception;

public class IllegalCommentException extends RuntimeException {
    private String message;

    public IllegalCommentException(String message) {
        super(message);
        this.message = message;
    }
}
