package user.list.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import user.list.entity.UserEntity;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign")
public class SignController {

    @RequestMapping("/in")
    public String in() {
        return "sign/in";
    }

    @RequestMapping("/out")
    public String out() {
        return "";
    }

    @RequestMapping(value = "/up", method = RequestMethod.GET)
    public String up(UserEntity userEntity) throws Exception {
        return "sign/up";
    }

    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public String upPost(@Valid UserEntity userEntity, BindingResult bindingResult) throws Exception {
        //@ModelAttribute
        if (bindingResult.hasErrors()) {
            return "sign/up";
        }
        return "sign/up";
    }

}
