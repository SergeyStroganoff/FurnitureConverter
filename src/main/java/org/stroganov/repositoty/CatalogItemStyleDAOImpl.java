package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.stroganov.entities.CatalogItemStyle;

@Repository
public class CatalogItemStyleDAOImpl extends ItemDAO implements CatalogItemStyleDAO {

    @Autowired
    protected CatalogItemStyleDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CatalogItemStyle getByArticleAndName(String article, String name) {
        if (article == null || name == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        CatalogItemStyle catalogItemStyle;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<CatalogItemStyle> query = session.createQuery("FROM CatalogItemStyle WHERE styleArticle = :articleValue AND styleName = :nameValue", CatalogItemStyle.class);
            query.setParameter("articleValue", article);
            query.setParameter("nameValue", name);
            catalogItemStyle = query.uniqueResult();
            transaction.commit();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return catalogItemStyle;
    }
}
