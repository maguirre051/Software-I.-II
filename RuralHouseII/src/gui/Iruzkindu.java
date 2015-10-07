package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;

import businessLogic.ApplicationFacadeInterface;

public class Iruzkindu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKom;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Iruzkindu frame = new Iruzkindu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	@SuppressWarnings("unchecked")
	public Iruzkindu(final Erabiltzailea er){
		
		setTitle("Iruzkindu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEtxea = new JLabel("Etxea:");
		lblEtxea.setBounds(33, 30, 46, 14);
		contentPane.add(lblEtxea);
		
		MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
		ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();		
					
		JLabel lblPuntuazioa = new JLabel("Puntuazioa:");
		lblPuntuazioa.setBounds(33, 81, 66, 14);
		contentPane.add(lblPuntuazioa);
		
		
		final JComboBox<Integer> comboBoxPunt = new JComboBox<Integer>();
		comboBoxPunt.setBounds(102, 78, 46, 20);
		contentPane.add(comboBoxPunt);
		comboBoxPunt.addItem(1);
		comboBoxPunt.addItem(2);
		comboBoxPunt.addItem(3);
		comboBoxPunt.addItem(4);
		comboBoxPunt.addItem(5);
			
		JLabel lblKomentarioa = new JLabel("Komentarioa:");
		lblKomentarioa.setBounds(33, 127, 66, 14);
		contentPane.add(lblKomentarioa);
		
		textFieldKom = new JTextField();
		textFieldKom.setBounds(105, 127, 283, 83);
		contentPane.add(textFieldKom);
		textFieldKom.setColumns(10);
		
		Vector<RuralHouse> v = null;
		try {
			v = facade.getAllRuralHouses();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Vector<String> b=new Vector();
		for (int i=0; i<v.size(); i++){
			b.addElement(v.get(i).getCity());
		}
		
		final JComboBox<String> comboBoxEtxe = new JComboBox(b);
		comboBoxEtxe.setBounds(74, 27, 122, 20);
		contentPane.add(comboBoxEtxe);
		
		
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
		
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(266, 40, 141, 55);
		contentPane.add(textArea);
		
		JButton btnIruzkindu = new JButton("Iruzkindu");
		btnIruzkindu.setBounds(184, 221, 89, 30);
		btnIruzkindu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldKom.getText().isEmpty()){
										
						MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						
						Vector<RuralHouse> etxe = new Vector();
						try {
							etxe=facade.getAllRuralHouses();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						RuralHouse r = new RuralHouse();
						for (int i=0; i<etxe.size(); i++){
							if (comboBoxEtxe.getSelectedItem().equals((etxe.get(i)).getCity())){
								r = etxe.get(i);
							}
						}
						String k = textFieldKom.getText();
						int p = (Integer) comboBoxPunt.getSelectedItem();
						java.util.Date d = new Date();
						try {
							facade.createIruzkina(r, er, d, p, k);
							System.out.println("iruzkina gehitu da");
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JFrame sw = new MainWindow(er);
						sw.setVisible(true);
						((MainWindow) sw).getBoton2().setEnabled(false);
						((MainWindow) sw).getbtnAddRH().setEnabled(false);
						((MainWindow) sw).btnIruzkindu().setEnabled(true);
						((MainWindow) sw).btnTxangoApDes().setEnabled(true);
						((MainWindow) sw).btnManageTxangoa().setEnabled(false);
						
					
				}else{textArea.setText("Informazioa sartu");}
			}
		});
		contentPane.add(btnIruzkindu);
		
		JButton btnEzabatu = new JButton("Iruzkina ezabatu");
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame s = new IruzkinaEzabatu(er);
				s.setVisible(true);
			}
		});
		btnEzabatu.setBounds(0, 225, 113, 37);
		contentPane.add(btnEzabatu);
		
		
		
		
		
		
	}

	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
