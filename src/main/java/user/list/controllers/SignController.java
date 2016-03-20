package user.list.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign")
public class SignController {

    @RequestMapping("/in")
    public String in() {
        return "index";
    }

    @RequestMapping("/out")
    public String out() {
        return "";
    }

    @RequestMapping("/up")
    public String up() throws Exception {
        if (true)
            throw new Exception();
        return "";
    }

}
