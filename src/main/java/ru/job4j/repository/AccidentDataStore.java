package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Accident;

public interface AccidentDataStore extends CrudRepository<Accident, Integer> {
}
