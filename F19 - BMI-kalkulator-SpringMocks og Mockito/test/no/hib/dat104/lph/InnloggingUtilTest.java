package no.hib.dat104.lph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;


public class InnloggingUtilTest {
	
    private MockHttpServletRequest fakeRequest = new MockHttpServletRequest();
    private MockHttpSession fakeSession = new MockHttpSession();
    
    /**
     * En bruker uten session ikke skal være innlogget
     */
    @Test
    public void enBrukerUtenSessionSkalIkkeVareInnlogget() {
    	fakeRequest.setSession(null);
    	assertFalse(InnloggingUtil.isInnlogget(fakeRequest));
    }
    
    /**
     * En bruker uten "innloggetBruker" i session ikke skal være innlogget
     */
    @Test
    public void enBrukerUtenInnloggetBrukerISessionSkalIkkeVareInnlogget() {
    	fakeRequest.setSession(fakeSession);
    	assertFalse(InnloggingUtil.isInnlogget(fakeRequest));
    }
    
    /**
     * En bruker med "innloggetBruker" i session skal være innlogget
     */
    @Test
    public void enBrukerMedInnloggetBrukerISessionSkalVareInnlogget() {
    	fakeRequest.setSession(fakeSession);
        fakeSession.setAttribute("innloggetBruker", "Knut");
    	assertTrue(InnloggingUtil.isInnlogget(fakeRequest));
    }
}




