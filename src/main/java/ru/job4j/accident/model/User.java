package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enable")
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    public static User of(int id, String username, String password, boolean enabled, Authority authority) {
        User user = new User();
        user.id = id;
        user.username = username;
        user.password = password;
        user.enabled = enabled;
        user.authority = authority;
        return user;
    }


}
