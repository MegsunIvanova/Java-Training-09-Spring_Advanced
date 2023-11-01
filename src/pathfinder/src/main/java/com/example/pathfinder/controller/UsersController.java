package com.example.pathfinder.controller;

import com.example.pathfinder.exeptions.UserNotFoundException;
import com.example.pathfinder.model.dto.binding.UserLoginDTO;
import com.example.pathfinder.model.dto.binding.UserRegistrationDTO;
import com.example.pathfinder.model.dto.view.UserProfileViewDTO;
import com.example.pathfinder.service.AuthService;
import com.example.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public UsersController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDTO loginDTO) {
        authService.login(loginDTO);
        return new ModelAndView("redirect:/");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleLoginError(UserNotFoundException e,
                                         RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("badCredentials", true);
        System.out.println(e.getMessage());
        return new ModelAndView("redirect:login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Valid UserRegistrationDTO userRegistrationDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDTO",
                    bindingResult);

            return new ModelAndView("redirect:register");
        }

        this.authService.register(userRegistrationDTO);

        return new ModelAndView("redirect:login");
    }

    //TODO: PostMapping for logout()
    @GetMapping("/logout")
    public ModelAndView logout() {
        this.authService.logout();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        UserProfileViewDTO userProfileViewDTO = userService.getUserProfile();

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userProfileViewDTO", userProfileViewDTO);

        return modelAndView;
    }

}
