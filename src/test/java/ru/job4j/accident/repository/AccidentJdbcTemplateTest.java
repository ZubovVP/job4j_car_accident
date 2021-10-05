package ru.job4j.accident.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.config.JdbcConfig;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.StatusAccident;

import javax.sql.DataSource;

import java.io.InputStream;
import java.util.*;

import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 12.11.2020.
 */
public class AccidentJdbcTemplateTest {
    private AccidentJdbcTemplate db;
    private JdbcTemplate jdbc;
    private Accident accident;

    @Before
    public void start() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            Properties props = new Properties();
            props.load(in);
            DataSource ds = new JdbcConfig().ds(props.getProperty("jdbc.driver"), props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
            this.jdbc = new JdbcConfig().jdbc(ds);
            this.db = new AccidentJdbcTemplate(this.jdbc);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        this.accident = fillAccident(this.accident);
    }

    @After
    public void clearAllTable() {
        this.jdbc.update("DELETE FROM accidents_rules");
        this.jdbc.update("DELETE FROM accidents");
    }

    private Accident fillAccident(Accident accident) {
        Set<Rule> rules = new HashSet<>();
        rules.add(Rule.of(1, "Статья. 1"));
        return Accident.of(0, "Name1", "Text1", "Address1", AccidentType.of(1, "Две машины"), rules, StatusAccident.Принята);
    }

    @Test
    public void addTest() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
        this.db.add(this.accident);
        Assert.assertThat(this.db.getAllElements().size(), is(1));
    }

    @Test
    public void getAllAccidentsTestWithOneElement() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
        this.accident = this.db.add(this.accident);
        Map<Integer, Accident> resultMap = this.db.getAllElements();
        Assert.assertThat(resultMap.size(), is(1));
        Assert.assertTrue(resultMap.containsKey(this.accident.getId()));
        Assert.assertThat(resultMap.get(this.accident.getId()), is(this.accident));
    }

    @Test
    public void getAllAccidentsTestWithTwoElements() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
        this.accident = this.db.add(this.accident);
        Map<Integer, Accident> resultMap = this.db.getAllElements();
        Assert.assertThat(resultMap.size(), is(1));
        Assert.assertTrue(resultMap.containsKey(this.accident.getId()));
        this.accident.setId(0);
        this.accident = this.db.add(this.accident);
        resultMap = this.db.getAllElements();
        Assert.assertThat(resultMap.size(), is(2));
        Assert.assertTrue(resultMap.containsKey(this.accident.getId()));
    }

    @Test
    public void addTypeTest() {
        Assert.assertThat(this.db.getTypes().size(), is(3));
        AccidentType at = AccidentType.of(1000000, "TestType");
        this.db.addType(at);
        List<AccidentType> resultList = this.db.getTypes();
        Assert.assertThat(resultList.size(), is(4));
        Assert.assertTrue(resultList.contains(at));
        this.jdbc.update("DELETE FROM accident_types WHERE accident_types.id = ?", at.getId());
    }

    @Test
    public void deleteAccidentFromDBTest() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
        this.accident = this.db.add(this.accident);
        Map<Integer, Accident> resultMap = this.db.getAllElements();
        Assert.assertThat(resultMap.size(), is(1));
        this.db.delete(this.accident.getId());
        Assert.assertThat(this.db.getAllElements().size(), is(0));
    }

    @Test
    public void findByIdAccidentTest() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
        this.accident = this.db.add(this.accident);
        Assert.assertThat(this.db.findById(this.accident.getId()), is(this.accident));
    }

    @Test
    public void getAllTypesTest() {
        Assert.assertThat(this.db.getTypes().size(), is(3));
        List<AccidentType> allTypes =  this.db.getTypes();
        Assert.assertTrue(allTypes.contains(this.accident.getType()));
    }

    @Test
    public void getTypeTest() {
        AccidentType ac = this.db.getType(this.accident.getType().getId());
        Assert.assertThat(ac, is(this.accident.getType()));
    }

    @Test
    public void getAllRulesTest() {
        List<Rule> allRules =  this.db.getRules();
        Assert.assertThat(allRules.size(), is(3));
        Assert.assertTrue(allRules.containsAll(this.accident.getRules()));
    }

    @Test
    public void updateAccidentTest() {
        Assert.assertThat(this.db.getAllElements().size(), is(0));
       this.accident = this.db.add(this.accident);
        Assert.assertThat(this.db.getAllElements().size(), is(1));
        Assert.assertThat(this.db.findById(this.accident.getId()), is(this.accident));
        this.accident.setName("Name2");
        this.db.update(this.accident);
        Assert.assertThat(this.db.findById(this.accident.getId()), is(this.accident));

    }



}