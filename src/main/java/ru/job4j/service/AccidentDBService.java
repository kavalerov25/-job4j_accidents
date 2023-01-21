package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentDBService {

    private final AccidentJdbcTemplate accidentsRepository;

    public Accident put(Accident accident) {
        return accidentsRepository.put(accident);
    }

    public Collection<Accident> getAccidents() {
        return accidentsRepository.getAccidents();
    }

    public Optional<Accident> get(int id) {
        return accidentsRepository.get(id);
    }

    public void update(Accident accident) {
        accidentsRepository.update(accident);
    }
}