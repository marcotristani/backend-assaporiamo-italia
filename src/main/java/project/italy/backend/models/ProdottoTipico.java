package project.italy.backend.models;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "prodotto")
public class ProdottoTipico extends EntityBaseNomeSlug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // nome e slug sono già presi dalla classe padre

    @NotBlank(message = "Inserire una descrizione")
    @Lob
    private String descrizione;

    @URL(message = "inserire un URL valido")
    @NotBlank(message = "Inserire un url per l'immagine")
    private String urlImmagine;

    @URL(message = "inserire un URL valido")
    @NotBlank(message = "Inserire una link per lo store")
    private String linkStore;

    @ManyToOne
    @JoinColumn(name = "regione_id", nullable = false)
    private Regione regione;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "prodotto_id"), inverseJoinColumns = @JoinColumn(name = "vino_id"))
    private List<Vino> vini;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public String getLinkStore() {
        return linkStore;
    }

    public void setLinkStore(String linkStore) {
        this.linkStore = linkStore;
    }

    public Regione getRegione() {
        return regione;
    }

    public void setRegione(Regione regione) {
        this.regione = regione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Vino> getVini() {
        return vini;
    }

    public void setVini(List<Vino> vini) {
        this.vini = vini;
    }

}
