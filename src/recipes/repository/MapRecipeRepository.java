package recipes.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;
import recipes.repository.Interface.RecipeRepositoryInterface;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Repository
@Qualifier("recipeMapRepository")
public class MapRecipeRepository implements RecipeRepositoryInterface {
    private ConcurrentMap<Long, Recipe> recipes;

    public MapRecipeRepository() {
        this.recipes = new ConcurrentHashMap<>();
    }

    @Override
    public Recipe getRecipe(long id) {
        return this.recipes.get(id);
    }

    @Override
    public Long addRecipe(Recipe recipe) {
        Long id = this.recipes.size() + 1L;
        this.recipes.put(id, recipe);
        return id;
    }

    @Override
    public void deleteRecipe(long id) {
        this.recipes.remove(id);
    }

    @Override
    public void updateRecipe(long id, Recipe newRecipe) {
        this.recipes.put(id, newRecipe);
    }

    @Override
    public List<Recipe> searchRecipeByName(String name) {
        return this.recipes.values().stream().filter(recipe -> recipe.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> searchRecipeByCategory(String category) {
        return this.recipes.values().stream().filter(recipe -> recipe.getCategory().contains(category)).collect(Collectors.toList());
    }
}
