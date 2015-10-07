package test;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.UIManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import domain.Owner;
import exceptions.DB4oManagerCreationException;
import exceptions.OverlappingOwnerExists;
import exceptions.OverlappingTxangoaExists;

public class TestEzabatuJabea extends TestCase{
	private static ApplicationFacadeInterface appFacadeInterface;
	private String izena,abizena,ePosta,pasahitza;
	private Owner ow;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
	public TestEzabatuJabea(String izena) {
		super(izena);
	}
	
	protected void setUp() {

		 izena= "Pepito";
		 abizena= "Juarros";
		 ePosta="a";
		 pasahitza="1234";
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

		try {
			ApplicationFacadeInterface facade= getBusinessLogic();
			facade.createOwner(izena, abizena, ePosta, pasahitza);
			//Ezabatuko dugun jabearen sorkuntza.
			Vector<Owner>ownbek=facade.getOwners();
			ow=ownbek.lastElement();
			assertEquals("Pepito",ow.getName());
			assertEquals("Juarros",ow.getAbizena());
			assertEquals("a",ow.getEposta());		
			assertEquals("1234",ow.getPassword());
			
			//Ezabaketaren proba
			Vector<Owner>ownbek1=facade.getOwners();
			facade.ezabatuJabea(izena, abizena,pasahitza);
			Vector<Owner>ownbek2=facade.getOwners();
			assertEquals(ownbek1.size(),ownbek1.size());
			facade.close();	
		
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OverlappingOwnerExists e) {
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
        TestSuite suite = new TestSuite(TestEzabatuJabea.class);

       

        return suite;
    }

	/**
	 * Main metodo nagusia.
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
	
	
}
