package project.italy.backend.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Regione;
import project.italy.backend.service.RegioneService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/regioni")
public class RegioneRestController {

    @Autowired
    RegioneService regioneService;

    @GetMapping
    public List<Regione> index() {
        return regioneService.getRegioniOrdinate();
    }

    @GetMapping("/{slugRegione}")
    public Regione show(@PathVariable("slugRegione") String slugRegione) {
        return regioneService.getRegioneBySlug(slugRegione);
    }

}
