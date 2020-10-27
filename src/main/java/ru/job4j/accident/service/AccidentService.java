package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.operations.Actions;

import java.util.Map;

import static joptsimple.internal.Strings.isNullOrEmpty;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Service
public class AccidentService implements Actions<Accident, Integer> {
    @Autowired
    private AccidentMem accidentMem;

    @Override
    public Accident add(Accident element) {
        Accident result;
        if (!isNullOrEmpty(element.getName()) && !isNullOrEmpty(element.getAddress()) && !isNullOrEmpty(element.getText()) && element.getType() != null && element.getRules() != null) {
            result = this.accidentMem.add(element);
        } else {
            throw new NullPointerException("Name, address, text, type, rule is null");
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    @Override
    public Map<Integer, Accident> getAllElements() {
        return this.accidentMem.getAllElements();
    }

    @Override
    public Accident findById(int id) {
        return id != 0 ? this.accidentMem.findById(id) : null;
    }
}
