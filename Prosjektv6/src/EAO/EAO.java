package EAO;

import javax.ejb.Stateless;
import javax.persistence.*;

import model.*;

@Stateless
public class EAO {
	@PersistenceContext(name = "PersistenceUnit")
	private EntityManager em;
	/**
	 * Makes an Lecturer object managed and persistent.
	 * 
	 * @param Lecturer
	 *            l
	 * @Throws: EntityExistsException - if the entity already exists. (If the
	 *          entity already exists, the EntityExistsException may be thrown
	 *          when the persist operation is invoked, or the
	 *          EntityExistsException or another PersistenceException may be
	 *          thrown at flush or commit time.) IllegalArgumentException - if
	 *          the instance is not an entity TransactionRequiredException - if
	 *          invoked on a container-managed entity manager of type
	 *          PersistenceContextType.TRANSACTION and there is no transaction
	 */
	public void addLecturer(Lecturer l) {
		try {
			em.persist(l);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Updates the managed entity by merging the state of the given entity into
	 * the current persistence context.
	 *
	 * @param: Lecturer
	 *             object l
	 *
	 * @return the managed instance that the state was merged to
	 *
	 * @throws IllegalArgumentException
	 *             - if instance is not an entity or is a removed entity
	 *             TransactionRequiredException - if invoked on a
	 *             container-managed entity manager of type
	 *             PersistenceContextType.TRANSACTION and there is no
	 *             transaction
	 */
	public void updateLecturer(Lecturer l) {
		try {
			em.merge(l);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes and delete the data of the managed entity instance referenced by
	 * the primary key.
	 * 
	 * Calls find(Object instance) so it can also throw exceptions from this
	 * method.
	 * 
	 * @param String
	 *            navn
	 *
	 * @throws IllegalArgumentException
	 *             - if the instance is not an entity or is a detached entity
	 *             TransactionRequiredException - if invoked on a
	 *             container-managed entity manager of type
	 *             PersistenceContextType.TRANSACTION and there is no
	 *             transaction
	 */
	public void deleteLecturer(String navn) {
		try {
			em.remove(em.find(Lecturer.class, navn));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Find by primary key. Search for an Lecturer entity with the specified
	 * primary key. If the entity instance is contained in the persistence
	 * context, it is returned from there.
	 *
	 * @param String
	 *            navn
	 *
	 * @return the found entity instance or null if the entity does not exist
	 *
	 * @throws IllegalArgumentException
	 *             - if the first argument does not denote an entity type or the
	 *             second argument is is not a valid type for that entity�s
	 *             primary key or is null
	 */
	public Lecturer findLecturer(String username) {
		if (username!=null) {
			return em.find(Lecturer.class, username);
		} else {
			// invalid username return null.
			return null;
		}
	}

	/**
	 * Updates the managed entity by merging the state of the given entity into
	 * the current persistence context.
	 *
	 * @param: Survey
	 *             object s
	 *
	 * @return the managed instance that the state was merged to
	 *
	 * @throws IllegalArgumentException
	 *             - if instance is not an entity or is a removed entity
	 *             TransactionRequiredException - if invoked on a
	 *             container-managed entity manager of type
	 *             PersistenceContextType.TRANSACTION and there is no
	 *             transaction
	 */
	public void updateSurvey(Survey s) {
		try {
			em.merge(s);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes and delete the data of the managed entity instance referenced by
	 * the primary key.
	 * 
	 * Calls find(Object instance) so it can also throw exceptions from this
	 * method.
	 * 
	 * @param Integer
	 *            id
	 *
	 * @throws IllegalArgumentException
	 *             - if the instance is not an entity or is a detached entity
	 *             TransactionRequiredException - if invoked on a
	 *             container-managed entity manager of type
	 *             PersistenceContextType.TRANSACTION and there is no
	 *             transaction
	 */
	public void deleteSurvey(Integer id, String username) {
		Lecturer l = this.findLecturer(username);
		if (id == null || l == null) {
			System.out.println("Unable to delete, id or lecturer was null.");
			return;
		}
		for (int i = 0; i < l.getSurveys().size(); i++) {
			if (l.getSurveys().get(i).getId().equals(id)) {
				
				em.remove(l.getSurveys().remove(i));
				this.updateLecturer(l);
			}
		}
	}

	/**
	 * Find by primary key. Search for an Survey entity with the specified
	 * primary key. If the entity instance is contained in the persistence
	 * context, it is returned from there.
	 *
	 * @param Integer
	 *            id
	 *
	 * @return the found entity instance or null if the entity does not exist
	 *
	 * @throws IllegalArgumentException
	 *             - if the first argument does not denote an entity type or the
	 *             second argument is is not a valid type for that entity�s
	 *             primary key or is null
	 */
	public Survey findSurvey(Integer id) {
		try {
			return em.find(Survey.class, id);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
