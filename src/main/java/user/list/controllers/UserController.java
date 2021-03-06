package user.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import user.list.entity.UserDtoItem;
import user.list.entity.UserEntity;
import user.list.services.UserService;

import java.util.List;
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
            page = pageNumber.get() + 1;
        }
        Page<UserEntity> pageUserEntity = userService.list(page);

        model.addAttribute("userList", pageUserEntity);
        model.addAttribute("prevPage", pageUserEntity.getNumber() - 1);
        model.addAttribute("nextPage", pageUserEntity.getNumber() + 1);
        model.addAttribute("firstDisable", pageUserEntity.getNumber() == 0);
        model.addAttribute("lastDisable", pageUserEntity.getNumber() == pageUserEntity.getTotalPages() - 1);

        return "user/list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(Model model) {
        return "user/find";
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public List<UserEntity> findPost(@RequestBody UserDtoItem userDtoItem) {
        return userService.find(userDtoItem);
    }

}