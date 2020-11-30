package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2020.
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "authority")
    private String authority;

    public static Authority of(int id, String authority) {
        Authority auth = new Authority();
        auth.id = id;
        auth.authority = authority;
        return auth;
    }
}
