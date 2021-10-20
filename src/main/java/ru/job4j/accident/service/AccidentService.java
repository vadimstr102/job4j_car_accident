package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidents;

    public AccidentService(AccidentMem accidents) {
        this.accidents = accidents;
    }

    public void save(Accident accident) {
        accidents.save(accident);
    }

    public Accident findById(int id) {
        return accidents.findById(id);
    }

    public Collection<Accident> findAll() {
        return accidents.findAll();
    }
}
