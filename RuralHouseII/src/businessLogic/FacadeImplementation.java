package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.SQLException;
import java.util.Vector;

import dataAccess.DataAccessInterface;
import domain.Booking;
import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.Txangoa;
import exceptions.BadDates;
import exceptions.DB4oManagerCreationException;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingErabiltzaileaExists;
import exceptions.OverlappingOfferExists;
import exceptions.OverlappingOwnerExists;
import exceptions.OverlappingTxangoaExists;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vector<Owner> owners;
	Vector<Erabiltzailea> erabiltzaileak;
	Vector<RuralHouse> ruralHouses;
	Vector<Iruzkina> iruzkinak;
	Vector<Txangoa> txangoak;
	Vector<Txangoa> txangoakJ;
	Vector<Txangoa> txangoakE;
	DataAccessInterface dB4oManager;
 

	public FacadeImplementation() throws RemoteException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, DB4oManagerCreationException {
		
	}
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Owner storeOwner(Owner o)throws RemoteException,
	Exception{
		return dB4oManager.storeOwner(o);
	}
	
	public Txangoa storeTxangoa(Txangoa t)throws RemoteException,
	Exception{
		return dB4oManager.storeTxangoa(t);
	}
	
	public Owner createOwner(String izena, String abizena, String ePosta, String pasahitza) throws RemoteException, OverlappingOwnerExists{
		ruralHouses=null;
		owners=null;
		boolean b = dB4oManager.existsOverlappingOwner(izena,abizena);
		if (!b) return dB4oManager.createOwner(izena, abizena, ePosta, pasahitza);
		return null;
	}
	
	public Erabiltzailea createErabiltzailea(String izena, String abizena, String ePosta, String pasahitza) throws RemoteException, OverlappingErabiltzaileaExists{
		owners=null;
		erabiltzaileak=null;
		boolean b = dB4oManager.existsOverlappingErabiltzailea(izena,abizena);
		if (!b) return dB4oManager.createErabiltzailea(izena, abizena, ePosta, pasahitza);
		return null;
	}
	
	public Iruzkina createIruzkina(RuralHouse r, Erabiltzailea e, Date d, int p, String k)throws RemoteException{
		return dB4oManager.createIruzkina(r, e, d, p, k);
	}
	
	public Txangoa createTxangoa(RuralHouse rh, String n, int i) throws RemoteException, OverlappingTxangoaExists{
		boolean b = dB4oManager.existsOverlappingTxangoa(rh,n,i);
		if (!b) return dB4oManager.createTxangoa(rh,n,i);
		return null;
	}
	
	
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates, RemoteException, Exception {
		if (firstDay.compareTo(lastDay)>=0) throw new BadDates();
		ruralHouses=null;
		owners=null;
		boolean b = dB4oManager.existsOverlappingOffer(ruralHouse,firstDay,lastDay); // The ruralHouse object in the client may not be updated
		if (!b) return dB4oManager.createOffer(ruralHouse,firstDay,lastDay,price);			
		return null;
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
		ruralHouses=null;
		owners=null;
		return dB4oManager.createBooking(ruralHouse,firstDate,lastDate,bookTelephoneNumber);
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception {
		
		if (owners!=null) { System.out.println("Owners obtained directly from business logic layer");
							return owners; }
		else return owners=dB4oManager.getOwners();
	}
	
	public Vector<Erabiltzailea> getErabiltzaileak() throws RemoteException,
	Exception {

		if (erabiltzaileak!=null) { System.out.println("Erabiltzaileak obtained directly from business logic layer");
					return erabiltzaileak; }
		else return erabiltzaileak=dB4oManager.getErabiltzaileak();
	}
	
	public Vector<Booking> getAllBooking() throws RemoteException{
		return dB4oManager.getAllBooking();
		
	}
	
	public Vector<Iruzkina> getAllIruzkina() throws RemoteException,
	Exception {

		if (iruzkinak!=null) { System.out.println("Iruzkinak obtained directly from business logic layer");
					return iruzkinak; }
		else return iruzkinak=dB4oManager.getAllIruzkina();
	}
	
	public Vector<Txangoa> getAllTxangoak() throws RemoteException,
	Exception {

		if (txangoak!=null) { System.out.println("Txangoak obtained directly from business logic layer");
					return txangoak; }
		else return txangoak=dB4oManager.getAllTxangoak();
	}
	
	public Vector<Txangoa> getTxangoakE(String i, String a) throws RemoteException,Exception {
		if (txangoakE!=null) { System.out.println("TxangoakE obtained directly from business logic layer");
					return txangoakE; }
		else return txangoakE=dB4oManager.getTxangoakE(i,a);
	}
	
	public Vector<Txangoa> getTxangoakJ(String i, String a) throws RemoteException,Exception {
		if (txangoakJ!=null) { System.out.println("TxangoakJ obtained directly from business logic layer");
					return txangoakJ; }
		else return txangoakJ=dB4oManager.getTxangoakJ(i,a);
	}
	
	
	public void ezabatuJabea(String iz, String ab, String p){
		String i=iz;
		String a=ab;
		String pa=p;		
		dB4oManager.ezabatuJabea(i, a, pa);
	}
	
	public void ezabatuErabiltzailea(String iz, String ab, String p){
		String i=iz;
		String a=ab;
		String pa=p;		
		dB4oManager.ezabatuErabiltzailea(i, a, pa);		
	}
	
	public void ezabatuIruzkina(RuralHouse r,Erabiltzailea e, Date d){
		RuralHouse rh=r;
		Erabiltzailea er=e;
		Date dt=d;
		dB4oManager.ezabatuIruzkina(rh, er, dt);
	}
	
	public void ezabatuTxangoa(Txangoa t){
		dB4oManager.ezabatuTxangoa(t);
	}
	
	public void ezabatuEtxea(String city){
		String c=city;
		dB4oManager.ezabatuEtxea(c);
	}
	
	
		
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		
		if (ruralHouses!=null) { System.out.println("RuralHouses obtained directly from business logic layer");
								 return ruralHouses; }
		else return ruralHouses=dB4oManager.getAllRuralHouses();

	}
	
	public void close() throws RemoteException{
		dB4oManager.close();

	}


	@Override
	public void setDataAccess(DataAccessInterface dai) throws RemoteException {
		dB4oManager=dai;
		// TODO Auto-generated method stub
		
	}

	}

