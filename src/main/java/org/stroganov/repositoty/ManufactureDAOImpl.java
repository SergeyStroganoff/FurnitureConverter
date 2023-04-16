package org.stroganov.repositoty;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stroganov.entities.Manufacture;

@Component
public class ManufactureDAOImpl extends ItemDAO implements  ManufactureDAO{

    @Autowired
    protected ManufactureDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Manufacture getByName(String name) {
       return getEntityFromDBByParam("name",name,Manufacture.class);
    }
}
