package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private SessionFactory sessionFactory;

    public ReizigerDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(reiziger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(reiziger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(reiziger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Reiziger findById(int id) {
        Session session = sessionFactory.openSession();
        Reiziger reiziger = session.get(Reiziger.class, id);
        session.close();
        return reiziger;
    }

    @Override
    public Set<Reiziger> findByGeboortedatum(LocalDate geboortedatum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Reiziger where geboortedatum = :geboortedatum");
        query.setParameter("geboortedatum", geboortedatum);
        //noinspection unchecked
        Set<Reiziger> reizigers = new HashSet<Reiziger>(query.list());
        session.close();
        return reizigers;
    }

    @Override
    public Set<Reiziger> findAll() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        Set<Reiziger> reizigers = new HashSet<Reiziger>(session.createQuery("from Reiziger").list());
        session.close();
        return reizigers;
    }
}
