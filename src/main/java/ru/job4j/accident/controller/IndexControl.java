package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Controller
public class IndexControl {
    @Autowired
    private AccidentService accidentService;


    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> result = accidentService.getAllElements();
        model.addAttribute("accidents", result);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", this.accidentService.getTypes());
        List<Rule> rules = accidentService.getRules();
        model.addAttribute("rules", rules);
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ruleResult = req.getParameterValues("rIdName")[0].split("_");
        Rule rule = Rule.of(Integer.parseInt(ruleResult[0]), ruleResult[1]);
        Set<Rule> rules = new HashSet<>();
        rules.add(rule);
        accident.setRules(rules);
        String[] typeResult = req.getParameterValues("tIdName")[0].split("_");
        accident.setType(AccidentType.of(Integer.parseInt(typeResult[0]), typeResult[1]));
        if (accident.getId() != 0) {
            accidentService.update(accident);
        } else {
            accidentService.add(accident);
        }
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", this.accidentService.getTypes());
        List<Rule> rules = accidentService.getRules();
        model.addAttribute("rules", rules);
        return "update";
    }
}
