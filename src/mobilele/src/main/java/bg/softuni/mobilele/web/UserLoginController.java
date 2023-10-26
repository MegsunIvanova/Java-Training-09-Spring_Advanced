package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final CurrentUser currentUser;


    public UserLoginController(UserService userService,
                               CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userModel")
    public UserLoginDTO initUserModel() {
        //TODO: how to return UserLoginDTO with empty fields
        return new UserLoginDTO("", "");
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {

        boolean isLogged = this.userService.loginUser(userLoginDTO);

        return isLogged ? "redirect:/" : "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logoutUser();
        return "redirect:/";
    }


}
