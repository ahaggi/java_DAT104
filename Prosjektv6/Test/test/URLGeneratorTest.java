package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utility.URLGenerator;

public class URLGeneratorTest extends URLGenerator {

	@Test
	public void testURL() {
		for (int i = 0; i<50; i++){
			//lag en url
			String url =  URLGenerator.generateUrl("" + i + "");
			// konverter tilbake til id
			int id = URLGenerator.findSurveyId(url);
			// sjekk at start id er lik slutt id
			assertTrue(id == i);
		}
	}
}


