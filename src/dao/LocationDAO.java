package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import utils.JpaUtil;
import app.Location;

public class LocationDAO {
    private EntityManagerFactory entityManagerFactory;

    public LocationDAO() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    public void save(Location location) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(location);
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

    public Location getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Location.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void delete(Location location) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(location);
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

    public void refresh(Location location) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.refresh(location);
        } finally {
            entityManager.close();
        }
    }
}
