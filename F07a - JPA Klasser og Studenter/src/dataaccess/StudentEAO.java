package dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Klasse;
import model.Student;

@Stateless
public class StudentEAO {
	
	@PersistenceContext(name = "studentPersistenceUnit")
	private EntityManager em;
	
	public void leggTilStudent(Student s) {
		em.persist(s);
	}
	
	public Student finnStudent(String id) {
		return em.find(Student.class, id);
	}
	
	public List<Student> finnStudentMedNavn(String nv) {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.navn = :nv", Student.class).setParameter("nv", nv);
		return query.getResultList();
	}
	
	public void oppdaterStudent(Student s) {
		em.merge(s);
	}
	
	public void slettStudent(String id) {
		em.remove(em.find(Student.class, id));
	}
	public Klasse finnKlasse(String id) {
		return em.find(Klasse.class, id);
	}

	public void byttKlasse(String studentid, String klassekode) {

		Student s = em.find(Student.class, studentid);
		Klasse k = em.find(Klasse.class, klassekode); 
//?		s.setKlasse(k);
//?		k.addStudent(s);
//? Ogs� slette fra gammel klasse?
//?		em.merge(s);
//?		em.merge(k);
//? flush() ..
	}

	public List<Student> alleStudenterIKlasse(String klassekode) {
		return em.find(Klasse.class, klassekode).getStudenter();
	}
	
	public List<Student> alleStudenterTotalt() {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
		return query.getResultList();
	}


}
