package kontroll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import static kontroll.UrlMappings.*;

/**
 * Servlet implementation class Fil
 */
@WebServlet("/" + UPLOAD_SERVLET)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(UPLOAD_JSP).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		File file;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		String filePath = getServletContext().getInitParameter("uploadFolder");
		String tempFolder = getServletContext().getInitParameter("tempFolder");

		// Verify the content type
		String contentType = request.getContentType();
		// Returns the index within this string of the first occurrence of the
		// specified substring, If NO such value of "multipart/form-data" exists, then -1 is returned
		if ((contentType.indexOf("multipart/form-data") >= 0)) { //contentType.contains("noe")
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File(tempFolder));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);
			ArrayList<String> uploadedFiles = new ArrayList<String>();
			try {
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();
 
				while (i.hasNext()) {
					FileItem fi = i.next();
					if (!fi.isFormField()) { //true if the instance represents a simple form field; false if it represents an uploaded file.
						// Get the uploaded file parameters
//						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
//						boolean isInMemory = fi.isInMemory();
//						long sizeInBytes = fi.getSize();
						// Write the file
						if (fileName.lastIndexOf("/") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("/")));
						} else {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("/") + 1));
						}
						fi.write(file);
						uploadedFiles.add("Uploaded Filename: " + filePath + fileName );
 					}
 
				}
 			} catch (Exception ex) {
				System.out.println(ex);
			}
			request.getSession().setAttribute("uploadedFiles", uploadedFiles);
		} else {
			String feilMelding = "No file uploaded";
			request.getSession().setAttribute("feilMelding", feilMelding);
		}
		response.sendRedirect(RES_SERVLET);

	}

}
