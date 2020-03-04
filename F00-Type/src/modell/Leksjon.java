package modell;

import java.util.ArrayList;
import java.util.List;

public class Leksjon {
	List<Setning> setninger;

	public Leksjon() {
		super();
		this.setninger = new ArrayList<Setning>();
	}
	
	public void leggTilSetning (String s){
 			Setning setning = new Setning(s);
 			setninger.add(setning);
	}

	public List<Setning> getSetninger() {
		return setninger;
	}

	public void setSetninger(List<Setning> setninger) {
		this.setninger = setninger;
	}
	
}
