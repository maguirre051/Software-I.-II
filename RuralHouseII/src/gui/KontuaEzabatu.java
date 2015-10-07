package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JTextArea;

import domain.Erabiltzailea;
import domain.Owner;
import businessLogic.ApplicationFacadeInterface;

@SuppressWarnings("serial")
public class KontuaEzabatu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIz;
	private JTextField textFieldAb;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KontuaEzabatu frame = new KontuaEzabatu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public KontuaEzabatu() {
		setTitle("Kontua Ezabatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JRadioButton rdbtnErabiltzailea = new JRadioButton("Erabiltzailea");
		buttonGroup.add(rdbtnErabiltzailea);
		rdbtnErabiltzailea.setBounds(59, 33, 109, 23);
		contentPane.add(rdbtnErabiltzailea);
		
		final JRadioButton rdbtnJabea = new JRadioButton("Jabea");
		buttonGroup.add(rdbtnJabea);
		rdbtnJabea.setBounds(181, 33, 109, 23);
		contentPane.add(rdbtnJabea);
		
		JLabel lblNolakoKontuaNahi = new JLabel("Nolako kontua nahi duzu ezabatu?");
		lblNolakoKontuaNahi.setBounds(77, 12, 213, 14);
		contentPane.add(lblNolakoKontuaNahi);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(32, 85, 46, 14);
		contentPane.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setBounds(32, 116, 46, 14);
		contentPane.add(lblAbizena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(32, 154, 67, 14);
		contentPane.add(lblPasahitza);
		
		textFieldIz = new JTextField();
		textFieldIz.setBounds(104, 82, 86, 20);
		contentPane.add(textFieldIz);
		textFieldIz.setColumns(10);
		
		textFieldAb = new JTextField();
		textFieldAb.setText("");
		textFieldAb.setBounds(104, 113, 86, 20);
		contentPane.add(textFieldAb);
		textFieldAb.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 151, 86, 20);
		contentPane.add(passwordField);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(32, 213, 365, 22);
		contentPane.add(textArea);
		
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
		
		JButton btnEzabatuKontua = new JButton("Ezabatu Kontua");
		btnEzabatuKontua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnJabea.isSelected()){
					if(!textFieldIz.getText().isEmpty() && !textFieldAb.getText().isEmpty() && !passwordField.getText().isEmpty()){
						
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						String iz = textFieldIz.getText();
						String ab = textFieldAb.getText();
						String p = passwordField.getText();	
						
						try {
							//Vector<Owner> a;
							//a=facade.getOwners();
							facade.ezabatuJabea(iz, ab, p);
							//Vector<Owner> b;
							//b=facade.getOwners();
							//if (a.size()>b.size()){
								JFrame sw = new MainWindow();
								sw.setVisible(true);
								((MainWindow) sw).getBoton1().setEnabled(false);
								((MainWindow) sw).getBoton2().setEnabled(false);
								((MainWindow) sw).getbtnAddRH().setEnabled(false);
								((MainWindow) sw).btnIruzkindu().setEnabled(false);
								((MainWindow) sw).btnTxangoApDes().setEnabled(false);
								((MainWindow) sw).btnManageTxangoa().setEnabled(false);
								
								//}else{
								//textArea.setText("Ez dago datu horiek dituen jaberik");
								//}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}else{textArea.setText("Beharrezko datuak sartu behar dituzu");}					
				}else if (rdbtnErabiltzailea.isSelected()){
					if(!textFieldIz.getText().isEmpty() && !textFieldAb.getText().isEmpty() && !passwordField.getText().isEmpty()){
						
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						String iz = textFieldIz.getText();
						String ab = textFieldAb.getText();
						String p = passwordField.getText();	
						
						try {
							//Vector<Erabiltzailea> a;
							//a=facade.getErabiltzaileak();
							facade.ezabatuErabiltzailea(iz, ab, p);
							//Vector<Erabiltzailea> b;
							//b=facade.getErabiltzaileak();
							//if (a.size()>b.size()){
								JFrame sw = new MainWindow();
								sw.setVisible(true);
								((MainWindow) sw).getBoton1().setEnabled(false);
								((MainWindow) sw).getBoton2().setEnabled(false);
								((MainWindow) sw).getbtnAddRH().setEnabled(false);
								//}else{
								//textArea.setText("Ez dago datu horiek dituen erabiltzailerik");
								//}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}else{textArea.setText("Beharrezko datuak sartu behar dituzu");}
				}else{
					textArea.setText("Kontu mota bat aukeratu behar duzu");
				}
			}
		});
		btnEzabatuKontua.setBounds(260, 98, 137, 50);
		contentPane.add(btnEzabatuKontua);
		
		
	}
	
	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
