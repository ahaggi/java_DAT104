package lph;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nullpointercrash")
public class MyNullPointerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String s = null;
		s.length();
	}
}
