package domain;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Txangoa implements Serializable{
	
	private RuralHouse rh;
	private String nora;
	private int iraupena; //minututan
	private Vector<Erabiltzailea> apuntatuak = new Vector<Erabiltzailea>();
	
	public Txangoa(RuralHouse r, String n, int i){
		this.rh=r;
		this.nora=n;
		this.iraupena=i;
	}
	
	public RuralHouse getRH(){
		return this.rh;
	}
	
	public String getNora(){
		return this.nora;
	}
	
	public int getIraupena(){
		return this.iraupena;
	}
	
	public Vector<Erabiltzailea> getApuntatuak(){
		return this.apuntatuak;
	}
	
	public void setRH(RuralHouse r){
		this.rh=r;
	}
	
	public void setNora(String s){
		this.nora=s;
	}
	
	public void setIraupena(int i){
		this.iraupena=i;
	}
	
	public void setApuntatuak(Vector<Erabiltzailea> v){
		this.apuntatuak=v;
	}

	
	public String toString(){
		return rh+":"+" "+nora+" | "+"Iraupena:"+" "+iraupena+"min.";
	}

}
