package ru.job4j.accident.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.operations.Actions;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.11.2020.
 */
@Repository
public class AccidentHibernate implements Actions<Accident, Integer, AccidentType, Rule> {
    private static final Logger LOGGER = LogManager.getLogger(AccidentHibernate.class.getName());
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Accident add(Accident element) {
        try {
            this.tx(
                    session -> session.save(element), this.sf);
        } catch (Exception e) {
            LOGGER.error("Failed to add accident to the Database. Element = {}.", element);
        }
        return element;
    }

    @Override
    public void addType(AccidentType type) {
        try {
            this.tx(
                    session -> session.save(type), this.sf);
        } catch (Exception e) {
            LOGGER.error("Failed to add element to the Database. AccidentType = {}.", type);
        }
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try {
            this.tx(
                    session -> {
                        final Query query = session.createQuery("delete Accident where id = :id");
                        query.setParameter("id", id);
                        return query.executeUpdate();
                    }, this.sf);
            result = true;
        } catch (Exception e) {
            LOGGER.error("Failed to delete accident to the Database. Id = {}.", id);
        }
        return result;
    }

    @Override
    public Accident findById(int id) {
        List<Accident> list = this.tx(
                session -> session.createQuery("from Accident i where i.id = " + id).list(), this.sf);
        return list != null ? list.get(0) : null;
    }

    @Override
    public Map<Integer, Accident> getAllElements() {
        List<Accident> listAccident = this.tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Accident a JOIN FETCH a.rules r", Accident.class).list(), this.sf);
        return listAccident.stream().collect(Collectors.toMap(Accident::getId, element -> element));
    }

    @Override
    public List<Rule> getRules() {
        return this.tx(
                session -> session.createQuery("from Rule").list(), this.sf);
    }

    @Override
    public List<AccidentType> getTypes() {
        return this.tx(
                session -> session.createQuery("from AccidentType").list(), this.sf);
    }

    @Override
    public AccidentType getType(int id) {
        List<AccidentType> list = this.tx(
                session -> session.createQuery("from Accident i where i.id = " + id).list(), this.sf);
        return list != null ? list.get(0) : null;
    }

    @Override
    public Accident update(Accident element) {
        try (Session session = this.sf.openSession()) {
            session.saveOrUpdate(element);
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Failed to update accident to the Database. Element = {}.", element);
        }
        return element;
    }

    private <E> E tx(final Function<Session, E> command, SessionFactory factory) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            E rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
