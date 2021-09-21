package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.User;
import recipes.service.inter.IUserService;
import recipes.service.inter.RecipeServiceInterface;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final IUserService userService;

    @Autowired
    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid User user) {
        this.userService.createUser(user);
    }
}
