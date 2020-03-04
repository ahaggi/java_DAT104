package no.hib.dat104.lph;


import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BmiCalculatorFuncTest {
    
    private static final String HOST = "localhost";
    private static final String PORT = "8080";
    private static final String CONTEXT_ROOT = "F19BMIkalkulatorSpringMocksogMockito";
    private static final String BASE_URL 
            = "http://" + HOST + ":" + PORT + "/" + CONTEXT_ROOT;
    
    @Before
    public void setup() {
        setBaseUrl(BASE_URL);
    }

    @Test
    /** 
     * Test1: At siden med skjemaet vises ved foresp�rsel til "roten" av 
     * applikasjonen.
     */
    public void rotUrlSkalGiSkjemasiden() {
        beginAt("");
        
        assertTitleEquals("BMI-kalkulator");
        assertFormPresent();
    }
    
    @Test
    /** 
     * Test2: At inntasting av gyldige data og trykk p� Beregn gir 
     * resultatsiden.
     */
    public void gyldigInputOgBeregnSkalGiResultatsiden() {
        beginAt("bmiSkjema");
        
        setTextField("hoyde", "185");
        setTextField("vekt", "80");
        submit();

        assertTitleEquals("BMI-kalkulator resultat");
        assertTextPresent("Din BMI er");
    }
    
    
    @Test
    /** 
     * Test3: At resultatsiden inneholder korrekt utregnet BMI avrundet til 
     * �n desimal ved gyldig input.
     */
    public void resultatSkalVaereKorrektOgAvrundetTilEnDesimal() {
        beginAt("/bmiSkjema");
        
        setTextField("hoyde", "185");
        setTextField("vekt", "80");
        submit();

//        assertTextInElement("bmi", "23.4");
        assertMatchInElement("bmi", exactly("23.4"));
    }
    
    private String exactly(String string) {
        return "^" + string + "$";
    }
    
    @Test
    /**
     * Test4: At trykk p� Beregn uten � gi inn data gir skjemasiden med 
     * feilmeldingen "Du m� oppgi h�yde og vekt!"
     */
    public void manglendeInputSkalGiSkjemasidenMedFeilmelding() {
        beginAt("/bmiSkjema");
        
        submit();
        
        assertTitleEquals("BMI-kalkulator");
        assertTextInElement("feilmelding", "Du m� oppgi h�yde og vekt!");
     
        //Alternativt kan vi bruke ren JUnit til assertions, men vi ser jo at 
        //det blir mye mer verbose og kronglete...
        assertEquals("Du m� oppgi h�yde og vekt!", 
                getElementById("feilmelding").getTextContent());
    }
}
