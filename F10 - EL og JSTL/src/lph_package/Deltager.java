package lph_package;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deltager", schema = "obl2")
public class Deltager implements Comparable<Deltager> {
    
    private static final String MANN = "&#9794;";
    private static final String KVINNE = "&#9792;";
    
    private String mobil;
    private String fornavn;
    private String etternavn;
    private String kjonn;
    private boolean betalt = false;
    
    public Deltager() {}
    
    public Deltager(String mobil) {
        this(mobil, "N", "N", "m", false);
    }
    
    public Deltager(String mobil, String fornavn, String etternavn, String kjonn) {
        this(mobil, fornavn, etternavn, kjonn, false);
    }

    public Deltager(String mobil, String fornavn, String etternavn, String kjonn, boolean betalt) {
        setMobil(mobil);
        setFornavn(fornavn);
        setEtternavn(etternavn);
        setKjonn(kjonn);
        setBetalt(betalt);
    }
    
    @Id
    public String getMobil() {
        return mobil;
    }
    public void setMobil(String mobil) {
    	this.mobil = mobil;
    }

    public String getMobilFormatert() {
        return getMobil().substring(0,3) + " " + getMobil().substring(3,5)
                + " " + getMobil().substring(5,8);
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
    
    public String getFulltNavn() {
        return getFornavn() + " " + getEtternavn();
    }
    
    public String getKjonn() {
        return kjonn;
    }
    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }

    public String getKjonnsymbol() {
        return (getKjonn().equals("m") ? MANN : KVINNE);
    }
    
    public boolean isBetalt() {
        return betalt;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = betalt;
    }
    
    @Override
    public boolean equals(Object obj) {
        Deltager other = (Deltager) obj;
        return this.getMobil().equals(other.getMobil());
    }

    @Override
    public int compareTo(Deltager that) {
        return this.getFulltNavn().compareTo(that.getFulltNavn());
    }
}
