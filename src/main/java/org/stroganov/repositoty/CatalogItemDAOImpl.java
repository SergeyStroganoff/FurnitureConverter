package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.stroganov.entities.CatalogItem;

@Repository
@Transactional
public class CatalogItemDAOImpl implements CatalogItemDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CatalogItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int saveOrUpdate(CatalogItem catalogItem) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.saveOrUpdate(catalogItem);
        }
        return catalogItem.getId();
    }
}
