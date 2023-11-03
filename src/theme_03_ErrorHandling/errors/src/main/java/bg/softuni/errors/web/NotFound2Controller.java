package bg.softuni.errors.web;

import bg.softuni.errors.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotFound2Controller {
    @GetMapping("not-found2/{id}")
    public String notFound2(@PathVariable("id") String id) {
        throw new ObjectNotFoundException("Something went wrong", id);
    }


}
