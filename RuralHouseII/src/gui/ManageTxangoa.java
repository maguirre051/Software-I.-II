package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Owner;
import domain.RuralHouse;
import domain.Txangoa;
import exceptions.OverlappingTxangoaExists;

import javax.swing.ButtonGroup;

import businessLogic.ApplicationFacadeInterface;

public class ManageTxangoa extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public ManageTxangoa(final Owner o) {		
		
		setTitle("Manage Txangoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Vector<RuralHouse> a = o.getRuralHouses();
		Vector<String> b = new Vector();
		for (int i=0; i<a.size(); i++){
			b.addElement(a.get(i).getCity());
		}
			
		final JComboBox comboBox = new JComboBox(b);
		comboBox.setBounds(91, 207, 139, 20);
		contentPane.add(comboBox);
		
		JLabel lblEtxeak = new JLabel("Etxea:");
		lblEtxeak.setBounds(35, 210, 46, 14);
		contentPane.add(lblEtxeak);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(37, 327, 357, 22);
		contentPane.add(textArea);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(255, 72, -220, 105);
		contentPane.add(scrollPane);
		
		ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
		Vector<Txangoa> v = new Vector<Txangoa>();
		try {
			v=facade.getTxangoakJ(o.getName(), o.getAbizena());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final JList list = new JList(v);
		list.setBounds(35, 74, 220, 103);
		contentPane.add(list);
		
		comboBox.setEnabled(false);
		//textField.setEnabled(false);
		//textField_1.setEnabled(false);
		list.setEnabled(false);
		scrollPane.setEnabled(false);
		
		final JRadioButton rdbtnTxangoaSortu = new JRadioButton("Txangoa sortu");
		rdbtnTxangoaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(true);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				list.setEnabled(false);
				scrollPane.setEnabled(false);
			}
		});
		buttonGroup.add(rdbtnTxangoaSortu);
		rdbtnTxangoaSortu.setBounds(35, 19, 109, 23);
		contentPane.add(rdbtnTxangoaSortu);
		
		final JRadioButton rdbtnTxangoaEzabatu = new JRadioButton("Txangoa ezabatu");
		rdbtnTxangoaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
				list.setEnabled(true);
				scrollPane.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnTxangoaEzabatu);
		rdbtnTxangoaEzabatu.setBounds(146, 19, 109, 23);
		contentPane.add(rdbtnTxangoaEzabatu);
		
		final JButton btn = new JButton("Sortu / Ezabatu");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(" ");
				if (rdbtnTxangoaSortu.isSelected()){
					if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty()){
						String n = textField.getText();
						int i = Integer.parseInt(textField_1.getText());
						
						Vector<RuralHouse> dd = new Vector();
						dd=o.getRuralHouses();
						String s = (String) comboBox.getSelectedItem();
						RuralHouse r = new RuralHouse();
						for (int k=0; k<dd.size();k++){
							if (dd.get(k).getCity().equals(s)){
								r=dd.get(k);
							}
						}	
						
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						try {
							facade.createTxangoa(r, n, i);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OverlappingTxangoaExists e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						JFrame sw = new MainWindow(o);
						sw.setVisible(true);
						((MainWindow) sw).getBoton1().setEnabled(false);
						((MainWindow) sw).getbtnAddRH().setEnabled(true);
						((MainWindow) sw).btnIruzkindu().setEnabled(false);
						((MainWindow) sw).btnManageTxangoa().setEnabled(true);
						((MainWindow) sw).btnTxangoApDes().setEnabled(false);
						
					}else{ textArea.setText("Beharrezko datuak sartu.");}
				}else if (rdbtnTxangoaEzabatu.isSelected()){
					if (!list.isSelectionEmpty()){
						
						Txangoa t = (Txangoa) list.getSelectedValue();
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						try {
							facade.ezabatuTxangoa(t);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						JFrame sw = new MainWindow(o);
						sw.setVisible(true);
						((MainWindow) sw).getBoton1().setEnabled(false);
						((MainWindow) sw).getbtnAddRH().setEnabled(true);
						((MainWindow) sw).btnIruzkindu().setEnabled(false);
						((MainWindow) sw).btnManageTxangoa().setEnabled(true);
						((MainWindow) sw).btnTxangoApDes().setEnabled(false);
											
					}else{textArea.setText("Ez dago txangorik edo ez duzu bat aukeratu");}								
				}else{textArea.setText("'Sortu' edo 'ezabatu' aukeratu behar duzu.");}
			}
		});
		btn.setBounds(285, 72, 109, 203);
		contentPane.add(btn);
	
		textField = new JTextField();
		textField.setBounds(91, 238, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
			
		JLabel lblNora = new JLabel("Nora?");
		lblNora.setBounds(35, 241, 46, 14);
		contentPane.add(lblNora);
		
		JLabel lblIraupenaminututan = new JLabel("Iraupena(minututan):");
		lblIraupenaminututan.setBounds(35, 266, 109, 14);
		contentPane.add(lblIraupenaminututan);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 263, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(345, 0, 89, 28);
		contentPane.add(btnAtzera);
		
		JLabel lblTxangoaEzabatu = new JLabel("Txangoa ezabatu:");
		lblTxangoaEzabatu.setBounds(10, 49, 134, 14);
		contentPane.add(lblTxangoaEzabatu);
		
		JLabel lblTxangoaSortu = new JLabel("Txangoa sortu:");
		lblTxangoaSortu.setBounds(10, 185, 167, 14);
		contentPane.add(lblTxangoaSortu);
		
		
		
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
