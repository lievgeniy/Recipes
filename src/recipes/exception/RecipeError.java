package recipes.exception;

public class RecipeError {
    private final String error;

    public RecipeError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}