package abc;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		
		Map<String, String> foreleserIFag = new HashMap<>();
		
		foreleserIFag.put("DAT104", "Lars-Petter");
		foreleserIFag.put("DAT103", "Atle");
		foreleserIFag.put("MAT102", "Jon Eivind");
		
		String aktueltFag = "MAT102";
		System.out.printf("Foreleser i %s er %s %n", aktueltFag, foreleserIFag.get(aktueltFag));
	}

}
