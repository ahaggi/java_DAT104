package no.hib.dat104.lph;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BmiServletTestUsingMockito {

	@Mock HttpServletRequest stubRequest;
	@Mock HttpServletResponse stubResponse;
	@Mock RequestDispatcher stubRequestDispatcher;
	
	BmiResultatController bmiServlet;
    
    @Before
    public void fellesOppsettForAlleTestene() {
        bmiServlet = new BmiResultatController();
        
        when(stubRequest.getRequestDispatcher(any(String.class)))
        		.thenReturn(stubRequestDispatcher);
    }
    
    @Test
    public void feilmeldingSkalLagresIRequestScopeHvisParametreIkkeErHeltall() 
            throws ServletException, IOException {

    	when(stubRequest.getParameter("hoyde")).thenReturn("x");
    	when(stubRequest.getParameter("vekt")).thenReturn("y");
    	
        bmiServlet.doGet(stubRequest, stubResponse);
        
        verify(stubRequest).setAttribute(eq("feilmelding"), any(String.class));
    }

    @Test
    public void feilmeldingSkalLagresIRequestScopeHvisUgyldigeParametre() 
            throws ServletException, IOException {
        
    	when(stubRequest.getParameter("hoyde")).thenReturn("0");
    	when(stubRequest.getParameter("vekt")).thenReturn("0");
    	
        bmiServlet.doGet(stubRequest, stubResponse);
        
        verify(stubRequest).setAttribute(eq("feilmelding"), any(String.class));
    }
    
    @Test
    public void ingenFeilmeldingSkalLagresIRequestScopeHvisOkParametre() 
            throws ServletException, IOException {
        
    	when(stubRequest.getParameter("hoyde")).thenReturn("185");
    	when(stubRequest.getParameter("vekt")).thenReturn("80");
    	
        bmiServlet.doGet(stubRequest, stubResponse);
        
        verify(stubRequest, never()).setAttribute(eq("feilmelding"), any(String.class));
    }
    
    @Test
    public void atDetGjoresEnForwardTilKorrektJsp() 
            throws ServletException, IOException {
        
        bmiServlet.doGet(stubRequest, stubResponse);
        
        verify(stubRequest).getRequestDispatcher("bmi5.jsp");
        verify(stubRequestDispatcher).forward(stubRequest, stubResponse);
    }
    
}
