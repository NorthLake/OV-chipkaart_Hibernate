package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;

import java.util.Set;

public interface AdresDAO {
    void save(Adres adres);
    void update(Adres adres);
    void delete(Adres adres);
    Adres findById(int id);
    Adres findByReiziger(Reiziger reiziger);
    Set<Adres> findByStad(String stad);
    Set<Adres> findAll();
}
