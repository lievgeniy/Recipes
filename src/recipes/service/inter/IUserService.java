package recipes.service.inter;

import recipes.model.Recipe;
import recipes.model.User;

public interface IUserService {
    User getUser(long id);
    void createUser(User user);
    void deleteUser(long id);
    void updateUser(long id, User newUser);
}
