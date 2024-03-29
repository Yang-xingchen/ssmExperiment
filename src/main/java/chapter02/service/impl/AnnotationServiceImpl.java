package chapter02.service.impl;

import chapter02.dao.Dao;
import chapter02.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service("service")
public class AnnotationServiceImpl implements Service {

    private Dao dao;

    public AnnotationServiceImpl(){
        System.out.println("AnnotationServiceImpl create");
    }

    @Autowired
    @Qualifier("dao")
    public AnnotationServiceImpl setDao(Dao dao) {
        System.out.println("setDao invoke");
        this.dao = dao;
        return this;
    }

    @Override
    public String say() {
        return dao.get();
    }
}
