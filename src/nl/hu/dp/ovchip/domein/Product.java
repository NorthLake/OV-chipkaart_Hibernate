package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_nummer")
    private int nummer;
    private String naam;
    private String beschrijving;
    private float prijs;
    @ManyToMany
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = @JoinColumn(name = "product_nummer"),
            inverseJoinColumns = @JoinColumn(name = "kaart_nummer")
    )
    private Set<OVChipkaart> kaarten = new HashSet<>();

    public Product() {

    }

    public Product(int nummer, String naam, String beschrijving, float prijs) {
        this.nummer = nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public boolean addKaart(OVChipkaart ovChipkaart) {
        return kaarten.add(ovChipkaart);
    }

    public boolean removeKaart(OVChipkaart ovChipkaart) {
        return kaarten.remove(ovChipkaart);
    }

    @Override
    public String toString() {
        BigDecimal prijsAfgerond = new BigDecimal(Float.toString(prijs)).setScale(2, RoundingMode.HALF_UP);
        StringBuilder string = new StringBuilder("Product #" + nummer + " " + naam + ": \"" + beschrijving + "\", €" + prijsAfgerond);
        if (!kaarten.isEmpty()) {
            string.append(", OV-Chipkaarten: ");
            for (OVChipkaart ovChipkaart : kaarten) {
                string.append("#").append(ovChipkaart.getKaartNummer()).append(", ");
            }
        }
        return string.toString();
    }

    //region getters
    public int getProductNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public float getPrijs() {
        return prijs;
    }

    public Set<OVChipkaart> getKaarten() {
        return Collections.unmodifiableSet(kaarten);
    }

    //endregion

    //region setters
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }
    //endregion
}
