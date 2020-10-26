package ru.job4j.accident.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Data
@EqualsAndHashCode
public class Accident {
        private int id;
        private String name;
        private String text;
        private String address;
        private AccidentType type;
}
