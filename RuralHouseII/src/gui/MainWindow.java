package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import configuration.ConfigXML;
import domain.Erabiltzailea;
import domain.Owner;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;
	private JButton boton4 = null;
	private JButton btnAddRH = null;
	private JButton btnIruzkIkusi = null;
	private JButton btnIruzkindu = null;
	private JButton btnTxangoApDes = null;
	private JButton btnManageTxangoa = null;
	private Owner owner=null;
	private Erabiltzailea erab=null;

    private static ApplicationFacadeInterface appFacadeInterface;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
	protected JLabel lblNewLabel;
	

	/**
	 * This is the default constructor
	 */
	public MainWindow() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public MainWindow(final Owner o) {
		super();
		
		this.owner=o;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MainWindow(final Erabiltzailea e) {
		super();
		
		this.erab=e;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 535);
		this.setContentPane(getJContentPane());
		this.setTitle("Rural Houses");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getBoton1());
			jContentPane.add(getBoton4());
			jContentPane.add(getbtnAddRH());
			jContentPane.add(btnIruzkIkusi());
			jContentPane.add(btnIruzkindu());
			jContentPane.add(btnManageTxangoa());
			jContentPane.add(btnTxangoApDes());
		}
		return jContentPane;
	}
	
	public JButton btnManageTxangoa() {
		if (btnManageTxangoa == null) {
			btnManageTxangoa = new JButton();
			btnManageTxangoa.setBounds(0, 437, 239, 60);
			btnManageTxangoa.setText("Manage Txangoa");
			btnManageTxangoa.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a;
					a = new ManageTxangoa(owner);
					a.setVisible(true);	
				}
			});
		}
		return btnManageTxangoa;
	}
	

	public JButton btnTxangoApDes() {
		if (btnTxangoApDes == null) {
			btnTxangoApDes = new JButton();
			btnTxangoApDes.setBounds(238, 437, 241, 60);
			btnTxangoApDes.setText("Txangoan apuntatu/desapuntatu");
			btnTxangoApDes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a;
					a = new TxangoApDes(erab);
					a.setVisible(true);
				}
			});
		}
		return btnTxangoApDes;
	}
	
	public JButton btnIruzkIkusi() {
		if (btnIruzkIkusi == null) {
			btnIruzkIkusi = new JButton();
			btnIruzkIkusi.setBounds(0, 377, 239, 60);
			btnIruzkIkusi.setText("Iruzkinak ikusi");
			btnIruzkIkusi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new IruzkinakIkusi();
					a.setVisible(true);
				}
			});
		}
		return btnIruzkIkusi;
	}
	
	public JButton btnIruzkindu() {
		if (btnIruzkindu == null) {
			btnIruzkindu = new JButton();
			btnIruzkindu.setBounds(238, 377, 241, 60);
			btnIruzkindu.setText("Iruzkindu/Ezabatu iruzkina");
			btnIruzkindu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a;
					a = new Iruzkindu(erab);
					a.setVisible(true);		
				}
			});
		}
		return btnIruzkindu;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBoton1() {
		if (boton1 == null) {
			boton1 = new JButton();
			boton1.setBounds(0, 183, 479, 61);
			boton1.setText("Book rural house");
			boton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la univerdad
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return boton1;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setBounds(0, 122, 479, 61);
			boton2.setText("Set availability");
			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					JFrame a = new SetAvailability2GUI(owner.getRuralHouses());
					a.setVisible(true);
				}
			});
		}
		return boton2;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(0, 61, 479, 61);
			boton3.setText("Query availability");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryAvailabilityGUI();
					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	
	private JButton getBoton4(){
		if (boton4 == null){
			boton4 = new JButton();
			boton4.setBounds(0, 245, 479, 64);
			boton4.setText("LogIn");
			boton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame lg = new LogIn();
					lg.setVisible(true);
				}
			});
		}
		return boton4;
		
	}
	public JButton getbtnAddRH(){
		if (btnAddRH == null){
			btnAddRH = new JButton();
			btnAddRH.setBounds(0, 309, 493, 69);
			btnAddRH.setText("Manage Rural House");
			btnAddRH.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame ad = new ManageRH(owner);
					ad.setVisible(true);
				}
			});
		}
		return btnAddRH;
		
	}
//	JButton btnAddRH = new JButton("Add / Delete Rural House");
//	btnAddRH.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			
//		}
//	});
//	btnAddRH.setBounds(0, 309, 493, 69);
//	jContentPane.add(btnAddRH);

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Select option:");
			lblNewLabel.setBounds(0, 0, 479, 61);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

