package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;

import java.util.Set;

public interface ProductDAO {
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Product findByNummer(int productNummer);
    Set<Product> findByOVChipKaart(OVChipkaart ovChipkaart);
    Set<Product> findAll();
}
