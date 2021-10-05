package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.StatusAccident;
import ru.job4j.accident.operations.Actions;


import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.11.2020.
 */
@Repository
public class AccidentJdbcTemplate implements Actions<Accident, Integer, AccidentType, Rule> {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Add element in a DB.
     *
     * @param element - accident.
     * @return - accident with id
     */
    @Override
    public Accident add(Accident element) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO accidents (name, text, address, id_accident_types) VALUES (?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, element.getName());
            ps.setString(2, element.getText());
            ps.setString(3, element.getAddress());
            ps.setInt(4, element.getType().getId());
            return ps;
        }, holder);
        element.setId(holder.getKey().intValue());
        for (Rule rule : element.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (accidents_id, rules_id) VALUES (?, ?)",
                    element.getId(), rule.getId());
        }
        return element;
    }

    /**
     * Add Accidedent of type in a DB.
     *
     * @param type - Accident of type.
     */
    @Override
    public void addType(AccidentType type) {
        jdbc.update("INSERT INTO accident_types (id, name) VALUES (?, ?)",
                type.getId(), type.getName());
    }

    /**
     * Delete accident form DB.
     *
     * @param id - id.
     * @return - result.
     */
    @Override
    public boolean delete(int id) {
        jdbc.update("DELETE FROM accidents_rules WHERE accidents_id = ?", id);
        return true;
    }

    /**
     * Find Accident from DB with id.
     *
     * @param id - id of an element.
     * @return - The Accident.
     */
    @Override
    public Accident findById(int id) {
        // CHECKSTYLE:OFF
        List<Accident> listOfAccidents = jdbc.query("SELECT accidents.id, accidents.text, accidents.address, accidents.name, accidents.id_accident_types AS idT, accident_types.name AS nameT, rules.id AS idR, rules.name AS nameR" +
                        " FROM accidents_rules" +
                        " INNER JOIN rules ON accidents_rules.rules_id = rules.id" +
                        " INNER JOIN accidents ON accidents_rules.accidents_id = accidents.id" +
                        " INNER JOIN accident_types ON accident_types.id = accidents.id_accident_types" +
                        " WHERE accidents.id = " + id,
                (rs, row) -> {
                    AccidentType type = AccidentType.of(rs.getInt("idT"), rs.getString("nameT"));
                    Set<Rule> rules = new HashSet<>();
                    rules.add(Rule.of(rs.getInt("idR"), rs.getString("nameR")));
                    return Accident.of(rs.getInt("id"), rs.getString("name"), rs.getString("text"), rs.getString("address"), type, rules, StatusAccident.Принята);
                });
        for (int x = 1; x < listOfAccidents.size(); x++) {
            if (listOfAccidents.get(x - 1).getId() == listOfAccidents.get(x).getId()) {
                listOfAccidents.get(x).getRules().addAll(listOfAccidents.get(x - 1).getRules());
                listOfAccidents.remove(x - 1);
            }
        }
        return listOfAccidents.get(0);
    }
    // CHECKSTYLE:ON


    /**
     * Return all accidents from DB.
     *
     * @return - accidents.
     */
    // CHECKSTYLE:OFF
    @Override
    public Map<Integer, Accident> getAllElements() {
        List<Accident> listOfAccidents = jdbc.query("SELECT accidents.id, accidents.text, accidents.address, accidents.name, accidents.id_accident_types AS idT, accident_types.name AS nameT, rules.id AS idR, rules.name AS nameR" +
                        "  FROM accidents_rules" +
                        " INNER JOIN rules ON accidents_rules.rules_id = rules.id" +
                        " INNER JOIN accidents ON accidents_rules.accidents_id = accidents.id" +
                        " INNER JOIN accident_types ON accident_types.id = accidents.id_accident_types",
                (rs, row) -> {
                    AccidentType type = AccidentType.of(rs.getInt("idT"), rs.getString("nameT"));
                    Set<Rule> rules = new HashSet<>();
                    rules.add(Rule.of(rs.getInt("idR"), rs.getString("nameR")));
                    return Accident.of(rs.getInt("id"), rs.getString("name"), rs.getString("text"), rs.getString("address"), type, rules, StatusAccident.Принята);
                });
        for (int x = 1; x < listOfAccidents.size(); x++) {
            if (listOfAccidents.get(x - 1).getId() == listOfAccidents.get(x).getId()) {
                listOfAccidents.get(x).getRules().addAll(listOfAccidents.get(x - 1).getRules());
                listOfAccidents.remove(x - 1);
            }
        }
        return listOfAccidents.stream().collect(Collectors.toMap(Accident::getId, element -> element));
    }
    // CHECKSTYLE:ON


    /**
     * Return all accidents of type from DB.
     *
     * @return - List of Accidents of type.
     */
    @Override
    public List<AccidentType> getTypes() {
        return jdbc.query("SELECT id, name FROM accident_types",
                (rs, row) -> AccidentType.of(rs.getInt("id"), rs.getString("name")));
    }

    /**
     * Find and return accident of type use id.
     *
     * @param id - id of accident of type.
     * @return - accident of type.
     */
    @Override
    public AccidentType getType(int id) {
        AccidentType result = jdbc.query("SELECT id, name FROM accident_types WHERE id = " + id,
                (rs, row) -> AccidentType.of(rs.getInt("id"), rs.getString("name"))).get(0);
        return result;
    }

    /**
     * Return all rules from DB.
     *
     * @return list of rules.
     */
    @Override
    public List<Rule> getRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, row) -> Rule.of(rs.getInt("id"), rs.getString("name")));
    }

    @Override
    public Accident update(Accident element) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE accidents SET name = '").append(element.getName()).append("', text = '").append(element.getText()).append("', address = '").append(element.getAddress()).append("', id_accident_types = ").append(element.getType().getId()).append(" WHERE id = ").append(element.getId());
        jdbc.update(sb.toString());
        sb.setLength(0);
        sb.append("DELETE FROM accidents_rules WHERE accidents_id = ").append(element.getId());
        jdbc.update(sb.toString());
        for (Rule rule : element.getRules()) {
            sb.setLength(0);
            jdbc.update("INSERT INTO accidents_rules (accidents_id, rules_id) VALUES (?, ?)",
                    element.getId(), rule.getId());
        }
        return element;
    }
}
