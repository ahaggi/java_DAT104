package modell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FilReader {
	
	
	
 	private String filnavn;

	
	public FilReader(String filnavn) {
		super();
 		this.filnavn = filnavn;
 	}


//	filnavn = JOptionPane.showInputDialog("Filnavn i mappen "+ MAPPE_STR);

	public Leksjon lesFrafil()throws FileNotFoundException, IOException{
		Leksjon lek =  new Leksjon();
				BufferedReader reader = new BufferedReader(new FileReader(filnavn));

				String linje = reader.readLine();

				while (linje != null) {
					linje = linje.trim();
					lek.leggTilSetning(linje);
					linje = reader.readLine();
				}
				reader.close();
 
		 
		
		return lek;

	}

}
