package org.stroganov.repositoty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public abstract class ItemDAO {
    public static final String INPUT_PARAMETERS_CANNOT_BE_NULL = "Input parameters cannot be null.";
    public static final Logger LOGGER = LogManager.getLogger(ItemDAO.class);
    public static final String ERROR_MESSAGE_FOR_QUERY = "Error while executing query.";
    protected final SessionFactory sessionFactory;

    protected ItemDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T getEntityFromDBByParam(String parameterName, Object parameterValue, Class<T> entityClass) {
        if (parameterName == null || parameterValue == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        T entity;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<T> query = session.createQuery("FROM " + entityClass.getSimpleName() + " WHERE " + parameterName + " = :paramValue", entityClass);
            query.setParameter("paramValue", parameterValue);
            entity = query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return entity;
    }
}
