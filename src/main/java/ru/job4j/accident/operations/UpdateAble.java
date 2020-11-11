package ru.job4j.accident.operations;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.11.2020.
 */
public interface UpdateAble<E> {

    /**
     * Update an element.
     *
     * @param element - elemnt.
     * @return - element.
     */
    E update(E element);

}
