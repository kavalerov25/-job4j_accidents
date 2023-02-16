package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Authority;
import ru.job4j.repository.AuthorityRepository;

@Service
public class AuthorityService {

    private final AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public Authority findByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}
