package org.stroganov.repositoty;

import org.hibernate.SessionFactory;
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
        return getEntityFromDBByParam("article", article, Model.class);
    }
}
