package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static int id = 1;

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id++);
        }
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
