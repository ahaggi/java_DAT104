package app.managed.dat101.way;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Student;

/**
 * NB! Denne koden er ikke kj�rbar i dette prosjektet. Den er kun ment som en illustrasjon p�
 *     hvordan application-managed entity-manager med transaksjoner ble gjort, f.eks. i DAT101.
 */
public class GoodOldMain {

	public static void main(String[] args) {

		Student student = new Student();
		//...
		leggTilStudent(student);
	}

	public static void leggTilStudent(Student s) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(s);
			tx.commit();

		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

	}

}
