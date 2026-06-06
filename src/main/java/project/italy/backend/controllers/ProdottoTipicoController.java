package project.italy.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.service.CategoriaService;
import project.italy.backend.service.ProdottoTipicoService;

@Controller
@RequestMapping("/backoffice/prodotti")
public class ProdottoTipicoController {

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/all")
    public String index(@RequestParam(defaultValue = "default") String order, Model model) {
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.findAllProdottiOrdinati(order);
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", null);
        model.addAttribute("regioneSelezionata", null);
        return "prodottoTipico/index";
    }

    @GetMapping("/categoria/{slugCategoria}")
    public String index(@PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order, Model model) {
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerCategoria(slugCategoria, order);
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        Categoria categoriaSelezionata = categoriaService.getCategoriaBySlug(slugCategoria);
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", categoriaSelezionata);
        model.addAttribute("regioneSelezionata", null);

        return "prodottoTipico/index";
    }
}
