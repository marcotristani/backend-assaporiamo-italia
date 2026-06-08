package project.italy.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.italy.backend.models.Vino;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.service.RegioneService;
import project.italy.backend.service.TipologiaService;
import project.italy.backend.service.VinoService;

@Controller
@RequestMapping("/backoffice/vini")
public class VinoController {

    @Autowired
    VinoService vinoService;

    @Autowired
    TipologiaService tipologiaService;

    @Autowired
    RegioneService regioneService;

    @GetMapping("/all")
    public String index(@RequestParam(defaultValue = "default") String order, Model model) {
        List<Vino> vini = vinoService.findAllViniOrdinati(order);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", null);
        model.addAttribute("regioneSelezionata", null);
        return "vino/index";
    }

    @GetMapping("/tipologia/{slugTipologia}")
    public String indexTipologia(@PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order, Model model) {
        List<Vino> vini = vinoService.getViniPerTipologia(slugTipologia, order);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Tipologia tipologiaSelezionata = tipologiaService.getTipologiaBySlug(slugTipologia);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", tipologiaSelezionata);
        model.addAttribute("regioneSelezionata", null);

        return "vino/index";
    }

    @GetMapping("/regione/{slugRegione}")
    public String indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order, Model model) {
        List<Vino> vini = vinoService.getViniPerRegione(slugRegione, order);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", null);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "vino/index";
    }

    @GetMapping("/tipologia/{slugTipologia}/regione/{slugRegione}")
    public String indexRegioneETipologia(@PathVariable("slugTipologia") String slugTipologia,
            @PathVariable("slugRegione") String slugRegione, @RequestParam(defaultValue = "default") String order,
            Model model) {
        List<Vino> vini = vinoService.getViniPerRegioneETipologia(slugRegione,
                slugTipologia, order);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Tipologia tipologiaSelezionata = tipologiaService.getTipologiaBySlug(slugTipologia);
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", tipologiaSelezionata);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "vino/index";
    }

    @GetMapping("/dettaglio/{slug}")
    public String show(@PathVariable("slug") String slug, Model model) {
        Vino vino = vinoService.getBySlug(slug);
        model.addAttribute("vino", vino);
        return "vino/show";
    }

}
