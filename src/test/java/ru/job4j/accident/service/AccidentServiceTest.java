package ru.job4j.accident.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 25.10.2020.
 */
public class AccidentServiceTest {
    private AccidentService service;
    private Accident accident;

    @Before
    public void start() {
        fillAccident();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AccidentMem.class);
        context.register(AccidentService.class);
        context.refresh();
        this.service = context.getBean(AccidentService.class);
    }

    private void fillAccident() {
        this.accident = new Accident();
        this.accident.setId(1);
        this.accident.setName("name");
        this.accident.setAddress("address");
        this.accident.setText("text");
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithNameIsNull() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setName(null);
        this.service.add(this.accident);
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithTextIsNull() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setText(null);
        this.service.add(this.accident);
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithAddressIsNull() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setAddress(null);
        this.service.add(this.accident);
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithNameIsEmpty() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setName("");
        this.service.add(this.accident);
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithTextIsEmpty() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setText("");
        this.service.add(this.accident);
    }

    @Test(expected = NullPointerException.class)
    public void addElementWithAddressIsEmpty() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.accident.setAddress("");
        this.service.add(this.accident);
    }

    @Test
    public void addElement() {
        assertThat(this.service.getAllElements().size(), is(0));
        this.service.add(this.accident);
        assertThat(this.service.getAllElements().size(), is(1));
    }

    @Test
    public void findByIdWithNotContainsId() {
        assertNull(this.service.findById(this.accident.getId()));
    }

    @Test
    public void findByIdWithContainsId() {
        this.service.add(this.accident);
        assertNotNull(this.service.findById(this.accident.getId()));
    }
}