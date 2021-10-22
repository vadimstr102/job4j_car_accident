package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private static final AtomicInteger TYPE_ID = new AtomicInteger(1);
    private static final AtomicInteger RULE_ID = new AtomicInteger(1);

    public AccidentMem() {
        AccidentType type1 = AccidentType.of(TYPE_ID.getAndIncrement(), "Две машины");
        AccidentType type2 = AccidentType.of(TYPE_ID.getAndIncrement(), "Машина и человек");
        AccidentType type3 = AccidentType.of(TYPE_ID.getAndIncrement(), "Машина и велосипед");
        types.put(type1.getId(), type1);
        types.put(type2.getId(), type2);
        types.put(type3.getId(), type3);

        Rule rule1 = Rule.of(RULE_ID.getAndIncrement(), "Статья 1");
        Rule rule2 = Rule.of(RULE_ID.getAndIncrement(), "Статья 2");
        Rule rule3 = Rule.of(RULE_ID.getAndIncrement(), "Статья 3");
        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);
        rules.put(rule3.getId(), rule3);

        saveAccident(new Accident("Иван Иванов", "Превышение скорости", "Москва, улица Ленина, 10",
                type1, Set.of(rule1, rule2)));
        saveAccident(new Accident("Владимир Петров", "Проезд на красный сигнал светофора", "Санкт-Петербург, Проспект Октября, 45",
                type2, Set.of(rule2, rule3)));
        saveAccident(new Accident("Алексей Сидоров", "Вождение в нетрезвом виде", "Новосибирск, улица Декабристов, 76",
                type3, Set.of(rule1, rule3)));
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return types.values();
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    public Collection<Rule> findAllRules() {
        return rules.values();
    }
}
