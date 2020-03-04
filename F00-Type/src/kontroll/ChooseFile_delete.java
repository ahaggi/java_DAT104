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
@WebServlet("/" + CHOOSEFILE_DELETE_SERVLET)
public class ChooseFile_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

 		String fileNameParameter = request.getParameter("fileName");
 		
		String uploadsFolder = getServletContext().getInitParameter("uploadsFolder");
		File folder = new File(uploadsFolder);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().equals(fileNameParameter))
				listOfFiles[i].delete();
		}
		response.sendRedirect(CHOOSEFILE_SERVLET);

	}

}
