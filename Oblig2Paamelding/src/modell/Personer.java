package modell;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "oblig2", name = "Personer")

public class Personer {

    private static final String MANN = "&#9794;";
    private static final String KVINNE = "&#9792;";

	/*
	 * Med betalingStatus, betalt = True.
	 */
    @Id
	private String mobil;
	private String fornavn;
	private String etternavn;
	private String kjonn;
	private boolean betalingStatus;
	
	
	/*
	 * Standard Constructor for Person.
	 */
	public Personer(){
		super();

		}

	public Personer(PersonSkjema ps) {
		super();
		this.fornavn = ps.getFornavn();
		this.etternavn = ps.getEtternavn();
		this.kjonn = ps.getKjonn();
		this.mobil = ps.getMobil();
		mobil = getMobilFormatert(mobil);
		
	}

    public String getKjonnsymbol() {
        return (getKjonn().equals("kvinne") ? KVINNE :  MANN);
    }
    public static String getMobilFormatert(String mob) {
    	if (mob != null && mob.length()==8) {
			mob = mob.substring(0, 3) + " " + mob.substring(3, 5) + " " + mob.substring(5, 8);
		}
        return mob;
    }

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	public String getmobil() {
		return mobil;
	}

	public void setmobil(String mobil) {
		this.mobil = mobil;
	}

	public boolean isBetalingStatus() {
		return betalingStatus;
	}

	public void setBetalingStatus(boolean betalingStatus) {
		this.betalingStatus = betalingStatus;
	}
	
	
}
