package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.entities.Model;

@Component
public class ModelDAOImpl extends ItemDAO implements ModelDAO {

    @Autowired
    public ModelDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Model getByArticle(String article) {
        if (article == null) {
            LOGGER.error(INPUT_PARAMETERS_CANNOT_BE_NULL);
            throw new IllegalArgumentException(INPUT_PARAMETERS_CANNOT_BE_NULL);
        }
        Model model;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<Model> query = session.createQuery("FROM Model WHERE article = :articleValue", Model.class);
            query.setParameter("articleValue", article);
            model = query.uniqueResult();
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_FOR_QUERY, ex);
            throw new RuntimeException(ERROR_MESSAGE_FOR_QUERY, ex);
        }
        return model;
    }

    public Model getByArticleV2(String article) {
        return getEntityFromDBByParam("article", article, Model.class);
    }
}
