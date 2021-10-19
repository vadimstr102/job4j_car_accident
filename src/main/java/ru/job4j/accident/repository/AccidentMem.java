package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private static final AccidentMem INST = new AccidentMem();
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static int id = 1;

    static {
        INST.save(new Accident("Иван Иванов", "Превышение скорости", "Москва, улица Ленина, 10"));
        INST.save(new Accident("Владимир Петров", "Проезд на красный сигнал светофора", "Санкт-Петербург, Проспект Октября, 45"));
        INST.save(new Accident("Алексей Сидоров", "Вождение в нетрезвом виде", "Новосибирск, улица Декабристов, 76"));
        INST.save(new Accident("Михаил Алексеев", "Стоянка в неположенном месте", "Нижний Новгород, улица Иванова, 12"));
        INST.save(new Accident("Юрий Михайлов", "Пересечение двойной сплошной линии дорожной разметки", "Белгород, улица Пирогова, 5"));
    }

    private AccidentMem() {

    }

    public static AccidentMem instOf() {
        return INST;
    }

    public void save(Accident accident) {
        accident.setId(id++);
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
