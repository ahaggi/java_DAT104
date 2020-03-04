package modell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InnloggingUtil {
	

	public static void loggInnSom(HttpServletRequest request, String brukerMobil) {

		loggUt(request);
		HttpSession sesjon = request.getSession(true);
		sesjon.setAttribute("innloggetBruker", brukerMobil); //her er det lik "kasserer" eller deltager-MobilNr 
	}

	public static boolean isInnlogget(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		return (session != null)
				&& (session.getAttribute("innloggetBruker") != null) ;
	}

	public static boolean isInnloggetSomKasserer(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		return (session != null) 
				&& (session.getAttribute("innloggetBruker") != null) && (session.getAttribute("innloggetBruker").equals("kasserer"));
	}

	public static String harInnloggetSom(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		return isInnlogget(request) ?	(String) session.getAttribute("innloggetBruker") : null;
	}


	public static void loggUt(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

}
