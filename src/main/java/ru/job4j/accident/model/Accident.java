package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_accident_types")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "accidents_rules", joinColumns = {
            @JoinColumn(name = "accidents_id")},
            inverseJoinColumns = {@JoinColumn(name = "rules_id")})
    private Set<Rule> rules;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAccident status;

    public static Accident of(int id, String name, String text, String address, AccidentType type, Set<Rule> rules, StatusAccident status) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        accident.status = status;
        return accident;
    }
}
