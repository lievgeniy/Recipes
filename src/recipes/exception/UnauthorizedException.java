package recipes.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String cause) {
        super(cause);
    }
}
