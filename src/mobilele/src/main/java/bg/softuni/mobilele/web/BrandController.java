package bg.softuni.mobilele.web;

import bg.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {
    @GetMapping("/brands")
    public String allBrands() {
        return "brands";
    }
}