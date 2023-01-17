package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Accident;
import ru.job4j.service.AccidentService;
import ru.job4j.service.AccidentTypeService;
import ru.job4j.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidentTypeService typeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        takeAction(accident, act -> accidents.put(accident));
        String[] ids = req.getParameterValues("rIds");
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        Optional<Accident> optAccident = Optional.ofNullable(accidents.findById(id));
        if (optAccident.isEmpty()) {
            return "404";
        }
        model.addAttribute("accident", optAccident.get());
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", typeService.getTypes());
        return "formUpdateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        takeAction(accident, act -> accidents.update(accident));
        return "redirect:/index";

    }

    private void takeAction(Accident accident, Consumer<Accident> action) {
        accident.setType(typeService.get(accident.getType().getId())
                .orElseThrow(NoSuchElementException::new));
        ruleService.setRules(accident);
        action.accept(accident);
    }
}