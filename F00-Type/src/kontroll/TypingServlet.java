package kontroll;

 import java.io.File;
import java.io.IOException;
 
 import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import modell.*;
 
import static kontroll.UrlMappings.*;

/**
 * Servlet implementation class Fil
 */
@WebServlet("/" + TYPING_SERVLET)
public class TypingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String fil = (String) request.getSession().getAttribute("file");
 		File file = new File(fil);
 		
		if (file.isFile()) {
			FilReader fr = new FilReader(fil);
			Leksjon leksjon = fr.lesFrafil();

	 		request.getSession().setAttribute("leksjon", leksjon);
	 		request.getRequestDispatcher(TYPING_JSP).forward(request, response);
	  		request.getSession().removeAttribute("file");

		} else {

			String feilMelding = "Ops Err! ,, choose another file";
			request.getSession().setAttribute("feilMelding", feilMelding);
			response.sendRedirect(CHOOSEFILE_SERVLET);

		}

		
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendRedirect(UPLOAD_SERVLET);
	}

}
