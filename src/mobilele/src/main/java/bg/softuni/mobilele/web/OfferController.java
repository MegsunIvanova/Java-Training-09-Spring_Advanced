package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.CreateOfferDTO;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.impl.OfferServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferServiceImpl offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("creatOfferDTO")) {
            model.addAttribute("creatOfferDTO", CreateOfferDTO.empty());
        }

        model.addAttribute("brands", this.brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid CreateOfferDTO creatOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("creatOfferDTO", creatOfferDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.creatOfferDTO",
                    bindingResult);

            return "redirect:/offer/add";
        }

        UUID newOfferUUID = this.offerService.createOffer(creatOfferDTO);

        return "redirect:/offer/" + newOfferUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid) {
        return "details";
    }
}

