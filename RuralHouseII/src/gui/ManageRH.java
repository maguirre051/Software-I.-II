package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JTextArea;

import domain.Owner;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

@SuppressWarnings("serial")
public class ManageRH extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDes;
	private JTextField textFieldC;
	private final ButtonGroup buttonGroup3 = new ButtonGroup();
	private JTextField textFieldHN;
	//private JComboBox<Owner> combo;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddDelRH frame = new AddDelRH();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ManageRH(final Owner ow) {
		
		setTitle("Manage Rural House");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JRadioButton rdbtnAdd = new JRadioButton("Add");
		rdbtnAdd.setBounds(56, 18, 109, 23);
		buttonGroup3.add(rdbtnAdd);
		rdbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldDes.setEnabled(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(rdbtnAdd);
		
		final JRadioButton rdbtnDelete = new JRadioButton("Delete");
		rdbtnDelete.setBounds(178, 18, 109, 23);
		buttonGroup3.add(rdbtnDelete);
		rdbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldDes.setEnabled(false);
			}
		});
		contentPane.add(rdbtnDelete);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(34, 114, 66, 14);
		contentPane.add(lblDescription);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(34, 139, 46, 14);
		contentPane.add(lblCity);
		
		textFieldDes = new JTextField();
		textFieldDes.setBounds(130, 111, 216, 20);
		contentPane.add(textFieldDes);
		textFieldDes.setColumns(10);
		
		textFieldC = new JTextField();
		textFieldC.setBounds(130, 136, 216, 20);
		contentPane.add(textFieldC);
		textFieldC.setColumns(10);
		
//		textFieldHN = new JTextField();
//		textFieldHN.setBounds(130, 79, 216, 20);
//		contentPane.add(textFieldHN);
//		textFieldHN.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(34, 229, 390, 22);
		contentPane.add(textArea);
		
		
		Vector<RuralHouse> rh = ow.getRuralHouses();
		Vector<String> b=new Vector();
		for (int i=0; i<rh.size(); i++){
			b.addElement(rh.get(i).getCity());
		}
		
		final JComboBox comboBox = new JComboBox(b);
		comboBox.setBounds(178, 48, 168, 20);
		contentPane.add(comboBox);
		comboBox.setEnabled(false);
		
		final JRadioButton rdbtnChange = new JRadioButton("Change");
		rdbtnChange.setBounds(56, 44, 109, 23);
		rdbtnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(true);
				textFieldDes.setEnabled(true);
			}
		});
		buttonGroup3.add(rdbtnChange);
		contentPane.add(rdbtnChange);
		
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
		
		JButton btnAD = new JButton("Add / Delete / Change");
		btnAD.setBounds(92, 182, 207, 41);
		btnAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnAdd.isSelected()){
					if(!textFieldDes.getText().isEmpty() && !textFieldC.getText().isEmpty()){
						
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						
						//int hn = Integer.parseInt(textFieldHN.getText());
						String des = textFieldDes.getText();
						String c = textFieldC.getText();						
						
						try {
							Owner o = ow;
							o.addRuralHouse(/*hn,*/des,c);
							facade.storeOwner(o);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JFrame sw = new MainWindow(ow);
						sw.setVisible(true);
						((MainWindow) sw).getBoton1().setEnabled(false);
						((MainWindow) sw).getbtnAddRH().setEnabled(true);
						((MainWindow) sw).btnIruzkindu().setEnabled(false);
						((MainWindow) sw).btnManageTxangoa().setEnabled(true);
						((MainWindow) sw).btnTxangoApDes().setEnabled(false);
					}else{textArea.setText("sartu beharrezko datuak");}
					
					
				}else if (rdbtnDelete.isSelected()){
					
					if(!textFieldC.getText().isEmpty()){
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						String c = textFieldC.getText();	
						Owner o = ow;
						Vector<RuralHouse> rh = o.getRuralHouses();
					for(int i=0; i<rh.size(); i++){
							if (rh.get(i).getCity().equals(c)){
								rh.remove(i);
								o.setRHouses(rh);
								try {
									facade.storeOwner(o);
									facade.ezabatuEtxea(c);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JFrame sw = new MainWindow(o);
								sw.setVisible(true);
								((MainWindow) sw).getBoton1().setEnabled(false);
								((MainWindow) sw).getbtnAddRH().setEnabled(true);
								((MainWindow) sw).btnIruzkindu().setEnabled(false);
								((MainWindow) sw).btnManageTxangoa().setEnabled(true);
								((MainWindow) sw).btnTxangoApDes().setEnabled(false);
							}
						}
						
					}else{textArea.setText("sartu beharrezko datuak");}
				}else if(rdbtnChange.isSelected()){
					
					//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
					ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
					textArea.setText(" ");
					//String c = textFieldC.getText();	
					Owner o = ow;
					Vector<RuralHouse> rh = o.getRuralHouses();
					RuralHouse r = new RuralHouse();
					for (int i=0; i<rh.size(); i++){
						if (comboBox.getSelectedItem().equals(rh.get(i).getCity())){
							r = rh.get(i);
						}
					}
					r.setCity(textFieldC.getText());
					r.setDescription(textFieldDes.getText());
					
					try {
						facade.storeOwner(ow);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
															
				}else{textArea.setText("add/delete/change?");}			
			}
		});
		contentPane.add(btnAD);
		
		
		
//		JLabel lblHouseNumber = new JLabel("House number");
//		lblHouseNumber.setBounds(34, 82, 86, 14);
//		contentPane.add(lblHouseNumber);
		
		
	
	}
	
	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
