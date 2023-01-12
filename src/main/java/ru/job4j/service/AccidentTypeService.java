package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.AccidentType;
import ru.job4j.repository.AccidentTypeMem;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeMem types;

    public List<AccidentType> getTypes() {
        return types.getTypes();
    }

}
