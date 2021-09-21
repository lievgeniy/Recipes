package recipes.exception;

public class BadParametersException  extends RuntimeException {
    public BadParametersException(String cause) {
        super(cause);
    }
}