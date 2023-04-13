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
public class SizeDAOImpl implements SizeDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public SizeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Size getSizeBy(String withValue, String heightValue, String depthValue) {
        Size size;
        try (Session session = sessionFactory.getCurrentSession()) {
            Query<Size> query = session.createQuery("FROM Size WHERE with = : withValue AND high = : heightValue AND  depth = : depthValue", Size.class);
            query.setParameter("withValue", withValue);
            query.setParameter("heightValue", heightValue);
            query.setParameter("depthValue", depthValue);
            size = query.uniqueResult();
        }
        return size;
    }
}
