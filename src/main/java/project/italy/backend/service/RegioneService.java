package project.italy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.italy.backend.models.Regione;
import project.italy.backend.repository.RegioneRepository;

@Service
public class RegioneService {

    @Autowired
    RegioneRepository regioneRepository;

    public List<Regione> getRegioniOrdinate() {
        return regioneRepository.findAllByOrderByNomeAsc();
    }

    public Regione getRegioneBySlug(String slugRegione) {
        Regione regione = regioneRepository.findBySlug(slugRegione)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata"));

        return regione;
    }
}
