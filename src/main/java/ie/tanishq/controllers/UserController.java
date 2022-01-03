package ie.tanishq.controllers;

import ie.tanishq.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/showall")
    public String showAllUsers(Model model)   {
        model.addAttribute("users", userService.getAllUsers());
        return "user/showall";
    }

}
