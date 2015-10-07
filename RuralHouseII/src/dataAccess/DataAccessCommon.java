package dataAccess;


import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import configuration.ConfigXML;
import domain.Booking;
import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.Txangoa;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingErabiltzaileaExists;
import exceptions.OverlappingOfferExists;
import exceptions.OverlappingOwnerExists;
import exceptions.OverlappingTxangoaExists;

public class DataAccessCommon implements DataAccessInterface {
	protected static ObjectContainer  db;
	private int bookingNumber=0; // if it is "static" then it is not serialized
	private int offerNumber=0;   // if it is "static" then it is not serialized

	protected static DB4oManagerAux theDB4oManagerAux;
	ConfigXML c;

	public DataAccessCommon()  {
		theDB4oManagerAux=new DB4oManagerAux(0,0);
		c=ConfigXML.getInstance();
		System.out.println("Creating DB4oManager instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
		}


	
	 class DB4oManagerAux {
		int bookingNumber;
		int offerNumber;
		DB4oManagerAux(int bookingNumber,int offerNumber){
			this.bookingNumber=bookingNumber;
			this.offerNumber=offerNumber;
		}
	}


	
	
	public void initializeDB(){
		
		System.out.println("Jabeak:"+getOwners());
		System.out.println("Erabiltzaileak:"+getErabiltzaileak());
		
//		db.commit();

		 /*Owner jon = new Owner("Jon", "Lopez","jon@gmail.com", "passJon");
		 Owner alfredo = new Owner("Alfredo","Garcia","alfr@yh.com", "passAlfredo");
		 Owner jesus = new Owner("Jes�s", "de Pedro","jss@htm.es", "passJesus");
		 Owner josean = new Owner("Josean","Tolosa", "jt@gmail.com", "passJosean");
		 
		 jon.addRuralHouse(1, "Ezkioko etxea","Ezkio");
	     jon.addRuralHouse(2, "Etxetxikia","Iru�a");
	     jesus.addRuralHouse(3, "Udaletxea","Bilbo");
	     josean.addRuralHouse(4, "Gaztetxea","Renteria");
		 
		 db.store(jon);
		 db.store(alfredo);
		 db.store(jesus);
		 db.store(josean);
		 
		 db.commit();*/
	}
	
	public Owner storeOwner(Owner o){
		db.store(o);
		db.commit();
		return o;
	}
	
	public Txangoa storeTxangoa(Txangoa t){
		db.store(t);
		db.commit();
		return t;
	}
	
	public Owner createOwner(String iz, String ab, String ep, String p){
		Owner proto = new Owner (iz,ab,ep,p);
		//ObjectSet<Owner> result = db.queryByExample(proto);
		//Owner ow= result.next();
		//db.store(theDB4oManagerAux);
		db.store(proto);
		db.commit();
		return proto;
	}
	
	public Erabiltzailea createErabiltzailea(String iz, String ab, String ep, String p){
		Erabiltzailea proto = new Erabiltzailea (iz,ab,ep,p);
		//ObjectSet<Erabiltzailea> result = db.queryByExample(proto);
		//Erabiltzailea er= result.next();
		//db.store(theDB4oManagerAux);
		db.store(proto);
		db.commit();
		return proto;
	}
	
	public Iruzkina createIruzkina(RuralHouse r,Erabiltzailea e, Date d, int p, String k){
		Iruzkina proto = new Iruzkina (r,e,d,p,k);
		db.store(proto);
		db.commit();
		return proto; 
	}
	
	public Txangoa createTxangoa(RuralHouse r, String n, int i){
		Txangoa proto = new Txangoa (r,n,i);
		db.store(proto);
		db.commit();
		return proto;
	}
	
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {

	try {
		
		//if (c.isDatabaseLocal()==false) openObjectContainer();
		
		
		RuralHouse proto = new RuralHouse(/*ruralHouse.getHouseNumber(),*/null,null,null);
		ObjectSet<RuralHouse> result = db.queryByExample(proto);
		RuralHouse rh=result.next();
		Offer o=rh.createOffer(theDB4oManagerAux.offerNumber++,firstDay, lastDay, price);
		db.store(theDB4oManagerAux); // To store the new value for offerNumber
		db.store(o);
		db.commit(); 
		return o;
	}
	catch (com.db4o.ext.ObjectNotStorableException e){
		System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createOffer");
		return null;
	}
	}
	

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {
		
		try {

			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse proto = new RuralHouse(/*ruralHouse.getHouseNumber(),*/null,ruralHouse.getDescription(),ruralHouse.getCity());
			 ObjectSet<RuralHouse> result = db.queryByExample(proto);
			 RuralHouse rh=result.next();

			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer!=null) {
				offer.createBooking(theDB4oManagerAux.bookingNumber++, bookTelephoneNumber);
				db.store(theDB4oManagerAux); // To store the new value for bookingNumber
				db.store(offer);
				db.commit();
				return offer.getBooking();
			}
			return null;

		} catch (com.db4o.ext.ObjectNotStorableException e){
				System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createBooking");
				return null;
			}
		catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners()  {
		

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Owner proto = new Owner(null,null,null,null);
			 ObjectSet<Owner> result = db.queryByExample(proto);
			 Vector<Owner> owners=new Vector<Owner>();
			 while(result.hasNext())				 
				 owners.add(result.next());
			 return owners;
	     } finally {
	         //db.close();
	     }
	} 
	
	
	public Vector<Erabiltzailea> getErabiltzaileak()  {
		

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Erabiltzailea proto = new Erabiltzailea(null,null,null,null);
			 ObjectSet<Erabiltzailea> result = db.queryByExample(proto);
			 Vector<Erabiltzailea> erabiltzaileak=new Vector<Erabiltzailea>();
			 while(result.hasNext())				 
				 erabiltzaileak.add(result.next());
			 return erabiltzaileak;
	     } finally {
	         //db.close();
	     }
	} 
	
	
	public void ezabatuJabea(String iz, String ab, String p){
		Owner proto = new Owner(null,null,null,null);
		ObjectSet<Owner> result = db.queryByExample(proto);
		//Boolean b=false;
		for(int i=0; i<result.size(); i++){
			if (result.get(i).getName().equals(iz) && result.get(i).getAbizena().equals(ab) && result.get(i).getPassword().equals(p)){
				db.delete(result.get(i));
				//result.remove(result.get(i));
				System.out.println(db);
				//b=true;
			}
		}
		//if (!b){System.out.println("Ez da aurkitu ezabatzekoa");}
	}
	
	public void ezabatuErabiltzailea(String iz, String ab, String p){
		Erabiltzailea proto = new Erabiltzailea(null,null,null,null);
		ObjectSet<Erabiltzailea> result = db.queryByExample(proto);
		for(int i=0; i<result.size(); i++){
			if (result.get(i).getIzena().equals(iz) && result.get(i).getAbizena().equals(ab) && result.get(i).getPasahitza().equals(p)){
				db.delete(result.get(i));
				//result.remove(result.get(i));
				System.out.println(db);
			}
		}
	}	
		
	public void ezabatuIruzkina(RuralHouse r,Erabiltzailea e, Date d){
		Iruzkina proto = new Iruzkina(null,null,null,0,null);
		ObjectSet<Iruzkina> result = db.queryByExample(proto);
		for(int i=0; i<result.size(); i++){
			if (result.get(i).getRH().equals(r) && result.get(i).getErabiltzailea().equals(e) && result.get(i).getData().equals(d)){
				db.delete(result.get(i));
				//result.remove(result.get(i));
				System.out.println(db);
			}
		}	
	}
	
	public void ezabatuEtxea(String city){
		RuralHouse proto = new RuralHouse(null,null,null);
		ObjectSet<RuralHouse> result = db.queryByExample(proto);
		for(int i=0; i<result.size(); i++){
			if (result.get(i).getCity().equals(city)){
				db.delete(result.get(i));
				//result.remove(result.get(i));
				System.out.println(db);
			}
		}	
	}
	
	public void ezabatuTxangoa(Txangoa t){
//		ObjectSet<Txangoa> result = db.queryByExample(t);
//		db.delete(result);
		Txangoa proto = new Txangoa(null,null,0);
		ObjectSet<Txangoa> result = db.queryByExample(proto);
		for(int i=0; i<result.size(); i++){
			if (result.get(i).equals(t)){
				db.delete(result.get(i));
				//result.remove(result.get(i));
				System.out.println(db);
			}
		}	
	}
	

	public Vector<RuralHouse> getAllRuralHouses() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 RuralHouse proto = new RuralHouse(/*0,*/null,null,null);
			 ObjectSet<RuralHouse> result = db.queryByExample(proto);
			 Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			 while(result.hasNext()) 
				 ruralHouses.add(result.next());
			 return ruralHouses;
	     } finally {
	         //db.close();
	     }
	}
	
	public Vector<Iruzkina> getAllIruzkina() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Iruzkina proto = new Iruzkina(null,null,null,0,null);
			 ObjectSet<Iruzkina> result = db.queryByExample(proto);
			 Vector<Iruzkina> iruzkinak=new Vector<Iruzkina>();
			 while(result.hasNext()) 
				 iruzkinak.add(result.next());
			 return iruzkinak;
	     } finally {
	         //db.close();
	     }
	}
	public Vector<Booking> getAllBooking() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Booking proto = new Booking(0,null,null);
			 ObjectSet<Booking> result = db.queryByExample(proto);
			 Vector<Booking> booking=new Vector<Booking>();
			 while(result.hasNext()) 
				 booking.add(result.next());
			 return booking;
	     } finally {
	         //db.close();
	     }
	}
	
	public Vector<Txangoa> getAllTxangoak() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Txangoa proto = new Txangoa(null,null,0);
			 ObjectSet<Txangoa> result = db.queryByExample(proto);
			 Vector<Txangoa> txangoak=new Vector<Txangoa>();
			 while(result.hasNext()) 
				 txangoak.add(result.next());
			 return txangoak;
	     } finally {
	         //db.close();
	     }
	}
	
	public Vector<Txangoa> getTxangoakE(String i, String a) {
		 try {
			 Txangoa proto = new Txangoa(null,null,0);
			 ObjectSet<Txangoa> result = db.queryByExample(proto);
			 Vector<Txangoa> txangoak=new Vector<Txangoa>();
			 for (int k=0; k<result.size(); k++){
				 for ( int j=0; j<result.get(k).getApuntatuak().size(); j++){
					if (result.get(k).getApuntatuak().get(j).getIzena().equals(i) && result.get(k).getApuntatuak().get(j).getAbizena().equals(a)){
						 txangoak.add(result.get(k));
					}
				}
			 }	 
			 return txangoak;
	     } finally {
	         //db.close();
	     }
	}
	
	public Vector<Txangoa> getTxangoakJ(String i, String a) {
		 try {
			 Txangoa proto = new Txangoa(null,null,0);
			 ObjectSet<Txangoa> result = db.queryByExample(proto);
			 Vector<Txangoa> txangoak=new Vector<Txangoa>();
			 for (int k=0; k<result.size(); k++){
				 if (result.get(k).getRH().getOwner().getName().equals(i) && result.get(k).getRH().getOwner().getAbizena().equals(a)){
					 txangoak.add(result.get(k));
				 }
			 }	 
			 return txangoak;
	     } finally {
	         //db.close();
	     }
	}
	
	
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws  OverlappingOfferExists{
		 try {
			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse rhn = (RuralHouse) db.queryByExample(new RuralHouse(/*rh.getHouseNumber(),*/null,null,rh.getCity())).next();		
			if (rhn.overlapsWith(firstDay, lastDay)!=null) throw new OverlappingOfferExists();
			else return false; 
	     } finally {
	         //db.close();
	     }
	}
	
	public boolean existsOverlappingOwner(String Izena, String Abizena) throws  OverlappingOwnerExists{
		 try {
			 ObjectSet <Owner> result = db.queryByExample(new Owner(Izena,Abizena,null,null));
			 int s=result.size();
			 if (s>0) throw new OverlappingOwnerExists();
			 else return false;
	     } finally {
	         //db.close();
	     }
	}
	
	public boolean existsOverlappingErabiltzailea(String Izena, String Abizena) throws  OverlappingErabiltzaileaExists{
		 try {
			 ObjectSet <Erabiltzailea> result = db.queryByExample(new Erabiltzailea(Izena,Abizena,null,null));
			 int s=result.size();
			 if (s>0) throw new OverlappingErabiltzaileaExists();
			 else return false;
	     } finally {
	         //db.close();
	     }
	}
	
	public boolean existsOverlappingTxangoa(RuralHouse r, String n, int i) throws  OverlappingTxangoaExists{
		 try {
			 ObjectSet <Txangoa> result = db.queryByExample(new Txangoa(r,n,0));
			 int s=result.size();
			 if (s>0) throw new OverlappingTxangoaExists();
			 else return false;
	     } finally {
	         //db.close();
	     }
	}


	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public String toString() {
		return "bookingNumber="+bookingNumber+" offerNumber="+offerNumber;
	}

}
