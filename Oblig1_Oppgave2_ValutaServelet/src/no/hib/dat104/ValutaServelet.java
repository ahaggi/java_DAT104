package no.hib.dat104;


import static no.hib.dat104.UrlMappings.VALUTASERVLET_URL;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;



/**
 * Servlet implementation class ValutaServelet
 */
@WebServlet("/"+VALUTASERVLET_URL)
public class ValutaServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValutaServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String source = request.getParameter("source");
        String target = request.getParameter("target");
        String amount = request.getParameter("amount").replaceAll(",", ".");
        
        
//        {"success":true,
//    		"source":"USD",
//    		"target":"EUR",
//    		"rate":"0.8996300",
//    		"amount":0.9,
//    		"message":""}

       
		String resMelding="";

		if (Regnom.validate(amount) ){
	        ExchangeRate exchangeRateResultat;
	        double rate=0;
			try {
				exchangeRateResultat = ExchangeRateService.getRate("USD", "EUR");
		         rate = exchangeRateResultat.getRate();
			} catch (JsonSyntaxException e) {
				 rate=0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double veksletBelop= Regnom.regn(amount,rate, source, target )  ;

			NumberFormat formater = new DecimalFormat("#0.00");
	        resMelding= ""+formater.format(Double.parseDouble(amount)) + " "+ source +" blir "+ formater.format(veksletBelop) +" "+ target +" ,vekslingskurs: " +rate;
			
		}else{
			resMelding="Feil!  inndata er ikke et tall";
		}		
		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Valutaveksling</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Valutveksling resultat</h1>");
		out.println("<h3> "+resMelding+"</h3>");
		out.println("<a href=\"index.html\">En gang til</a>");
		out.println("</body>");
		out.println("</html>");

	
	
	
	}



}
