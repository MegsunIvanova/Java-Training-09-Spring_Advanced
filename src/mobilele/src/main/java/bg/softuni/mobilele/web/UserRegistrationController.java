package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.ReCaptchaResponseDTO;
import bg.softuni.mobilele.model.dto.UserRegisterDTO;
import bg.softuni.mobilele.service.ReCaptchaService;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    public UserRegistrationController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }

    @ModelAttribute("userModel")
    public void initUserModel(Model model) {
        model.addAttribute("userModel", new UserRegisterDTO());
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("g-recaptcha-response") String reCaptchaResponse,
                           @Valid UserRegisterDTO userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        Boolean isNotBot = reCaptchaService.verify(reCaptchaResponse)
                .map(ReCaptchaResponseDTO::isSuccess)
                .orElse(false);

        if(!isNotBot) {
            return  "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(userModel);

        return "redirect:/users/login";
    }
}
