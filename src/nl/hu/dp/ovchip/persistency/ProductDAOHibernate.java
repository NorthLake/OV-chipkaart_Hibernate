package nl.hu.dp.ovchip.persistency;

import nl.hu.dp.ovchip.domein.OVChipkaart;
import nl.hu.dp.ovchip.domein.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory sessionFactory;

    public ProductDAOHibernate() {
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
    public void save(Product product) {
        Session session = openSessionWithTransaction();
        session.save(product);
        closeSessionWithTransaction(session);
    }

    @Override
    public void update(Product product) {
        Session session = openSessionWithTransaction();
        session.update(product);
        closeSessionWithTransaction(session);
    }

    @Override
    public void delete(Product product) {
        Session session = openSessionWithTransaction();
        session.delete(product);
        closeSessionWithTransaction(session);
    }

    @Override
    public Product findByNummer(int productNummer) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, productNummer);
        session.close();
        return product;
    }

    @Override
    public Set<Product> findByOVChipKaart(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        Set<Product> producten = new HashSet<Product>(session.createQuery("select OVChipkaart from Product join OVChipkaart on Product.kaarten").list());
        session.close();
        return producten;
    }

    @Override
    public Set<Product> findAll() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        Set<Product> producten = new HashSet<Product>(session.createQuery("from Product").list());
        session.close();
        return producten;
    }
}
