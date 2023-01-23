package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Accident;
import ru.job4j.service.AccidentDataService;
import ru.job4j.service.AccidentTypeDataService;
import ru.job4j.service.RuleDataService;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentDataService accidents;
    private final AccidentTypeDataService typeService;
    private final RuleDataService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        takeAction(accident, act -> accidents.create(accident));
        String[] ids = req.getParameterValues("rIds");
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        Optional<Accident> optAccident = accidents.findById(id);
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