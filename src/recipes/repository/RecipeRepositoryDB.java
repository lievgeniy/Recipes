package recipes.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.util.List;

@Repository
@Qualifier("recipeDBRepository")
public interface RecipeRepositoryDB extends CrudRepository<Recipe, Long> {
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}
