package gui;

import java.awt.Color;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;


import javax.swing.UIManager;

import configuration.ConfigXML;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import exceptions.DB4oManagerCreationException;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;

public class Launcher {
	
	
	
	public static void main(String[] args) {
		
		MainWindow a=new MainWindow();
		a.setVisible(true);
		((MainWindow) a).getBoton1().setEnabled(false);
		((MainWindow) a).getBoton2().setEnabled(false);
		((MainWindow) a).getbtnAddRH().setEnabled(false);
		((MainWindow) a).getBoton1().setEnabled(false);
		((MainWindow) a).btnIruzkindu().setEnabled(false);
		((MainWindow) a).btnManageTxangoa().setEnabled(false);
		((MainWindow) a).btnTxangoApDes().setEnabled(false);
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
			MainWindow.setBussinessLogic(appFacadeInterface);

		} catch (java.rmi.ConnectException e) {
			a.lblNewLabel.setText("No business logic: Run BusinessLogicServer first!!");
			a.lblNewLabel.setForeground(Color.RED);
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (java.rmi.NotBoundException e) {
			a.lblNewLabel.setText("No business logic: Maybe problems running BusinessLogicServer");
			a.lblNewLabel.setForeground(Color.RED);
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (com.db4o.ext.DatabaseFileLockedException e) {
			a.lblNewLabel.setText("Database locked: Do not run BusinessLogicServer or BusinessLogicServer!!");
			a.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (DB4oManagerCreationException e) {
			a.lblNewLabel.setText("No database: Run DB4oManagerServer first!!");
			a.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());

			
		}catch (Exception e) {
			a.lblNewLabel.setText("Error: "+e.toString());
			a.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());
		}
		//a.pack();


	}

}
