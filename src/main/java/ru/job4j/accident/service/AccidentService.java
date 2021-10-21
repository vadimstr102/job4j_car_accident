package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public void saveAccident(Accident accident, String[] rulesIds) {
        AccidentType type = accidentJdbcTemplate.findTypeById(accident.getType().getId());
        Set<Rule> rules = new HashSet<>();
        for (String id : rulesIds) {
            rules.add(accidentJdbcTemplate.findRuleById(Integer.parseInt(id)));
        }
        accident.setType(type);
        accident.setRules(rules);
        accidentJdbcTemplate.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidentJdbcTemplate.findAllAccidents();
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentJdbcTemplate.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentJdbcTemplate.findAllRules();
    }
}
