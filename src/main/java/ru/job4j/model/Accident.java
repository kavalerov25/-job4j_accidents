package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private Set<Rule> rules = new HashSet<>();

    public Accident(int id, String name, String text, String address, AccidentType type) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
    }


    public Collection<Rule> getRules() {
        return Set.copyOf(rules);
    }
    public void setRules(Collection<Rule> collection) {
        rules.clear();
        rules.addAll(collection);
    }
}
