package project.italy.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.italy.backend.models.Regione;
import project.italy.backend.service.RegioneService;

@Controller
@RequestMapping("/backoffice/regioni")
public class RegioneController {

    @Autowired
    RegioneService regioneService;

    @GetMapping
    public String index(Model model) {
        List<Regione> regioni = regioneService.getRegioniOrdinate();
        model.addAttribute("regioni", regioni);
        return "regione/index";
    }

    @GetMapping("/{slugRegione}")
    public String show(@PathVariable("slugRegione") String slugRegione, Model model) {
        Regione regione = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("regione", regione);
        return "regione/show";
    }
}
