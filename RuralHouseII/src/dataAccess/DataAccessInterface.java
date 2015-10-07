package dataAccess;

import java.util.Date;
import java.util.Vector;

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

public interface DataAccessInterface {
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price);

	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked;
	
	public Owner storeOwner(Owner o);
	public Txangoa storeTxangoa(Txangoa t);
	
	public Vector<Owner> getOwners();
	public Vector<RuralHouse> getAllRuralHouses();
	public Vector<Erabiltzailea> getErabiltzaileak();
	public Vector<Iruzkina> getAllIruzkina();
	public Vector<Txangoa> getAllTxangoak();
	public Vector<Txangoa> getTxangoakE(String i, String a);
	public Vector<Txangoa> getTxangoakJ(String i, String a);
	public Vector<Booking> getAllBooking();
	public void ezabatuJabea(String iz, String ab, String p);
	public void ezabatuErabiltzailea(String iz, String ab, String p);
	public void ezabatuIruzkina(RuralHouse r,Erabiltzailea e, Date d);
	public void ezabatuEtxea(String city);
	public void ezabatuTxangoa(Txangoa t);
	
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws OverlappingOfferExists;
	public boolean existsOverlappingOwner(String Izena, String Abizena) throws  OverlappingOwnerExists;
	public boolean existsOverlappingErabiltzailea(String Izena, String Abizena) throws  OverlappingErabiltzaileaExists;
	public boolean existsOverlappingTxangoa(RuralHouse r, String n, int i) throws  OverlappingTxangoaExists;
	
	public Owner createOwner(String iz, String ab, String ep, String p);
	public Erabiltzailea createErabiltzailea(String iz, String ab, String ep, String p);
	public Iruzkina createIruzkina(RuralHouse r, Erabiltzailea e, Date d, int p, String k);
	public Txangoa createTxangoa(RuralHouse r, String n, int i);
	
	public void close();
}
