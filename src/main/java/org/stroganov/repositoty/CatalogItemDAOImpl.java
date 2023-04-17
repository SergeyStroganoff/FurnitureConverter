package org.stroganov.repositoty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroganov.entities.CatalogItem;

@Repository
public class CatalogItemDAOImpl implements CatalogItemDAO {
    private final SessionFactory sessionFactory;
    public static final Logger LOGGER = LogManager.getLogger(CatalogItemDAOImpl.class);
    @Autowired
    public CatalogItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(CatalogItem catalogItem) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(catalogItem);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return catalogItem.getId();
    }
}
