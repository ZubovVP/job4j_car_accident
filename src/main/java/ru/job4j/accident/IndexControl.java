package ru.job4j.accident;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = Arrays.asList("first", "second", "third");
        model.addAttribute("elements", list);
        return "index";
    }
}
