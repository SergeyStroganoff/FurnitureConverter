package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.stroganov.entities.Size;

@Repository
@Transactional
public class SizeDAOImpl extends ItemDAO implements SizeDAO {
    @Autowired
    public SizeDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Autowired

    @Override
    public Size findSizeByDimensions(String widthValue, String heightValue, String depthValue) {
        if (widthValue == null || heightValue == null || depthValue == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        Size size;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<Size> query = session.createQuery("FROM Size WHERE width = :widthValue AND height = :heightValue AND depth = :depthValue", Size.class);
            query.setParameter("widthValue", widthValue);
            query.setParameter("heightValue", heightValue);
            query.setParameter("depthValue", depthValue);
            size = query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return size;
    }
}
