package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroganov.entities.SampleF;

@Repository
public class SampleFDAOImpl extends ItemDAO implements SampleFDAO {
    @Autowired
    public SampleFDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public SampleF getByArticle(String article) {
        if (article == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        SampleF sampleF;
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<SampleF> query = session.createQuery("FROM SampleF WHERE article = :articleValue", SampleF.class);
            query.setParameter("articleValue", article);
            sampleF = query.uniqueResult();
            transaction.commit();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return sampleF;
    }
    @Override
    public int save(SampleF sampleF) {
        if (sampleF == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(sampleF);
            transaction.commit();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return sampleF.getId();
    }
}
