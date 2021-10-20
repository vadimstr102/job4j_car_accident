package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void saveAccident(Accident accident, String[] rulesIds) {
        AccidentType type = accidentMem.findTypeById(accident.getType().getId());
        Set<Rule> rules = new HashSet<>();
        for (String id : rulesIds) {
            rules.add(accidentMem.findRuleById(Integer.parseInt(id)));
        }
        accident.setType(type);
        accident.setRules(rules);
        accidentMem.saveAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentMem.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }
}
