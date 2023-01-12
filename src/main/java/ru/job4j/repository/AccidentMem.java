package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AccidentMem INST = new AccidentMem();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    AtomicInteger idCount = new AtomicInteger();

    public AccidentMem() {
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Stas", "Description 1", "Address 1", new AccidentType()));
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Kir", "Description 2", "Address 2", new AccidentType()));
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Petr", "Description 3", "Address 3", new AccidentType()));
    }

    public static AccidentMem instOf() {
        return INST;
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(idCount.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public void update(int id, Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        Accident accident = accidents.get(id);
        if (accident == null) {
            return Optional.empty();
        }
        return Optional.of(accident);
    }

}
