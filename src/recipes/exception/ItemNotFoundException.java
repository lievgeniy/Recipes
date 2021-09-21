package recipes.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String cause) {
        super(cause);
    }
}
