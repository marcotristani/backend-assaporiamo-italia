package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.models.Vino;
import project.italy.backend.repository.VinoRepository;

@Service
public class VinoService {

    @Autowired
    VinoRepository vinoRepository;

    @Autowired
    RegioneService regioneService;

    @Autowired
    TipologiaService tipologiaService;

    public Optional<Vino> findBySlug(String slugVino) {
        return vinoRepository.findBySlug(slugVino);
    }

    public Vino getBySlug(String slugVino) {
        return vinoRepository.findBySlug(slugVino).get();
    }

    public List<Vino> findAllViniOrdinati(String order) {
        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findAllByOrderByNomeAsc();
        }
        return vinoRepository.findAll();
    }

    public List<Vino> getViniPerRegione(String slugRegione, String order) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByRegioneOrderByNomeAsc(regione);
        }

        return vinoRepository.findByRegione(regione);
    }

    public List<Vino> getViniPerTipologia(String slugTipologia, String order) {

        Tipologia tipologia = tipologiaService.getTipologiaBySlug(slugTipologia);

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByTipologiaOrderByNomeAsc(tipologia);
        }

        return vinoRepository.findByTipologia(tipologia);
    }

    public List<Vino> getViniPerRegioneETipologia(String slugRegione, String slugTipologia,
            String order) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        Tipologia tipologia = tipologiaService.getTipologiaBySlug(slugTipologia);

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByRegioneAndTipologiaOrderByNomeAsc(regione, tipologia);
        }

        return vinoRepository.findByRegioneAndTipologia(regione, tipologia);
    }

    public List<Vino> getViniPerProdotto(String slugProdotto,
            String order) {

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByProdottiTipiciSlugOrderByNomeAsc(slugProdotto);
        }

        return vinoRepository.findByProdottiTipiciSlug(slugProdotto);
    }

    public Vino create(Vino nuovoVino, Regione regioneSelezionata,
            Tipologia tipologiaSelezionata, List<ProdottoTipico> prodottiSelezionati) {
        nuovoVino.setRegione(regioneSelezionata);
        nuovoVino.setTipologia(tipologiaSelezionata);
        nuovoVino.setProdottiTipici(prodottiSelezionati);
        return vinoRepository.save(nuovoVino);
    }

    public Vino save(Vino vino) {
        return vinoRepository.save(vino);
    }

    public Vino update(Vino vinoDaModificare, Vino formVino, Regione regione,
            Tipologia tipologia) {

        vinoDaModificare.setNome(formVino.getNome());
        vinoDaModificare.setDescrizione(formVino.getDescrizione());
        vinoDaModificare.setLinkStore(formVino.getLinkStore());
        vinoDaModificare.setUrlImmagine(formVino.getUrlImmagine());
        vinoDaModificare.setCertificazione(formVino.getCertificazione());
        vinoDaModificare.setRegione(regione);
        vinoDaModificare.setTipologia(tipologia);

        return vinoRepository.save(vinoDaModificare);
    }
}
