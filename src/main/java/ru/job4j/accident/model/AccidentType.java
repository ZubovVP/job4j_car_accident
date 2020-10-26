package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.10.2020.
 */
@Data
@EqualsAndHashCode
public class AccidentType {
    private String name;
    private int id;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }
}
