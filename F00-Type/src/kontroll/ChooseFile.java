package kontroll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static kontroll.UrlMappings.*;

/**
 * Servlet implementation class Fil
 */
@WebServlet("/" + CHOOSEFILE_SERVLET)
public class ChooseFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> fileslist = new ArrayList<String>();
		String uploadsFolder = getServletContext().getInitParameter("uploadsFolder");
		File folder = new File(uploadsFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile())
				fileslist.add(listOfFiles[i].getName());
		}
		
		request.setAttribute("filesList", fileslist);
		request.getRequestDispatcher(CHOOSEFILE_JSP).forward(request, response);
  		request.getSession().removeAttribute("feilMelding");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filePath = getServletContext().getInitParameter("uploadsFolder");
		String fileName = request.getParameter("fileName");
		HttpSession session = request.getSession();

		String file = filePath + fileName.trim();
 		session.setAttribute("file", file);
		response.sendRedirect(TYPING_SERVLET);

	}

}
