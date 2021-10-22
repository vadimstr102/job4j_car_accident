package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentHibernate accidents;

    public AccidentService(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    public void saveAccident(Accident accident, String[] rulesIds) {
        AccidentType type = accidents.findTypeById(accident.getType().getId());
        Set<Rule> rules = new HashSet<>();
        for (String id : rulesIds) {
            rules.add(accidents.findRuleById(Integer.parseInt(id)));
        }
        accident.setType(type);
        accident.setRules(rules);
        accidents.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.findAccidentById(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.findAllAccidents();
    }

    public Collection<AccidentType> findAllTypes() {
        return accidents.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidents.findAllRules();
    }
}
