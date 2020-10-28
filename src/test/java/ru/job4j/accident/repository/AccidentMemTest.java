package ru.job4j.accident.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.10.2020.
 */
public class AccidentMemTest {
    private AccidentMem accidentMem = new AccidentMem();
    private Accident accident;

    @Before
    public void start() {
        this.accident = new Accident();
        this.accident.setId(1);
        this.accident.setName("name");
        this.accident.setAddress("address");
        this.accident.setText("text");
        this.accident.setType(AccidentType.of(1, "TestType"));
        Set<Rule> rules = new HashSet<>();
        rules.add(Rule.of(1, "TestRule"));
        this.accident.setRules(rules);
    }

    @Test
    public void add() {
        assertThat(this.accidentMem.getAllElements().size(), is(0));
        this.accidentMem.add(this.accident);
        assertThat(this.accidentMem.getAllElements().size(), is(1));
        assertThat(this.accidentMem.getAllElements().get(this.accident.getId()), is(this.accident));
    }

    @Test
    public void getAllElements() {
    }

    @Test
    public void findById() {
        assertNull(this.accidentMem.findById(this.accident.getId()));
        this.accidentMem.add(this.accident);
        assertThat(this.accidentMem.findById(this.accident.getId()), is(this.accident));
    }
}