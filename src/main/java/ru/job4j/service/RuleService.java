package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.RuleMem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleMem store;

    public Collection<Rule> getRules() {
        return store.getRules();
    }

    public Rule findById(int id) {
        return store.findById(id).orElseThrow(() ->
                new NoSuchElementException("Статья нарушения не найдена"));
    }

    public void setRules(Accident accident) {
        if (accident != null && !accident.getRules().isEmpty()) {
            final List<Integer> ids = new ArrayList<>();
            for (Rule rule : accident.getRules()) {
                ids.add(rule.getId());
            }
            accident.setRules(store.getByIds(ids));
        }
    }
}
