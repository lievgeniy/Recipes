package recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import recipes.repository.Interface.IUserRepository;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = IUserRepository.class)
public class RecipesApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }
}
