package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.operations.Actions;

import java.util.List;
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
public class AccidentService implements Actions<Accident, Integer, AccidentType, Rule> {
    @Autowired
    private Actions accidentRepository;

    /**
     * Check accident and redirect on accidentRepository.
     *
     * @param element - Accident.
     * @return - Accident with id.
     */
    @Override
    public Accident add(Accident element) {
        Accident result;
        if (!isNullOrEmpty(element.getName()) && !isNullOrEmpty(element.getAddress()) && !isNullOrEmpty(element.getText()) && element.getType() != null && element.getRules() != null) {
            result = (Accident) this.accidentRepository.add(element);
        } else {
            throw new NullPointerException("Name, address, text, type, rule is null");
        }
        return result;
    }

    /**
     * Delete accident from DB.
     *
     * @param id - id of Accident.
     * @return - result.
     */
    @Override
    public boolean delete(int id) {
        return this.accidentRepository.delete(id);
    }


    /**
     * Return all Accident.
     *
     * @return Map of Accidents.
     */
    @Override
    public Map<Integer, Accident> getAllElements() {
        return this.accidentRepository.getAllElements();
    }

    /**
     * Check id and redirect on accidentRepository.
     *
     * @param id - id of an element.
     * @return Accident.
     */
    @Override
    public Accident findById(int id) {
        return id != 0 ? (Accident) this.accidentRepository.findById(id) : null;
    }

    /**
     * Check Accident of type and redirect on accidentRepository.
     * @param type - AccidentType.
     */
    @Override
    public void addType(AccidentType type) {
        if (type != null && type.getId() > 0 && !isNullOrEmpty(type.getName())) {
            this.accidentRepository.addType(type);
        }
    }

    /**
     * Find AccidentType use id.
     *
     * @param id - id of Accident of type.
     * @return - AccidentType.
     */
    @Override
    public AccidentType getType(int id) {
        return (AccidentType) this.accidentRepository.getType(id);
    }

    /**
     * Get all types.
     * @return list of types.
     */
    @Override
    public List<AccidentType> getTypes() {
        return this.accidentRepository.getTypes();
    }

    /**
     * Return all rules.
     *
     * @return - list of rules.
     */
    @Override
    public List<Rule> getRules() {
        return this.accidentRepository.getRules();
    }
}
