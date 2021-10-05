package ru.job4j.accident.model;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $.
 * Date: 04.10.2021.
 */
public enum StatusAccident {
    Принята("Принята"), Отклонена("Отклонена"), Завершена("Завершена");
    private final String name;

    StatusAccident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
