package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private SessionFactory sessionFactory;

    public OVChipkaartDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session openSessionWithTransaction() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void closeSessionWithTransaction(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void save(OVChipkaart ovChipkaart) {
        Session session = openSessionWithTransaction();
        session.save(ovChipkaart);
        closeSessionWithTransaction(session);
    }

    @Override
    public void update(OVChipkaart ovChipkaart) {
        Session session = openSessionWithTransaction();
        session.update(ovChipkaart);
        closeSessionWithTransaction(session);
    }

    @Override
    public void delete(OVChipkaart ovChipkaart) {
        Session session = openSessionWithTransaction();
        session.delete(ovChipkaart);
        closeSessionWithTransaction(session);
    }

    @Override
    public OVChipkaart findByKaartNummer(int kaartNummer) {
        Session session = sessionFactory.openSession();
        OVChipkaart ovChipkaart = session.get(OVChipkaart.class, kaartNummer);
        session.close();
        return ovChipkaart;
    }

    @Override
    public Set<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select OVChipkaart from OVChipkaart left join OVChipkaart.reiziger where Reiziger.id = :reizigerId");
        query.setParameter("reizigerId", reiziger.getId());
        //noinspection unchecked
        Set<OVChipkaart> producten = new HashSet<OVChipkaart>(query.list());
        session.close();
        return producten;
    }

    @Override
    public Set<OVChipkaart> findAll() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        Set<OVChipkaart> ovChipkaarten = new HashSet<OVChipkaart>(session.createQuery("from OVChipkaart").list());
        session.close();
        return ovChipkaarten;
    }
}
