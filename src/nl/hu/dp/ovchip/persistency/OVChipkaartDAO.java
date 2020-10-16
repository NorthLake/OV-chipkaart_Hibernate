package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.util.Set;

public interface OVChipkaartDAO {
    void save(OVChipkaart ovChipkaart);
    void update(OVChipkaart ovChipkaart);
    void delete(OVChipkaart ovChipkaart);
    OVChipkaart findByKaartNummer(int kaartNummer);
    Set<OVChipkaart> findByProduct(Product product);
    Set<OVChipkaart> findByReiziger(Reiziger reiziger);
    Set<OVChipkaart> findAll();
}
