package ru.job4j.accident.operations;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.04.2020.
 */
public interface Actions<E, I, T, R> extends AddAble<E>, GetAllElementsAble<I, E>, FindById<E>, DeleteAble, GetTypesAble<T>, AddTypeAble<T>, GetAllTypes<T>, GetAllRulesAble<R> {

}
