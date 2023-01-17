package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;
import ru.job4j.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem store;
    private final AccidentTypeMem types;

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public void create(Accident accident) {
        accident.setType(types.findById(accident.getType().getId()).orElseThrow(() ->
                new NoSuchElementException("Тип инцидента не найден")));
        store.create(accident);
    }

    public boolean update(int id, Accident accident) {
        accident.setType(types.findById(accident.getType().getId()).orElseThrow(() ->
                new NoSuchElementException("Тип инцидента не найден")));
        return store.update(id, accident);
    }

    public Accident findById(int id) {
        Accident accident = new Accident();
        if (store.findById(id).isPresent()) {
            accident = store.findById(id).get();
        }
        return accident;
    }
}
