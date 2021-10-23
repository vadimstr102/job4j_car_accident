package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident saveAccident(Accident accident) {
        if (accident.getId() != 0) {
            return updateAccident(accident);
        }

        return executeTransaction(session -> {
            session.save(accident);
            return accident;
        });
    }

    public Accident findAccidentById(int id) {
        return executeTransaction(session -> session.get(Accident.class, id));
    }

    public List<Accident> findAllAccidents() {
        return executeTransaction(session -> session.createQuery(
                        "select distinct a from Accident a join fetch a.rules",
                        Accident.class
                ).list()
        );
    }

    public AccidentType findTypeById(int id) {
        return executeTransaction(session -> session.get(AccidentType.class, id));
    }

    public List<AccidentType> findAllTypes() {
        return executeTransaction(session -> session.createQuery("from AccidentType", AccidentType.class).list());
    }

    public Rule findRuleById(int id) {
        return executeTransaction(session -> session.get(Rule.class, id));
    }

    public List<Rule> findAllRules() {
        return executeTransaction(session -> session.createQuery("from Rule", Rule.class).list());
    }

    private Accident updateAccident(Accident accident) {
        return executeTransaction(session -> {
            Accident dbAccident = session.get(Accident.class, accident.getId());
            dbAccident.setName(accident.getName());
            dbAccident.setText(accident.getText());
            dbAccident.setAddress(accident.getAddress());
            dbAccident.setType(accident.getType());
            dbAccident.setRules(accident.getRules());
            return accident;
        });
    }

    private <T> T executeTransaction(final Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                T rsl = command.apply(session);
                tx.commit();
                return rsl;
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
