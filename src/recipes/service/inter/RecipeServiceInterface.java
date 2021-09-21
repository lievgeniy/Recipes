package recipes.service.inter;

import recipes.model.Recipe;

import java.util.List;

public interface RecipeServiceInterface {
    Recipe getRecipe(long id);
    Long createRecipe(Recipe recipe);
    void deleteRecipe(long id);
    void updateRecipe(long id, Recipe newRecipe);
    List<Recipe> searchByNameOrCategory(String param, String searchValue);
}
