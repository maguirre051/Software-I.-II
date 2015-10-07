package domain;

import java.io.Serializable;
import java.util.Vector;



@SuppressWarnings("serial")
public class Owner implements Serializable {

	//private String bankAccount = "";
	private String izena="";
	private String abizena="";
	private String ePosta="";
	private String pasahitza="";
	
	private Vector<RuralHouse> RuralHouses = new Vector<RuralHouse>();


	public Owner(String name,String ab, String e, String password){
		this.izena=name;
		this.abizena=ab;
		this.ePosta=e;
		this.pasahitza=password;		
	}
	
	/**
	 * This method returns the name
	 * 
	 * @return owner name
	 */
	public String getName() {
		return this.izena;
	}

	/**
	 * This method sets the owner name 
	 * 
	 * @param name
	 *            owner name
	 */
	public void setName(String name) {
		this.izena= name;
	}
	
	/**
	 * This method returns the owner login
	 * 
	 * @return The owner login
	 */

	
	/**
	 * This method returns the owner login
	 * 
	 * @return The owner login
	 */
	public String getAbizena() {
		return this.abizena;
	}

	/**
	 * This method sets the owner login 
	 * 
	 * @param login
	 *            the owner login
	 */
	public void setAbizena(String login) {
		this.abizena = login;
	}
	
	/**
	 * This method returns the owner password
	 * 
	 * @return The owner login
	 */
	public String getPassword() {
		return this.pasahitza;
	}

	/**
	 * This method sets the owner password 
	 * 
	 * @param password
	 *            the owner password
	 */
	public void setPassword(String password) {
		this.pasahitza = password;
	}
	
	public String getEposta(){
		return this.ePosta;
	}
	
	public void setEposta(String ep){
		this.ePosta = ep;
	}
	
	/**
	 * This method obtains an owner's(userId) rural houses 
	 * 
	 * @param userId
	 *            user key
	 * @return a vector of Rural Houses
	 */
	
	public Vector<RuralHouse> getRuralHouses() {
		return RuralHouses;
	}
	
	public void setRHouses (Vector<RuralHouse> v){
		this.RuralHouses = v;
	}
	
	public RuralHouse addRuralHouse(/*int hn,*/ String description, String city) {
     RuralHouse rh=new RuralHouse(/*hn,*/ this,  description,  city);
	 RuralHouses.add(rh);
	 return rh;
	}
	
//	public Vector<Txangoa> getTxangoak(){
//		
//	}
	
	public String toString(){
		return izena+" "+abizena+" "+RuralHouses;
	}
	
}