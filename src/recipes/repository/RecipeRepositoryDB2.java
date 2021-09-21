package recipes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;
import recipes.repository.Interface.RecipeRepositoryInterface;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Qualifier("recipeDBRepository2")
public class RecipeRepositoryDB2 implements RecipeRepositoryInterface {
    private final RecipeRepositoryDB recipeRepository;

    @Autowired
    public RecipeRepositoryDB2(@Qualifier("recipeDBRepository") RecipeRepositoryDB recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipe(long id) {
        return this.recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Long addRecipe(Recipe recipe) {
        return this.recipeRepository.save(recipe).getId();
    }

    @Override
    public void deleteRecipe(long id) {
        this.recipeRepository.deleteById(id);
    }

    @Override
    public void updateRecipe(long id, Recipe newRecipe) {
        var oldRecipe = this.recipeRepository.findById(id);
        if (oldRecipe.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        oldRecipe.get().setName(newRecipe.getName());
        oldRecipe.get().setDescription(newRecipe.getDescription());
        oldRecipe.get().setIngredients(newRecipe.getIngredients());
        oldRecipe.get().setDirections(newRecipe.getDirections());
        oldRecipe.get().setCategory(newRecipe.getCategory());
        oldRecipe.get().setDate(LocalDateTime.now());
        this.recipeRepository.save(oldRecipe.get());
    }

    public List<Recipe> searchRecipeByName(String name) {
        return this.recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    @Override
    public List<Recipe> searchRecipeByCategory(String category) {
        return this.recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }
}
