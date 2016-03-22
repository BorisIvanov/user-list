package user.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import user.list.entity.UserEntity;
import user.list.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/sign")
public class SignController {

    private final UserRepository userRepository;

    @Autowired
    public SignController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public String up(UserEntity userEntity) throws Exception {
        return "sign/up";
    }

    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public String upPost(@Valid UserEntity userEntity, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception {
        if (bindingResult.hasErrors()) {
            return "sign/up";
        }
        if (userRepository.readByLogin(userEntity.getLogin()) != null) {
            bindingResult.addError(new ObjectError("UserEntity", ""));
            return "sign/up";
        }
        userRepository.save(userEntity);
        return "sign/up";
    }

}
