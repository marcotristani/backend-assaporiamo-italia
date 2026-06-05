package project.italy.backend.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Vino;
import project.italy.backend.service.VinoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/vini")
public class VinoRestController {

    @Autowired
    VinoService vinoService;

    @GetMapping("/all")
    public List<Vino> index(@RequestParam(defaultValue = "default") String order) {
        return vinoService.findAllViniOrdinati(order);
    }

    @GetMapping("/regione/{slugRegione}")
    public List<Vino> indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order) {
        return vinoService.getViniPerRegione(slugRegione, order);
    }

    @GetMapping("/categoria/{slugTipologia}")
    public List<Vino> indexTipologia(@PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order) {
        return vinoService.getViniPerTipologia(slugTipologia, order);
    }

    @GetMapping("/regione/{slugRegione}/categoria/{slugTipologia}")
    public List<Vino> indexRegioneETipolgia(
            @PathVariable("slugRegione") String slugRegione,
            @PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order) {
        return vinoService.getViniPerRegioneETipologia(slugRegione, slugTipologia, order);
    }

}
