package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.exception.BadParametersException;
import recipes.exception.ItemNotFoundException;
import recipes.model.User;
import recipes.repository.Interface.IUserRepository;
import recipes.service.inter.IUserService;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id: " + id));
    }

    @Override
    public void createUser(User user) {
        if (this.userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new BadParametersException("User already exists");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setRoles("ROLE_USER");
        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id: " + id));
        this.userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, User newUser) {
        User oldUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id: " + id));
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        this.userRepository.save(oldUser);
    }
}
