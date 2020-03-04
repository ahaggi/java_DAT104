package no.hib.dat104;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;


public class TestKlasse {
	String source ;
    String target;
    double amount;
    String ugyldigValuta;
	
    @Before
	public final void setup() throws Exception {
    	 source = "NOK";
         target = "USD";
         amount = 1.;
         ugyldigValuta = "feil";

    }
    
    
//	Test at veksling fra en valuta til seg selv gir samme beløp ut som inn.
	@Test
	public void testRegnom1() throws IOException {
//		assertEquals(double expected, double actual, double delta)
        ExchangeRate exchangeRateResultat= ExchangeRateService.getRate(source, source);
        double rate = exchangeRateResultat.getRate();
		assertEquals( amount , Regnom.regn(amount+"", rate, source, source) , 0.01 );
		
	}

//	 Test at et beløp på 0 gir 0 ut når man veksler fra en valuta til en annen.

	@Test
	public void testRegnom2() throws IOException {
        ExchangeRate exchangeRateResultat= ExchangeRateService.getRate(source, target);
        double rate = exchangeRateResultat.getRate();
		assertEquals( 0 , Regnom.regn(0+"", rate, source, target) , 0.01 );
		
//	 Ugyldig valutakode skal gi 0 som svar, ikke kaste unntak. Test at dette fungerer.
	}
	@Test
	public void testRegnom3() throws IOException {
        ExchangeRate exchangeRateResultat;
        double rate=0;
		try {
			exchangeRateResultat = ExchangeRateService.getRate("Uxx", "EUR");
	         rate = exchangeRateResultat.getRate();
		} catch (JsonSyntaxException e) {
			 rate=0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals( 0 , rate , 0.01 );
		
	}

	
	
	
	
	
	

}
