package com.lemzki.tools.netcast.repo;

import com.lemzki.tools.netcast.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional("netcastTransactionManager")
public class MessageDao {


    private static final String INSERT_QUERY = "INSERT INTO MESSAGE(NUMBER, MESSAGE) VALUES (:number, :message);";

    @PersistenceContext(unitName="netcast")
    EntityManager entityManager;

    public void insert(Message message){
       entityManager.createNativeQuery(INSERT_QUERY)
            .setParameter("number", message.getNumber())
            .setParameter("message", message.getMessage())
            .executeUpdate();
    }
}
