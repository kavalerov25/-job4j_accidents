package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeMem store;

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public boolean update(AccidentType type) {
        return store.update(type);
    }

    public Optional<AccidentType> get(int id) {
        return store.get(id);
    }

}
