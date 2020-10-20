package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Map;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Controller
public class IndexControl {
    private static final AccidentMem ACCIDENT_MEM = new AccidentMem();

    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> result = ACCIDENT_MEM.getAccidents();
        model.addAttribute("accidents", result);
        return "index";
    }
}
