package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident saveAccident(Accident accident) {
        if (accident.getId() != 0) {
            return updateAccident(accident);
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) keyHolder.getKeys().get("id"));

        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }

        return accident;
    }

    private Accident updateAccident(Accident accident) {
        jdbc.update(
                "update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );

        jdbc.update(
                "delete from accident_rule where accident_id = ?",
                accident.getId()
        );

        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }

        return accident;
    }

    public List<Accident> findAllAccidents() {
        List<Accident> accidents = jdbc.query(
                "select a.id, a.name, a.text, a.address, t.id, t.name from accident a inner join type t on t.id = a.type_id",
                AccidentJdbcTemplate::mapRowAccident
        );

        for (Accident accident : accidents) {
            List<Rule> rules = findRulesByAccidentId(accident.getId());
            accident.setRules(new HashSet<>(rules));
        }

        return accidents;
    }

    public Accident findAccidentById(int id) {
        Accident accident = jdbc.queryForObject(
                "select a.id, a.name, a.text, a.address, t.id, t.name from accident a inner join type t on t.id = a.type_id where a.id = ?",
                AccidentJdbcTemplate::mapRowAccident,
                id
        );

        List<Rule> rules = findRulesByAccidentId(accident.getId());
        accident.setRules(new HashSet<>(rules));

        return accident;
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from type where id = ?",
                AccidentJdbcTemplate::mapRowAccidentType,
                id
        );
    }

    public List<AccidentType> findAllTypes() {
        return jdbc.query(
                "select id, name from type",
                AccidentJdbcTemplate::mapRowAccidentType
        );
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select id, name from rule where id = ?",
                AccidentJdbcTemplate::mapRowRule,
                id
        );
    }

    public List<Rule> findAllRules() {
        return jdbc.query(
                "select id, name from rule",
                AccidentJdbcTemplate::mapRowRule
        );
    }

    private static Accident mapRowAccident(ResultSet rs, int row) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt(1));
        accident.setName(rs.getString(2));
        accident.setText(rs.getString(3));
        accident.setAddress(rs.getString(4));
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt(5));
        accidentType.setName(rs.getString(6));
        accident.setType(accidentType);
        return accident;
    }

    private static AccidentType mapRowAccidentType(ResultSet rs, int row) throws SQLException {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt(1));
        accidentType.setName(rs.getString(2));
        return accidentType;
    }

    private static Rule mapRowRule(ResultSet rs, int row) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt(1));
        rule.setName(rs.getString(2));
        return rule;
    }

    private List<Rule> findRulesByAccidentId(int accidentId) {
        return jdbc.query(
                "select r.id, r.name from rule r inner join accident_rule ar on r.id = ar.rule_id where ar.accident_id = ?",
                AccidentJdbcTemplate::mapRowRule,
                accidentId
        );
    }
}
