package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.entities.SampleF;

@Component
public class SampleFDAOImpl extends ItemDAO implements SampleFDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public SampleFDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SampleF getByArticle(String article) {
        if (article == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }

        SampleF sampleF;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<SampleF> query = session.createQuery("FROM SampleF WHERE article = :article", SampleF.class);
            query.setParameter("article", article);
            sampleF = query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return sampleF;
    }
}
