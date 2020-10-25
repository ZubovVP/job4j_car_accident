package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.operations.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Repository
public class AccidentMem implements Actions<Accident, Integer> {
    private final Random rd = new Random();
    private final Map<Integer, Accident> accidents = new HashMap<>();

    @Override
    public Accident add(Accident element) {
        if (element.getId() == 0) {
            int id = this.rd.nextInt(100000000);
            while (this.accidents.containsKey(id)) {
                id = this.rd.nextInt(100000000);
            }
            element.setId(id);
        }
        return this.accidents.put(element.getId(), element);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    @Override
    public Map<Integer, Accident> getAllElements() {
        return this.accidents;
    }

    @Override
    public Accident findById(int id) {
        return this.accidents.containsKey(id) ? this.accidents.get(id) : null;
    }
}
