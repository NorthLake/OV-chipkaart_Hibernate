package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.Adres;
import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory sessionFactory;

    public AdresDAOHibernate() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
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
    public void save(Adres adres) {
        Session session = openSessionWithTransaction();
        session.save(adres);
        closeSessionWithTransaction(session);
    }

    @Override
    public void update(Adres adres) {
        Session session = openSessionWithTransaction();
        session.update(adres);
        closeSessionWithTransaction(session);
    }

    @Override
    public void delete(Adres adres) {
        Session session = openSessionWithTransaction();
        session.delete(adres);
        closeSessionWithTransaction(session);
    }

    @Override
    public Adres findById(int id) {
        Session session = sessionFactory.openSession();
        Adres adres = session.get(Adres.class, id);
        session.close();
        return adres;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Adres where reiziger = :reiziger");
        query.setParameter("reiziger", reiziger);
        Adres adres = (Adres) query.getSingleResult();
        session.close();
        return adres;
    }

    @Override
    public Set<Adres> findByStad(String stad) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Adres where woonplaats = :woonplaats");
        query.setParameter("woonplaats", stad);
        //noinspection unchecked
        Set<Adres> adressen = new HashSet<Adres>(query.list());
        session.close();
        return adressen;
    }

    @Override
    public Set<Adres> findAll() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        Set<Adres> adressen = new HashSet<Adres>(session.createQuery("from Adres").list());
        session.close();
        return adressen;
    }
}
