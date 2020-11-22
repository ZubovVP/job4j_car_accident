package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.10.2020.
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "rules")
public class Rule implements Comparable<Rule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }

    @Override
    public int compareTo(Rule o) {
        return this.getId() - o.getId();
    }
}
