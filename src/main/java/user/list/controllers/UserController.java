package user.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import user.list.entity.UserEntity;
import user.list.services.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = {"list", "/list/{pageNumber}"}, method = RequestMethod.GET)
    public String list(@PathVariable Optional<Integer> pageNumber, Model model) {
        int page = 1;
        if (pageNumber.isPresent()) {
            page = pageNumber.get();
        }
        Page<UserEntity> pageUserEntity = userService.list(page);

        int current = pageUserEntity.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, pageUserEntity.getTotalPages());

        model.addAttribute("userList", pageUserEntity);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "user/list";
    }

}