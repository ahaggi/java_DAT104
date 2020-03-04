package no.hib.dat104;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Melding {

	private Integer id;

	private Long tidsstempel;
	private String avsender;
	private String tekst;

	public Melding() {
	}

	public Melding(long tidsstempel, String avsender, String tekst) {
		this.tidsstempel = tidsstempel;
		this.avsender = avsender;
		this.tekst = tekst;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTidsstempel() {
		return tidsstempel;
	}

	public void setTidsstempel(long tidsstempel) {
		this.tidsstempel = tidsstempel;
	}

	public String getAvsender() {
		return avsender;
	}

	public void setAvsender(String avsender) {
		this.avsender = avsender;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getFormatertTidsstempel() {
		Locale norsk = new Locale("no", "NO");
		DateFormat format = new SimpleDateFormat("d. MMMM HH:mm", norsk);
		Date stamp = new Date(tidsstempel);
		return format.format(stamp);
	}

	@Override
	public String toString() {
		return String.format("(#%d) %s skrev %s: %s", id,
				getFormatertTidsstempel(), avsender, tekst);
	}

}
