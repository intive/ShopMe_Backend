package com.intive.shopme.voivodeship;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class VoivodeshipService {

    private final VoivodeshipRepository repository;

    public VoivodeshipService(VoivodeshipRepository repository) {
        this.repository = repository;
    }

    public List<Voivodeship> getAll() {
        return repository.findAll();
    }
}
