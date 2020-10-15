package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartNummer;
    @Column(name = "geldig_tot")
    private LocalDate geldigTot;
    private int klasse;
    private float saldo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = @JoinColumn(name = "kaart_nummer"),
            inverseJoinColumns = @JoinColumn(name = "product_nummer")
    )
    private Set<Product> producten = new HashSet<>();

    public OVChipkaart() {

    }

    public OVChipkaart(int kaartNummer, LocalDate geldigTot, int klasse, float saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        BigDecimal saldoAfgerond = new BigDecimal(Float.toString(saldo)).setScale(2, RoundingMode.HALF_UP);
        StringBuilder string = new StringBuilder("OVChipkaart #" + kaartNummer + ", geldig tot " + geldigTot + ", klasse " + klasse + ", €" + saldoAfgerond);
        if (reiziger != null)
            string.append(", reiziger #").append(reiziger.getId());
        if (!producten.isEmpty()) {
            string.append(", producten: ");
            for (Product product : producten) {
                string.append("#").append(product.getProductNummer()).append(", ");
            }
        }
        return string.toString();
    }

    public boolean addProduct(Product product) {
        return producten.add(product);
    }

    public boolean removeProduct(Product product) {
        return producten.remove(product);
    }

    //region getters
    public int getKaartNummer() {
        return kaartNummer;
    }

    public LocalDate geldigTot() {
        return geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public Set<Product> getProducten() {
        return Collections.unmodifiableSet(producten);
    }

    //endregion

    //region setters
    public void setGeldigTot(LocalDate geldigTot) {
        this.geldigTot = geldigTot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
    //endregion
}
