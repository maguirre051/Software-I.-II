package test;

import java.awt.Color;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.UIManager;

import configuration.ConfigXML;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import dataAccess.DataAccessCommon;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import domain.RuralHouse;
import domain.Txangoa;
import exceptions.DB4oManagerCreationException;
import exceptions.OverlappingTxangoaExists;
import gui.MainWindow;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 
public class TestCreateTxangoa extends TestCase {

	private RuralHouse rh,rh1;
	private String nora;
	private int iraupena;
	private Txangoa txango;
	private DataAccessInterface db;

private static ApplicationFacadeInterface appFacadeInterface;

	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
		
		
	
	
	public TestCreateTxangoa(String izena) {
		super(izena);
	}
	
	protected void setUp() {

		rh = new RuralHouse(null,"Handia","Beasain");

		nora = new String("Beasain");
		
        iraupena=31;
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
	
	public void testCreateTxangoa(){

		try {
			ApplicationFacadeInterface facade= getBusinessLogic();
			facade.createTxangoa(rh,nora,iraupena);		
			Vector<Txangoa>txanbek=facade.getAllTxangoak();
			txango=txanbek.lastElement();
			assertEquals(35,txango.getIraupena());
			assertEquals("Beasain",txango.getNora());
			assertEquals("Beasain",txango.getRH().getCity());
			assertEquals("Beasain",txango.getRH().getCity());
			assertEquals("Handia",txango.getRH().getDescription());

			
		
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OverlappingTxangoaExists e) {
			// TODO Auto-generated catch block
			/*e.printStackTrace();*/
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
        TestSuite suite = new TestSuite(TestCreateTxangoa.class);

       

        return suite;
    }

	/**
	 * Main metodo nagusia.
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
	
	
}
