package EAO;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modell.Personer;


@Stateless
public class EAO {
	
	@PersistenceContext(name = "personPersistenceUnit")
	private EntityManager em;
	
	public void leggTilPerson(Personer p) {
		em.persist(p)  ; 
	}
	
	public void oppdaterPerson(Personer p) {
		em.merge(p)  ; 
	}

	
	public void slettPerson(String mob) {
		em.remove(em.find(Personer.class, mob));
	}
	
	public Personer finnPerson(String mob){
		return em.find(Personer.class, mob);
	}

	public boolean erRegisterertFraFoer(String mob){
	return finnPerson(Personer.getMobilFormatert(mob))!=null;

	}

	public List<Personer> allePersoner() {

		
		TypedQuery<Personer> query = em.createQuery("SELECT i FROM Personer i ORDER BY  i.fornavn, i.etternavn, i.mobil", Personer.class);
		 List<Personer> personer= query.getResultList();          

		 return personer;
	}



}
