package recipes.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String cause) {
        super(cause);
    }
}
