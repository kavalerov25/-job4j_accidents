package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem {

    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    {
        AccidentType accidentType1 = new AccidentType(1, "Две машины");
        AccidentType accidentType2 = new AccidentType(2, "Машина и человек");
        AccidentType accidentType3 = new AccidentType(3, "Машина и велосипед");
        types.put(accidentType1.getId(), accidentType1);
        types.put(accidentType2.getId(), accidentType2);
        types.put(accidentType3.getId(), accidentType3);
    }

    public List<AccidentType> getTypes() {
        return types.values().stream().toList();
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }

    public boolean update(AccidentType type) {
        return types.replace(type.getId(), type) != null;
    }

    public Optional<AccidentType> get(int id) {
        return Optional.of(types.get(id));
    }
}
