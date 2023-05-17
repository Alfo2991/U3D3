package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import utils.JpaUtil;
import app.Partecipazione;

public class PartecipazioneDAO {
    private EntityManagerFactory entityManagerFactory;

    public PartecipazioneDAO() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    public void save(Partecipazione partecipazione) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(partecipazione);
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

    public Partecipazione getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Partecipazione.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void delete(Partecipazione partecipazione) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(partecipazione);
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

    public void refresh(Partecipazione partecipazione) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.refresh(partecipazione);
        } finally {
            entityManager.close();
        }
    }
}
