package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import app.Evento;
import utils.JpaUtil;

public class EventoDAO {
    private EntityManagerFactory entityManagerFactory;

    public EventoDAO() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    public void save(Evento evento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(evento);
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

    public Evento getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Evento.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void delete(Evento evento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(evento);
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

    public void refresh(Evento evento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.refresh(evento);
        } finally {
            entityManager.close();
        }
    }
}
