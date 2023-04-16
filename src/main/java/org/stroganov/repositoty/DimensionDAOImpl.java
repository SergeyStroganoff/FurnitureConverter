package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.stroganov.entities.Dimension;

@Repository
@Transactional
public class DimensionDAOImpl extends ItemDAO implements DimensionDAO {
    @Autowired
    public DimensionDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Dimension findDimensionByDimensions(String widthValue, String heightValue, String depthValue) {
        if (widthValue == null || heightValue == null || depthValue == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        Dimension dimension;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<Dimension> query = session.createQuery("FROM Dimension WHERE width = :widthValue AND height = :heightValue AND depth = :depthValue", Dimension.class);
            query.setParameter("widthValue", widthValue);
            query.setParameter("heightValue", heightValue);
            query.setParameter("depthValue", depthValue);
            dimension = query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return dimension;
    }
}
