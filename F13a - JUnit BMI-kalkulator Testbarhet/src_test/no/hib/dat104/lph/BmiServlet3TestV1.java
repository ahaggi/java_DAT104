package no.hib.dat104.lph;

import static org.junit.Assert.*;

import org.junit.Test;

public class BmiServlet3TestV1{

    @Test
    public void bmiPaaAttenKommaFireSkalVaereUndervekt() {
        BmiServlet3 s3 = new BmiServlet3();
        assertEquals("undervekt", s3.beregnVektklasse(18.4));
    }

    @Test
    public void bmiPaaAttenKommaFemSkalVaereNormalvekt() {
        BmiServlet3 s3 = new BmiServlet3();
        assertEquals("normalvekt", s3.beregnVektklasse(18.5));
    }
    
    //+ mange flere tester på denne metoden
    
    @Test
    public void avrundingAvKomma45SkalVaereKomma5() {
        BmiServlet3 s3 = new BmiServlet3();
        assertEquals(10.5, s3.rundAvTilEnDesimal(10.45), Double.MIN_VALUE);
    }

    @Test
    public void avrundingAvKomma44SkalVaereKomma4() {
        BmiServlet3 s3 = new BmiServlet3();
        assertEquals(10.4, s3.rundAvTilEnDesimal(10.44), Double.MIN_VALUE);
    }

    //+ flere tester på denne metoden
    
    //osv ... på de andre metodene
    
    

}
