package recipes.repository.Interface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.model.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
