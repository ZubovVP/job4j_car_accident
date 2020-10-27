package ru.job4j.accident.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.10.2020.
 */
@Data
@EqualsAndHashCode
public class Rule {
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }
}
