package spring.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.mvc.models.Person;
import spring.mvc.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PeopleService peopleService;

    @Autowired
    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @RequestMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "index";
    }

    @RequestMapping("/{id}")
    public String find_by_id(@PathVariable("id") int id,  Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "show";
    }

    @RequestMapping("/new")
    public String new_person(@ModelAttribute("new_person") Person person) {
        return "new";
    }

    @PostMapping()
    public String create_person(@ModelAttribute("new_person") @Valid Person person,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        peopleService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit_person(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        peopleService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }
}

