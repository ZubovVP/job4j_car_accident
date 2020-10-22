package ru.job4j.accident.operations;

import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.04.2020.
 */
public interface GetAllElementsAble<I, E> {

    /**
     * Return list.
     *
     * @return list.
     */
    Map<I, E> getAllElements();
}
