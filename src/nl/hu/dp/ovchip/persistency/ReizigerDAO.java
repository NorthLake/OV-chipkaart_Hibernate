package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.Reiziger;

import java.time.LocalDate;
import java.util.Set;

public interface ReizigerDAO {
    void save(Reiziger reiziger);
    void update(Reiziger reiziger);
    void delete(Reiziger reiziger);
    Reiziger findById(int id);
    Set<Reiziger> findByGeboortedatum(LocalDate datum);
    Set<Reiziger> findAll();
}
