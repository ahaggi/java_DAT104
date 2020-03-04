package EAO;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modell.Cart;
import modell.Item;


@Stateless
public class EAO {
	
	@PersistenceContext(name = "handlelistenPersistenceUnit")
	private EntityManager em;
	
	public void leggTilItem(Item i) {
		em.persist(i)  ; 
	}
	
	
	public void slettItem(String id) {
		em.remove(em.find(Item.class, id));
	}
	
	

	
	public Cart alleItems() {
		Cart cart = new Cart();
		
		TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i", Item.class);
		 List<Item> items= query.getResultList();          
		// Hvorfor retunerer den ikke  NullPointerException??
		 
		 for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			 cart.leggTilItem( (Item) iterator.next());
	
		}
		 return cart;
	}



}
