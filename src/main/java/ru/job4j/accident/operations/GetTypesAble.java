package ru.job4j.accident.operations;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.10.2020.
 */
public interface GetTypesAble<T> {
    T getType(int id);
}
