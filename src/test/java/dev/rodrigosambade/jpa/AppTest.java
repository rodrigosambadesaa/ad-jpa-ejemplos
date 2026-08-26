package dev.rodrigosambade.jpa;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AppTest {@Test void mappingsPersistBothOriginalExampleDomains(){try(var emf=Persistence.createEntityManagerFactory("examples")){var em=emf.createEntityManager();var tx=em.getTransaction();tx.begin();var department=new Department("I+D");em.persist(department);em.persist(new Employee("Rodrigo",department));var professor=new Professor("Eva");professor.addEmail("eva@example.test");em.persist(professor);tx.commit();assertEquals(1L,em.createQuery("select count(e) from Employee e",Long.class).getSingleResult());assertEquals(1L,em.createQuery("select count(e) from EmailAddress e",Long.class).getSingleResult());em.close();}}}
