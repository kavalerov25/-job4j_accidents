package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    AtomicInteger idCount = new AtomicInteger(accidents.size());

    public AccidentMem() {
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Stas", "Description 1", "Address 1",
                new AccidentType(), Set.of(new Rule())));
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Kir", "Description 2", "Address 2",
                new AccidentType(), Set.of(new Rule())));
        accidents.put(idCount.incrementAndGet(), new Accident(idCount.get(), "Petr", "Description 3", "Address 3",
                new AccidentType(), Set.of(new Rule())));
    }


    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(idCount.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accident) != null;
    }

    public Optional<Accident> findById(int id) {
        Accident accident = accidents.get(id);
        if (accident == null) {
            return Optional.empty();
        }
        return Optional.of(accident);
    }

    public Accident put(Accident accident) {
        int id = idCount.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
        return accident;
    }
}
