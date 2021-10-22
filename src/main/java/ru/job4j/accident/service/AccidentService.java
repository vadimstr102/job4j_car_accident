package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
public class AccidentService {
    private final AccidentRepository accidents;
    private final AccidentTypeRepository types;
    private final RuleRepository rules;

    public AccidentService(AccidentRepository accidents, AccidentTypeRepository types, RuleRepository rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    @Transactional
    public void saveAccident(Accident accident, String[] rulesIds) {
        AccidentType type = types.findById(accident.getType().getId()).get();
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : rulesIds) {
            ruleSet.add(rules.findById(Integer.parseInt(id)).get());
        }
        accident.setType(type);
        accident.setRules(ruleSet);
        accidents.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.findById(id).get();
    }

    public List<Accident> findAllAccidents() {
        List<Accident> result = new ArrayList<>();
        accidents.findAll().forEach(result::add);
        return result;
    }

    public List<AccidentType> findAllTypes() {
        List<AccidentType> result = new ArrayList<>();
        types.findAll().forEach(result::add);
        return result;
    }

    public List<Rule> findAllRules() {
        List<Rule> result = new ArrayList<>();
        rules.findAll().forEach(result::add);
        return result;
    }
}
