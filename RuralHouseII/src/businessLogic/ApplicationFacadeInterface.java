package businessLogic;

import java.rmi.*;
import java.util.Vector;
import java.util.Date;

import dataAccess.DataAccessInterface;
import domain.Booking;
import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;


import domain.Txangoa;
import exceptions.OfferCanNotBeBooked; 
import exceptions.OverlappingErabiltzaileaExists;
import exceptions.OverlappingOwnerExists;
import exceptions.OverlappingTxangoaExists;


public interface ApplicationFacadeInterface extends Remote {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Owner storeOwner(Owner o)throws RemoteException,
	Exception;
	public Txangoa storeTxangoa(Txangoa t)throws RemoteException,
	Exception;
	
	public Owner createOwner(String iz, String ab, String ep, String p) throws RemoteException, OverlappingOwnerExists;
	public Erabiltzailea createErabiltzailea(String iz, String ab, String ep, String p) throws RemoteException, OverlappingErabiltzaileaExists;
	
	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception;
	
	Iruzkina createIruzkina(RuralHouse r, Erabiltzailea e, Date d, int p, String k)throws RemoteException;
	public Txangoa createTxangoa(RuralHouse rh, String n, int i) throws RemoteException, OverlappingTxangoaExists;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	Booking createBooking(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException,
			OfferCanNotBeBooked;

	
	/**
	 * This method retrieves the existing  owners 
	 * 
	 * @return a Set of owners
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception;
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	public Vector<Erabiltzailea> getErabiltzaileak() throws RemoteException,
	Exception;
	
	public void ezabatuJabea(String iz, String ab, String p) throws RemoteException,
	Exception;
	public void ezabatuErabiltzailea(String iz, String ab, String p)throws RemoteException,
	Exception;
	public void ezabatuIruzkina(RuralHouse r,Erabiltzailea e, Date d)throws RemoteException,
	Exception;
	public void ezabatuEtxea(String city)throws RemoteException,
	Exception;
	public void ezabatuTxangoa(Txangoa t)throws RemoteException,
	Exception;
	
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	public Vector<Booking> getAllBooking() throws RemoteException;
	public Vector<Iruzkina> getAllIruzkina() throws RemoteException,
	Exception;
	
	public Vector<Txangoa> getAllTxangoak() throws RemoteException,
	Exception;
	public Vector<Txangoa> getTxangoakE(String i, String a) throws RemoteException,Exception;
	public Vector<Txangoa> getTxangoakJ(String i, String a) throws RemoteException,Exception;
	
	public void close() throws RemoteException;

    public void setDataAccess(DataAccessInterface dai) throws RemoteException;
	
}