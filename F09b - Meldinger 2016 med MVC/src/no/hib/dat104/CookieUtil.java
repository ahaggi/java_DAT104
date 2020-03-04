package no.hib.dat104;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static String getAvsenderCookie(HttpServletRequest request)
			throws UnsupportedEncodingException {

		String avsender = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("avsender")) {
					avsender = cookie.getValue();
					avsender = URLDecoder.decode(avsender, "ISO-8859-1");
				}
			}
		}
		return avsender;
	}

	public static void setAvsenderCookie(HttpServletResponse response,
			String mottattAvsender) throws UnsupportedEncodingException {

		String cookieverdi = URLEncoder.encode(mottattAvsender, "ISO-8859-1");
		Cookie cookie = new Cookie("avsender", cookieverdi);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(cookie);
	}
}
