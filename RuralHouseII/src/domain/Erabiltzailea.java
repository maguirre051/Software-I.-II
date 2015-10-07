package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Erabiltzailea implements Serializable {
	
	private String Izena = "";
	private String Abizena = "";
	private String ePosta = "";
	private String Pasahitza = "";
	
	public Erabiltzailea (String iz, String ab, String e, String p){
		this.Izena=iz;
		this.Abizena=ab;
		this.ePosta=e;
		this.Pasahitza=p;
	}
	
	public Erabiltzailea(){}
	
	public String getIzena(){
		return this.Izena;
	}
	
	public String getAbizena(){
		return this.Abizena;
	}
	
	public String getEposta(){
		return this.ePosta;
	}
	
	public String getPasahitza(){
		return this.Pasahitza;
	}
	
	public void setIzena(String iz){
		this.Izena=iz;
	}
	
	public void setAbizena(String ab){
		this.Abizena=ab;
	}
	
	public void setEposta(String e){
		this.ePosta=e;
	}
	
	public void setPasahitza(String p){
		this.Pasahitza=p;
	}
	
	public String toString(){
		return Izena+" "+Abizena;
	}

}
