package dev.rodrigosambade.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void mappingsPersistBothOriginalExampleDomains() {
        try (EntityManagerFactory entityManagerFactory =
                     Persistence.createEntityManagerFactory("examples")) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                persistFixtures(entityManager);

                assertEquals(1L, count(entityManager, "Employee"));
                assertEquals(1L, count(entityManager, "EmailAddress"));
            } finally {
                entityManager.close();
            }
        }
    }

    private static void persistFixtures(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Department department = new Department("I+D");
            entityManager.persist(department);
            entityManager.persist(new Employee("Rodrigo", department));

            Professor professor = new Professor("Eva");
            professor.addEmail("eva@example.test");
            entityManager.persist(professor);

            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    private static long count(EntityManager entityManager, String entityName) {
        return entityManager
                .createQuery("select count(e) from " + entityName + " e", Long.class)
                .getSingleResult();
    }
}
