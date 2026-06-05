
package project.italy.backend.restControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.service.CategoriaService;
import project.italy.backend.service.ProdottoTipicoService;
import project.italy.backend.service.RegioneService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/prodotti")
public class ProdottoTipicoRestController {

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @Autowired
    RegioneService regioneService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/all")
    public ResponseEntity<List<ProdottoTipico>> index(@RequestParam(defaultValue = "default") String order) {
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.findAllProdottiOrdinati(order);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}")
    public ResponseEntity<List<ProdottoTipico>> indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);

        if (optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegione(slugRegione, order);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/categoria/{slugCategoria}")
    public ResponseEntity<List<ProdottoTipico>> indexCategoria(@PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order) {

        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerCategoria(slugCategoria, order);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}/categoria/{slugCategoria}")
    public ResponseEntity<List<ProdottoTipico>> indexRegioneECategoria(
            @PathVariable("slugRegione") String slugRegione,
            @PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order) {

        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty() || optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegioneECategoria(slugRegione,
                slugCategoria, order);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

}
