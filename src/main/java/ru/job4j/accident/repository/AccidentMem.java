package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.operations.Actions;

import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.10.2020.
 */
@Repository
public class AccidentMem implements Actions<Accident, Integer, AccidentType, Rule> {
    private final Random rd = new Random();
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final List<Rule> rules = new ArrayList<>();

    public AccidentMem() {
        this.addType(AccidentType.of(1, "Две машины"));
        this.addType(AccidentType.of(2, "Машина и человек"));
        this.addType(AccidentType.of(3, "Машина и велосипед"));
    }

    @Override
    public Accident add(Accident element) {
        int id = this.rd.nextInt(100000000);
        while (this.accidents.containsKey(id)) {
            id = this.rd.nextInt(100000000);
        }
        element.setId(id);
        element.setType(getType(element.getType().getId()));
        return this.accidents.put(element.getId(), element);
    }

    @Override
    public boolean delete(int id) {
        this.accidents.remove(id);
        return true;
    }


    @Override
    public Map<Integer, Accident> getAllElements() {
        return this.accidents;
    }

    @Override
    public Accident findById(int id) {
        return this.accidents.containsKey(id) ? this.accidents.get(id) : null;
    }

    @Override
    public void addType(AccidentType type) {
        if (!this.types.containsKey(type.getId())) {
            this.types.put(type.getId(), type);
        }
    }

    @Override
    public AccidentType getType(int id) {
        return this.types.get(id);
    }

    @Override
    public List<AccidentType> getTypes() {
        return new ArrayList<>(this.types.values());
    }

    @Override
    public List<Rule> getRules() {
        return this.rules;
    }

    @Override
    public Accident update(Accident element) {
        return this.accidents.replace(element.getId(), element);
    }
}
