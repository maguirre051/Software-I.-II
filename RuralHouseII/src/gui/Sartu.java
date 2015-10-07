package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

import domain.Erabiltzailea;
import domain.Owner;
import businessLogic.ApplicationFacadeInterface;

public class Sartu extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sartu frame = new Sartu();
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
	public Sartu() {
		setTitle("Sartu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(56, 68, 46, 14);
		contentPane.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setBounds(56, 104, 46, 14);
		contentPane.add(lblAbizena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(56, 136, 46, 14);
		contentPane.add(lblPasahitza);
		
		textField = new JTextField();
		textField.setBounds(122, 65, 113, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 101, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 133, 113, 20);
		contentPane.add(passwordField);
		
		final JRadioButton rdbtnErabiltzailea = new JRadioButton("Erabiltzailea");
		buttonGroup.add(rdbtnErabiltzailea);
		rdbtnErabiltzailea.setBounds(56, 23, 109, 23);
		contentPane.add(rdbtnErabiltzailea);
		
		final JRadioButton rdbtnJabea = new JRadioButton("Jabea");
		buttonGroup.add(rdbtnJabea);
		rdbtnJabea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rdbtnJabea.setBounds(169, 23, 109, 23);
		contentPane.add(rdbtnJabea);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 161, 374, 25);
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
		
		JButton btnNewButton = new JButton("Sartu!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnJabea.isSelected()){
					if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !passwordField.getText().isEmpty()){
						
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						String iz = textField.getText();
						String ab = textField_1.getText();
						String p = passwordField.getText();						
						//Vector<Owner> a;
						try {
							Vector<Owner> a;
							a = facade.getOwners();	
							Boolean b=false;
							for(int i=0; i<a.size(); i++){
								if (a.get(i).getName().equals(iz) && a.get(i).getAbizena().equals(ab) && a.get(i).getPassword().equals(p)){
									b=true;
									JFrame sw = new MainWindow(a.get(i));
									sw.setVisible(true);
									//blokeatu bookrural (boton1)
									((MainWindow) sw).getBoton1().setEnabled(false);
									((MainWindow) sw).getbtnAddRH().setEnabled(true);
									((MainWindow) sw).btnIruzkindu().setEnabled(false);
									((MainWindow) sw).btnManageTxangoa().setEnabled(true);
									((MainWindow) sw).btnTxangoApDes().setEnabled(false);
								}	
							}
							if (b.booleanValue()==false){ textArea.setText("Ez dago datu horiek dituen jaberik");}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		
					}else{
						textArea.setText("Beharrezko datuak sartu behar dituzu");
					}
						
				}else if (rdbtnErabiltzailea.isSelected()){
					if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !passwordField.getText().isEmpty()){
						
						//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						textArea.setText(" ");
						String iz = textField.getText();
						String ab = textField_1.getText();
						String p = passwordField.getText();						
						Vector<Erabiltzailea> a;
						try {
							a = facade.getErabiltzaileak();
							Boolean b=false;
							for(int i=0; i<a.size(); i++){
								if (a.get(i).getIzena().equals(iz) && a.get(i).getAbizena().equals(ab) && a.get(i).getPasahitza().equals(p)){
									b=true;
									JFrame sw = new MainWindow(a.get(i));									
									sw.setVisible(true);
									//blokeatu setavaile (boton2)
									((MainWindow) sw).getBoton2().setEnabled(false);
									((MainWindow) sw).getbtnAddRH().setEnabled(false);
									((MainWindow) sw).btnIruzkindu().setEnabled(true);
									((MainWindow) sw).btnManageTxangoa().setEnabled(false);
									((MainWindow) sw).btnTxangoApDes().setEnabled(true);
								}						
							}
							if (b.booleanValue()==false){ textArea.setText("Ez dago datu horiek dituen erabiltzailerik");}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						textArea.setText("Beharrezko datuak sartu behar dituzu");
					}
						
				}else{
					textArea.setText("Erabiltzaile edo Jabe modua aukeratu behar duzu");
					
				}
			}
		});
		btnNewButton.setBounds(122, 193, 113, 23);
		contentPane.add(btnNewButton);
		
		
	}
	
	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
