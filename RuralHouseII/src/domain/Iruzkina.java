package domain;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Iruzkina implements Serializable {
	
	private RuralHouse rh;
	private Erabiltzailea erab;
	private Date data;
	private int puntuazioa; //gain 5
	private String komentarioa;
	
	public Iruzkina (RuralHouse r,Erabiltzailea e, Date d, int p, String k){
		this.rh=r;
		this.erab=e;
		this.data=d;
		this.puntuazioa=p;
		this.komentarioa=k;
	}
	
	public void setErabiltzailea (Erabiltzailea e){
		this.erab=e;
	}
	
	public void setDate (Date d){
		this.data=d;
	}
	
	public void setPunt (int p){
		this.puntuazioa=p;
	}
	
	public void setKomentarioa (String k){
		this.komentarioa=k;
	}
	
	public void setRH (RuralHouse r){
		this.rh=r;
	}
	
	public Erabiltzailea getErabiltzailea(){
		return this.erab;
	}
	
	public Date getData(){
		return this.data;
	}
	
	public int getPuntuazioa(){
		return this.puntuazioa;
	}
	
	public String getKomentarioa(){
		return this.komentarioa;
	}
	
	public RuralHouse getRH(){
		return this.rh;
	}
	
	public String toString(){
		return "Etxea:"+rh+" | "+"Erab:"+erab.getIzena()+" | "+"Data:"+data+" | "+"Punt:"+puntuazioa+"/5"+" | "+"Iritzia:"+komentarioa;
	}
	

}
