package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    private int       id;
    private String    voorletters;
    private String    tussenvoegsel;
    private String    achternaam;
    private LocalDate geboortedatum;

    @OneToOne(mappedBy = "reiziger", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Adres adres;
    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OVChipkaart> kaarten = new HashSet<>();

    public Reiziger(){

    }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        String naam = tussenvoegsel == null
                ? voorletters + " " + achternaam
                : voorletters + " " + tussenvoegsel + " " + achternaam;

        StringBuilder string = new StringBuilder("Reiziger #" + id + " " + naam + ", geb. " + geboortedatum);
        if (adres != null)
            string.append(", adres #").append(adres.getId());
        if (!kaarten.isEmpty()) {
            string.append(", OV-Chipkaarten: ");
            for (OVChipkaart ovChipkaart : kaarten)
                string.append("#").append(ovChipkaart.getKaartNummer()).append(", ");
        }

        return string.toString();
    }

    public boolean addKaart(OVChipkaart ovChipkaart) {
        ovChipkaart.setReiziger(this);
        return this.kaarten.add(ovChipkaart);
    }

    public boolean removeKaart(OVChipkaart ovChipkaart) {
        ovChipkaart.setReiziger(null);
        return this.kaarten.remove(ovChipkaart);
    }

    //region getters
    public Integer getId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public Set<OVChipkaart> getKaarten() {
        return Collections.unmodifiableSet(kaarten);
    }

    //endregion

    //region setters
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    //endregion
}
