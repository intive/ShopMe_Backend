package com.intive.shopme.voivodeship;

import com.intive.shopme.model.db.DbVoivodeship;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class VoivodeshipService {

    private final VoivodeshipRepository repository;

    public VoivodeshipService(VoivodeshipRepository repository) {
        this.repository = repository;
    }

    public List<DbVoivodeship> getAll() {
        return repository.findAll();
    }

    boolean getVoivodeshipById(UUID id) {
        return repository.existsById(id);
    }
}
