package recipes.repository.Interface;

import recipes.model.Recipe;

import java.util.List;

public interface RecipeRepositoryInterface {
    Recipe getRecipe(long id);
    Long addRecipe(Recipe recipe);
    void deleteRecipe(long id);
    void updateRecipe(long id, Recipe newRecipe);
    List<Recipe> searchRecipeByName(String name);
    List<Recipe> searchRecipeByCategory(String category);
}
