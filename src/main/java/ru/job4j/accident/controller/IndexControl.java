package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import java.util.HashMap;
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
    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> map = new HashMap<>();
        Accident accident = new Accident();
        accident.setId(1);
        accident.setName("name");
        accident.setAddress("adress");
        accident.setText("test");
        map.put(accident.getId(), accident);

        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setName("name2");
        accident2.setAddress("adress2");
        accident2.setText("test2");
        map.put(accident2.getId(), accident2);

        Accident accident3 = new Accident();
        accident3.setId(3);
        accident3.setName("name3");
        accident3.setAddress("adress3");
        accident3.setText("test3");
        map.put(accident3.getId(), accident3);

        model.addAttribute("accidents", map);
        return "index";
    }
}
