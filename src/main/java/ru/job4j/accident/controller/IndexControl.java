package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentServiceForSpringData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private AccidentServiceForSpringData accidentService;


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

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
