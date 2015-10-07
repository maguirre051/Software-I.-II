package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import businessLogic.ApplicationFacadeInterface;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.RuralHouse;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class IruzkinakIkusi extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	public IruzkinakIkusi()  {
		setTitle("Iruzkinak ikusi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
		final ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();		
		contentPane.setLayout(null);
		
		Vector<Erabiltzailea> er = new Vector<Erabiltzailea>();
		//Vector<String> erabiltzaileak = new Vector();
		try {
			er=facade.getErabiltzaileak();			
			//for (int i=0; i<er.size(); i++){
				//erabiltzaileak.add(er.get(i).getIzena());
			//}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Vector<RuralHouse> etx = new Vector<RuralHouse>();
		try {
			etx=facade.getAllRuralHouses();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Vector<String> b=new Vector();
		for (int i=0; i<etx.size(); i++){
			b.addElement(etx.get(i).getCity());
		}
		
		
		final JComboBox comboErab = new JComboBox(er);
		comboErab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboErab.setBounds(181, 294, 70, 20);
		contentPane.add(comboErab);
		comboErab.setEnabled(false);
		
		final JComboBox comboEtxe = new JComboBox(b);
		comboEtxe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboEtxe.setBounds(181, 320, 70, 20);
		contentPane.add(comboEtxe);
		comboEtxe.setEnabled(false);
		
		JList<Iruzkina> list;
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 414, 236);
		contentPane.add(scrollPane);
		try {
			list = new JList<Iruzkina>(facade.getAllIruzkina());
			scrollPane.setViewportView(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JRadioButton rdbtnErabiltzailea = new JRadioButton("Erabiltzailea");
		rdbtnErabiltzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
//				ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();								
				comboEtxe.setEnabled(false);
				comboErab.setEnabled(true);
				
				scrollPane.setViewportView(null);
				Erabiltzailea erab = new Erabiltzailea();
				erab = (Erabiltzailea) comboErab.getSelectedItem();
				Vector<Iruzkina> a = new Vector<Iruzkina>();
				Vector<Iruzkina> b = new Vector<Iruzkina>();
				try {
					a=facade.getAllIruzkina();
					for (int i=0; i<a.size(); i++){
						if (a.get(i).getErabiltzailea().equals(erab)){
							b.addElement((Iruzkina)a.get(i));
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JList<Iruzkina> l = new JList<Iruzkina>(b);
				scrollPane.setViewportView(l);
				
			}
		});
		buttonGroup.add(rdbtnErabiltzailea);
		rdbtnErabiltzailea.setBounds(75, 293, 83, 23);
		contentPane.add(rdbtnErabiltzailea);
		
		JRadioButton rdbtnEtxea = new JRadioButton("Etxea");
		rdbtnEtxea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
				ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();				
				comboErab.setEnabled(false);
				comboEtxe.setEnabled(true);
				scrollPane.setViewportView(null);
				
//				Vector<RuralHouse> etxe = new Vector();
//				try {
//					etxe=facade.getAllRuralHouses();
//				} catch (RemoteException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				} catch (Exception e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				RuralHouse r = new RuralHouse();
//				for (int i=0; i<etxe.size(); i++){
//					if (comboEtxe.getSelectedItem().equals((etxe.get(i)).getCity())){
//						r = etxe.get(i);
//					}
//				}
				
				Vector<Iruzkina> a= new Vector<Iruzkina>();
				Vector<Iruzkina> b= new Vector<Iruzkina>();
				try {
					a=facade.getAllIruzkina();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i=0; i<a.size(); i++){
					if (a.get(i).getRH().getCity().equals(comboEtxe.getSelectedItem())){						 
						b.addElement(a.get(i));
					}
				}
				JList<Iruzkina> l = new JList<Iruzkina>(b);
				scrollPane.setViewportView(l);
			}
		});
		buttonGroup.add(rdbtnEtxea);
		rdbtnEtxea.setBounds(75, 319, 53, 23);
		contentPane.add(rdbtnEtxea);
		
		
		JRadioButton rdbtnDef = new JRadioButton("Data (defektuzkoa)");
		rdbtnDef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
				ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();				
				comboErab.setEnabled(false);
				comboEtxe.setEnabled(false);
				
				JList list;
				try {
					list = new JList(facade.getAllIruzkina());
					scrollPane.setViewportView(list);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonGroup.add(rdbtnDef);
		rdbtnDef.setBounds(276, 293, 148, 23);
		contentPane.add(rdbtnDef);
		
		JLabel lblSailkatu = new JLabel("Sailkatu:");
		lblSailkatu.setBounds(10, 297, 41, 14);
		contentPane.add(lblSailkatu);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(345, 0, 89, 28);
		contentPane.add(btnAtzera);
		
		btnAtzera.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	btnAtzera_actionPerformed(e);
	        }
	      });
		
		
	}
	
	private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
