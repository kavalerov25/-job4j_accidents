package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class RuleMem {

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger size = new AtomicInteger(rules.size());

    {
        rules.put(1, new Rule(1, "Статья 1"));
        rules.put(2, new Rule(2, "Статья 2"));
        rules.put(3, new Rule(3, "Статья 3"));
    }

    public List<Rule> getRules() {
        return rules.values().stream().toList();
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    public List<Rule> getByIds(List<Integer> ids) {
        return ids.stream().map(rules::get).collect(Collectors.toList());
    }

    public Rule put(Rule rule) {
        int id = size.incrementAndGet();
        rule.setId(id);
        rules.put(id, rule);
        return rule;
    }

    public boolean update(Rule rule) {
        return rules.replace(rule.getId(), rule) != null;
    }

}
