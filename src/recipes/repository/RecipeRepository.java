package recipes.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;
import recipes.repository.Interface.RecipeRepositoryInterface;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
@Qualifier("recipeSingleRepository")
public class RecipeRepository implements RecipeRepositoryInterface {
    private Recipe recipe;

    @Override
    public Recipe getRecipe(long id) {
        return this.recipe;
    }

    @Override
    public Long addRecipe(Recipe recipe) {
        this.setRecipe(recipe);
        return 1L;
    }

    @Override
    public void deleteRecipe(long id) {
        this.setRecipe(null);
    }

    @Override
    public void updateRecipe(long id, Recipe newRecipe) {
        this.setRecipe(newRecipe);
    }

    @Override
    public List<Recipe> searchRecipeByName(String name) {
        if (this.recipe.getName().contains(name)) {
            return List.of(this.recipe);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Recipe> searchRecipeByCategory(String category) {
        if (this.recipe.getCategory().contains(category)) {
            return List.of(this.recipe);
        }
        return Collections.emptyList();
    }
}
