package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(1);

    public AccidentMem() {
        save(new Accident("Иван Иванов", "Превышение скорости", "Москва, улица Ленина, 10"));
        save(new Accident("Владимир Петров", "Проезд на красный сигнал светофора", "Санкт-Петербург, Проспект Октября, 45"));
        save(new Accident("Алексей Сидоров", "Вождение в нетрезвом виде", "Новосибирск, улица Декабристов, 76"));
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ID.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
