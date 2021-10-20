package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void saveAccident(Accident accident) {
        AccidentType type = accidentMem.findTypeById(accident.getType().getId());
        accident.setType(type);
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
}
