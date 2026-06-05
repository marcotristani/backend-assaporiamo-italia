package project.italy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.models.Vino;
import project.italy.backend.repository.RegioneRepository;
import project.italy.backend.repository.TipologiaRepository;
import project.italy.backend.repository.VinoRepository;

@Service
public class VinoService {

    @Autowired
    VinoRepository vinoRepository;

    @Autowired
    RegioneRepository regioneRepository;

    @Autowired
    TipologiaRepository tipologiaRepository;

    public List<Vino> findAllViniOrdinati(String order) {
        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findAllByOrderByNomeAsc();
        }
        return vinoRepository.findAll();
    }

    public List<Vino> getViniPerRegione(String slugRegione, String order) {

        Regione regione = regioneRepository.findBySlug(slugRegione)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Regione non trovata"));

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByRegioneOrderByNomeAsc(regione);
        }

        return vinoRepository.findByRegione(regione);
    }

    public List<Vino> getViniPerTipologia(String slugTipologia, String order) {

        Tipologia tipologia = tipologiaRepository.findBySlug(slugTipologia)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata"));

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByTipologiaOrderByNomeAsc(tipologia);
        }

        return vinoRepository.findByTipologia(tipologia);
    }

    public List<Vino> getViniPerRegioneETipologia(String slugRegione, String slugTipologia,
            String order) {

        Regione regione = regioneRepository.findBySlug(slugRegione)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Regione non trovata"));

        Tipologia tipologia = tipologiaRepository.findBySlug(slugTipologia)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata"));

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByRegioneAndTipologiaOrderByNomeAsc(regione, tipologia);
        }

        return vinoRepository.findByRegioneAndTipologia(regione, tipologia);
    }
}
