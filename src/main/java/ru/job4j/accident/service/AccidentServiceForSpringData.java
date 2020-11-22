package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.operations.Actions;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;


import java.util.*;

import static joptsimple.internal.Strings.isNullOrEmpty;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.11.2020.
 */
@Service
public class AccidentServiceForSpringData implements Actions<Accident, Integer, AccidentType, Rule> {
    @Autowired
    AccidentRepository ar;
    @Autowired
    AccidentTypeRepository at;
    @Autowired
    RuleRepository rr;


    @Override
    public Accident add(Accident element) {
        Accident result;
        if (!isNullOrEmpty(element.getName()) && !isNullOrEmpty(element.getAddress()) && !isNullOrEmpty(element.getText()) && element.getType() != null && element.getRules() != null) {
            result = this.ar.save(element);
        } else {
            throw new NullPointerException("Name, address, text, type, rule is null");
        }
        return result;
    }

    @Override
    public void addType(AccidentType type) {
        if (type != null && type.getId() > 0 && !isNullOrEmpty(type.getName())) {
            this.at.save(type);
        }
    }

    @Override
    public boolean delete(int id) {
        this.ar.delete(Accident.of(id, "", "", "", null, null));
        return true;
    }

    @Override
    public Accident findById(int id) {
        Iterator<Accident> resultItr = this.ar.findAllById(Arrays.asList(id)).iterator();
        return resultItr.hasNext() ? resultItr.next() : null;
    }

    @Override
    public Map<Integer, Accident> getAllElements() {
        Map<Integer, Accident> result = new HashMap<>();
        Iterator<Accident> it = this.ar.findAll().iterator();
        Accident accident;
        while (it.hasNext()) {
            accident = it.next();
            result.put(accident.getId(), accident);
        }
        return result;
    }

    @Override
    public List<Rule> getRules() {
        ArrayList<Rule> result = new ArrayList<>();
        this.rr.findAll().iterator().forEachRemaining(result::add);
        Collections.sort(result);
        return result;
    }

    @Override
    public List<AccidentType> getTypes() {
        ArrayList<AccidentType> result = new ArrayList<>();
        this.at.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public AccidentType getType(int id) {
        Iterator<AccidentType> resultItr = this.at.findAllById(Arrays.asList(id)).iterator();
        return resultItr.hasNext() ? resultItr.next() : null;
    }

    @Override
    public Accident update(Accident element) {
        return add(element);
    }
}
