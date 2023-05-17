package app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    @Temporal(TemporalType.DATE)
    private LocalDate dataEvento;
    private String descrizione;
    private TipoEvento tipoEvento;
    private int numeroMassimoPartecipanti;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<Partecipazione> partecipazioni = new HashSet<>();
	private Location location;

    public Evento() {
    }

    public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public Set<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(Set<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }


    public void setLocation(Location location) {
            this.location = location;
    }

    public void addPartecipazione(Partecipazione partecipazione) {
        partecipazioni.add(partecipazione);
        partecipazione.setEvento(this);
    }
    public enum TipoEvento {
        PUBBLICO,
        PRIVATO
    }
}
