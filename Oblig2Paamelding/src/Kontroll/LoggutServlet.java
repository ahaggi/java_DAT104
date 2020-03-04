package Kontroll;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modell.InnloggingUtil;

import static modell.UrlMappings.*;
/**
 * Servlet implementation class LogginnServlet
 */
@WebServlet("/"+LOGGUT_SERVLET)
public class LoggutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forrigeSide = request.getHeader("Referer");
		String omdirigerTil ="home.html";
		if (forrigeSide.contains("/betalingsOversikt")) {
			omdirigerTil = "logginnKasserer";
		}else if (forrigeSide.contains("/deltagerliste")) {
			omdirigerTil = "logginn";
		}
			
		InnloggingUtil.loggUt(request);
		request.setAttribute("omdirigerTil", omdirigerTil);
		request.getRequestDispatcher(LOGGUT_JSP).forward(request, response);
		
		
//		request.getRequestDispatcher(LOGGUT_JSP).include(request, response);
//		response.sendRedirect(forrigeSide);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String referer = request.getHeader("Referer");
//		response.sendRedirect(referer);



	}

}
