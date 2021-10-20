package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(1);
    private static final AtomicInteger TYPE_ID = new AtomicInteger(1);

    public AccidentMem() {
        AccidentType type1 = AccidentType.of(1, "Две машины");
        AccidentType type2 = AccidentType.of(2, "Машина и человек");
        AccidentType type3 = AccidentType.of(3, "Машина и велосипед");
        types.put(TYPE_ID.getAndIncrement(), type1);
        types.put(TYPE_ID.getAndIncrement(), type2);
        types.put(TYPE_ID.getAndIncrement(), type3);

        saveAccident(new Accident("Иван Иванов", "Превышение скорости", "Москва, улица Ленина, 10", type1));
        saveAccident(new Accident("Владимир Петров", "Проезд на красный сигнал светофора", "Санкт-Петербург, Проспект Октября, 45", type2));
        saveAccident(new Accident("Алексей Сидоров", "Вождение в нетрезвом виде", "Новосибирск, улица Декабристов, 76", type3));
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
}
