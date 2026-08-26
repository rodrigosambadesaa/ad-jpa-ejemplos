package dev.rodrigosambade.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory =
                     Persistence.createEntityManagerFactory("examples")) {
            persistExamples(entityManagerFactory);
        }
    }

    private static void persistExamples(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Department department = new Department("Informática");
            entityManager.persist(department);
            entityManager.persist(new Employee("Ana", department));

            Professor professor = new Professor("Pablo");
            professor.addEmail("pablo@example.test");
            entityManager.persist(professor);

            transaction.commit();
        } catch (RuntimeException exception) {
            rollbackIfActive(transaction);
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    private static void rollbackIfActive(EntityTransaction transaction) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }
}
