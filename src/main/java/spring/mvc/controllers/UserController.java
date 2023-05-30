package spring.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.mvc.models.User;
import spring.mvc.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.findAll());
        return "index";
    }

    @RequestMapping("/{id}")
    public String find_by_id(@PathVariable("id") int id,  Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "show";
    }

    @RequestMapping("/new")
    public String new_person(@ModelAttribute("new_user") User user) {
        return "new";
    }

    @PostMapping()
    public String create_person(@ModelAttribute("new_user") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.savePerson(user);
        return "redirect:/users";
    }

    @GetMapping("{id}/edit")
    public String edit_person(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.updatePerson(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deletePerson(id);
        return "redirect:/users";
    }
}

