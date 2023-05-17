package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import utils.JpaUtil;
import app.Persona;

public class PersonaDAO {
    private EntityManagerFactory entityManagerFactory;

    public PersonaDAO() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    public void save(Persona persona) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Persona getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Persona.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void delete(Persona persona) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void refresh(Persona persona) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.refresh(persona);
        } finally {
            entityManager.close();
        }
    }
}
