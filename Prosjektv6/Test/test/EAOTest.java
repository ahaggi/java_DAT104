package test;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;

import org.eclipse.persistence.sessions.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import EAO.EAO;
import model.Lecturer;
import model.Survey;

import javax.ejb.EJB;
import javax.persistence.*;

/**
 * @author hhl19
 *
 */
public class EAOTest {
	// Aquire an EAO
	@EJB
	private EAO EAO;
	
	// State 
	
	private Lecturer lecturer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		survey = new Survey((Integer) 1, "Skoletest", "matteprøve", false, true, false, (long) 1000000,
//				(long) 1000000000);
//		interview = new Interview((Integer) 1, "hallo på do", survey);
//		lecturer = new Lecturer("Atle Patle", "eltaPeltA");
		survey = Mockito.mock(Survey.class);
		lecturer = Mockito.mock(Lecturer.class);
		eao = Mockito.mock(EAO.class);
		em = Mockito.mock(EntityManager.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		survey = null;
		lecturer = null;
		eao = null;
		em = null;
	}

	/**
	 * Test method for {@link EAO.EAO#addLecturer(model.Lecturer)}.
	 */
	@Test
	public void testAddLecturer() {

	}

	/**
	 * Test method for {@link EAO.EAO#updateLecturer(model.Lecturer)}.
	 */
	@Test
	public void testUpdateLecturer() {
		fail("Not yet implemented"); // TODO EAOTest UpdateLecturer
	}

	/**
	 * Test method for {@link EAO.EAO#deleteLecturer(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteLecturer() {
		fail("Not yet implemented"); // TODO EAOTest DeleteLecturer
	}

	/**
	 * Test method for {@link EAO.EAO#findLecturer(java.lang.String)}.
	 */
	@Test
	public void testFindLecturer() {
		// Mock the EntityManager to return our dummy element
		//Mockito.when(em.find(Lecturer.class, 1)).thenReturn(lecturer);

		// Mock the EAO to use our EntityManager
		//Mockito.when(eao.findLecturer("Atle Patle")).thenCallRealMethod();
		//Mockito.when(eao.getEntityManager()).thenReturn(em);

		// Perform the actual test
		assertTrue(lecturer.equals(eao.findLecturer("Atle Patle")));
		assertTrue(eao.findLecturer("perpålespen").equals(null));
	}

	/**
	 * Test method for {@link EAO.EAO#addSurvey(model.Survey)}.
	 */
	@Test
	public void testAddSurvey() {

	}

	/**
	 * Test method for {@link EAO.EAO#updateSurvey(model.Survey)}.
	 */
	@Test
	public void testUpdateSurvey() {
		fail("Not yet implemented"); // TODO EAOTest UpdateSurvey
	}

	/**
	 * Test method for {@link EAO.EAO#deleteSurvey(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSurvey() {
		fail("Not yet implemented"); // TODO EAOTest DeleteSurvey
	}

	/**
	 * Test method for {@link EAO.EAO#findSurvey(java.lang.Integer)}.
	 */
	@Test
	public void testFindSurvey() {
		// Mock the EntityManager to return our dummy element
		Mockito.when(em.find(Survey.class, 1)).thenReturn(survey);

		// Mock the EAO to use our EntityManager
		Mockito.when(eao.findSurvey(1)).thenCallRealMethod();
		Mockito.when(eao.getEntityManager()).thenReturn(em);

		// Perform the actual test
		assertTrue(survey.equals(eao.findSurvey(1)));
		assertTrue(eao.findSurvey(42).equals(null));
	}

}
