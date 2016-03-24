package user.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import user.list.entity.UserDtoItem;
import user.list.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign")
public class SignController {

    private final UserService userService;

    @Autowired
    public SignController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/in")
    public String in() {
        return "sign/in";
    }

    @RequestMapping("/out")
    public String out() {
        return "";
    }

    @RequestMapping(value = "/up", method = RequestMethod.GET)
    public String up(UserDtoItem userDtoItem) throws Exception {
        return "sign/up";
    }

    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public String upPost(@Valid UserDtoItem userDtoItem, BindingResult bindingResult, RedirectAttributes model)
            throws Exception {
        if (bindingResult.hasErrors()) {
            return "sign/up";
        }
        if (userService.readByLogin(userDtoItem.getLogin()) != null) {
            FieldError fieldError = new FieldError("userEntity", "login", userDtoItem.getLogin(), false,
                    new String[]{"Exist.userEntity.login"}, null, "");
            bindingResult.addError(fieldError);
            return "sign/up";
        }
        userService.save(userDtoItem);

        model.addFlashAttribute("info", "sign.up.success");
        return "redirect:/";
    }

}
