package modell;

import java.util.ArrayList;
import java.util.List;

public class Setning {
	List<String> ord;

	public Setning(String s) {
		super();
		this.ord = new ArrayList<String>();
		leggTil(s);
	}
	
	private void leggTil(String s){
	
		String [] ordSamling = s.split(" ");
		
		if (ordSamling!=null) 
			for (String o : ordSamling) 
				if(o!=null && o!="") ord.add(o);
		
	}
	
	
	public List<String> getOrd() {
		return ord;
	}

	public void setOrd(List<String> ord) {
		this.ord = ord;
	}
	
	
	

}
