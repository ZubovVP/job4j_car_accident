package ru.job4j.accident.operations;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.10.2020.
 */
public interface GetAllTypes<T> {
    List<T> getTypes();
}
