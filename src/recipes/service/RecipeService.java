package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import recipes.constants.Constants;
import recipes.exception.ItemNotFoundException;
import recipes.exception.RecipeNotFoundException;
import recipes.exception.UnauthorizedException;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.repository.Interface.IUserRepository;
import recipes.repository.Interface.RecipeRepositoryInterface;
import recipes.security.Interface.IAuthenticationFacade;
import recipes.service.inter.RecipeServiceInterface;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService implements RecipeServiceInterface {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private IUserRepository userRepository;

    private final RecipeRepositoryInterface recipeRepository;

    @Autowired
    public RecipeService(@Qualifier("recipeDBRepository2") RecipeRepositoryInterface recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    /*
    private final RecipeRepositoryDB recipeRepository;
    @Autowired
    public RecipeService(@Qualifier("recipeDBRepository") RecipeRepositoryDB recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

     */

    @Override
    public Recipe getRecipe(long id) {
        Recipe recipe = this.recipeRepository.getRecipe(id);
        if (recipe == null) {
            throw new RecipeNotFoundException("Recipe not found with id : " + id);
        }
        return recipe;
    }

    @Override
    public Long createRecipe(Recipe recipe) {
        String userName = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findUserByEmail(userName).orElseThrow(() -> new ItemNotFoundException("User not found: " + userName));
        recipe.setUser(user);
        recipe.setDate(LocalDateTime.now());
        return this.recipeRepository.addRecipe(recipe);
    }

    @Override
    public void deleteRecipe(long id) {
        try {
            Recipe recipe = this.getRecipe(id);
            String userName = authenticationFacade.getAuthentication().getName();
            if (!recipe.getUser().getEmail().equals(userName)) {
                throw new UnauthorizedException("Action forbidden! Your are not the author of the recipe");
            }

            this.recipeRepository.deleteRecipe(recipe.getId());

        } catch (EmptyResultDataAccessException emptyException) {
            throw new RecipeNotFoundException("Recipe not found with id : " + id);
        }
    }

    @Override
    public void updateRecipe(long id, Recipe newRecipe) {
        try {
            Recipe recipe = this.getRecipe(id);
            String userName = authenticationFacade.getAuthentication().getName();
            if (!recipe.getUser().getEmail().equals(userName)) {
                throw new UnauthorizedException("Action forbidden! Your are not the author of the recipe");
            }

            this.recipeRepository.updateRecipe(id, newRecipe);
        } catch (EmptyResultDataAccessException emptyException) {
            throw new RecipeNotFoundException("Recipe not found with id : " + id);
        }

    }

    @Override
    public List<Recipe> searchByNameOrCategory(String param, String searchValue) {
        if (Constants.SEARCH_PARAM_NAME.equals(param)) {
            return this.recipeRepository.searchRecipeByName(searchValue);
        }
        return this.recipeRepository.searchRecipeByCategory(searchValue);
    }

}
