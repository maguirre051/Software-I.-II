package test;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import javax.swing.UIManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import domain.Booking;
import domain.Owner;
import domain.RuralHouse;
import exceptions.DB4oManagerCreationException;
import exceptions.OverlappingOwnerExists;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
public class CreateBookingTest extends TestCase {
	private static ApplicationFacadeInterface appFacadeInterface;
	private String telephone;
	private Date firstDay,lastDay;
	private RuralHouse rh;
	private Booking book;
	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}

	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
	public CreateBookingTest(String izena) {
		super(izena);
	}
	
	protected void setUp() {
		telephone="902658254";
		 firstDay=null;
		 lastDay=null;
		 rh= new RuralHouse(null,"Handia","Beasain");
        ConfigXML c=ConfigXML.getInstance();



		try {
			
			ApplicationFacadeInterface appFacadeInterface;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new FacadeImplementation();
				
				if (c.isDatabaseLocal()) 
					 appFacadeInterface.setDataAccess(new DataAccessLocal());
			    else 
			    	 appFacadeInterface.setDataAccess(new DataAccessRemote());
			}
			
			else {
				
				System.setProperty("java.security.policy", c.getJavaPolicyPath());				
				System.setSecurityManager(new RMISecurityManager());
				
				final String businessLogicNode = c.getBusinessLogicNode();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				
				appFacadeInterface=(ApplicationFacadeInterface) Naming.lookup("rmi://"+ businessLogicNode + ":" + portNumber + serviceName);
			} 
			setBussinessLogic(appFacadeInterface);

		} catch (java.rmi.ConnectException e) {
			
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (java.rmi.NotBoundException e) {
		
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (com.db4o.ext.DatabaseFileLockedException e) {
				
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (DB4oManagerCreationException e) {
		
			System.out.println("Error in StartWindow: "+e.toString());

			
		}catch (Exception e) {
		
			System.out.println("Error in StartWindow: "+e.toString());
		}

	}
	
	public void testJabea(){
			ApplicationFacadeInterface facade= getBusinessLogic();

		try {
			facade.createBooking(rh, firstDay, lastDay, telephone);
			Vector<Booking> bookbek=facade.getAllBooking();
			book=bookbek.lastElement();
			assertEquals(telephone,book.getTelephone());
			assertEquals(rh.getDescription(),book.getOffer().getRuralHouse().getDescription());
			assertEquals(rh.getCity(),book.getOffer().getRuralHouse().getCity());
			assertEquals(firstDay,book.getBookingDate());
		
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
		
		
	
    public static Test suite() {

        //
        // Erreflexio mekanismoa erabili
        // testXXX() metodoak gehitzeko.
        //
        TestSuite suite = new TestSuite(CreateBookingTest.class);

       

        return suite;
    }

	/**
	 * Main metodo nagusia.
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
	
}
