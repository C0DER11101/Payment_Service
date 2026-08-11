package com.jsp.payment.repository;

import com.jsp.payment.model.TransactionModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.math.BigInteger;

@Component
public class TransactionRepository {

    /*
    public TransactionRepository() {
        System.out.println(this.getClass().getSimpleName() + " object created");
    }
     */

    @PersistenceContext
    EntityManager entityManager; // PreparedStatement

    @Transactional // all the transaction is managed by spring transaction manager
    public void save(TransactionModel txModel) {
        /*
        Without the @Transational annotation the code of this method would have looked like this:

        try {
            EntityTransaction transaction = entityManager.getTransaction();
        } catch(IllegalStateException e) {
            transaction.rollback(); // explicitly undoing all the changes done to the table
            //e.printStackTrace();
        }
        transaction.commit(); // explicitly committing the changes so they get reflected in the database
         */

        entityManager.persist(txModel);
    }

    public TransactionModel findById(BigInteger altKey) {
        return entityManager.find(TransactionModel.class, altKey); // helpful link: https://thorben-janssen.com/jpa-getreference/
    }

    public List<TransactionModel> findAll() {
        // HQL query example: FROM TransactionModel;
        // SQL query: SELECT * FROM tx_transaction;
        // HQL is not SQL!!
        // This was required because EntityManager doesn't have a suitable method to retrieve all the records from a given database table
        Query entityData = entityManager.createQuery("from TransactionModel"); // `SELECT *` can be omitted!
        return entityData.getResultList();
    }

    public List<TransactionModel> findByPaymentMode(String paymentMode) {
        Query hqlQuery = entityManager.createQuery("from TransactionModel where paymentMode=:pM");
        hqlQuery.setParameter("pM", paymentMode);
        return hqlQuery.getResultList();
    }
}