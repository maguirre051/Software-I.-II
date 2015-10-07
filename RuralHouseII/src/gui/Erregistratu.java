package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.ButtonGroup;

import gui.MainWindow;
import businessLogic.ApplicationFacadeInterface;

import javax.swing.JTextArea;

import domain.Erabiltzailea;
import domain.Owner;
import exceptions.OverlappingErabiltzaileaExists;
import exceptions.OverlappingOwnerExists;

public class Erregistratu extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Erregistratu frame = new Erregistratu();
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
	public Erregistratu() {
		setTitle("Erregistratu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JRadioButton rdbtnErabiltzailea = new JRadioButton("Erabiltzailea");
		rdbtnErabiltzailea.setBounds(77, 33, 109, 23);
		rdbtnErabiltzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(false);
			}
		});
		buttonGroup.add(rdbtnErabiltzailea);
		contentPane.add(rdbtnErabiltzailea);
		
		final JRadioButton rdbtnJabea = new JRadioButton("Jabea");
		rdbtnJabea.setBounds(191, 33, 109, 23);
		rdbtnJabea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnJabea);
		contentPane.add(rdbtnJabea);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(32, 78, 46, 14);
		contentPane.add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setBounds(32, 103, 46, 14);
		contentPane.add(lblAbizena);
		
		JLabel lblEposta = new JLabel("ePosta");
		lblEposta.setBounds(32, 128, 46, 14);
		contentPane.add(lblEposta);
		
		JLabel lblJabeKodeabeharrezkoa = new JLabel("Jabe kodea (beharrezkoa bada)");
		lblJabeKodeabeharrezkoa.setBounds(32, 153, 154, 14);
		contentPane.add(lblJabeKodeabeharrezkoa);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(32, 192, 52, 14);
		contentPane.add(lblPasahitza);
		
		JLabel lblErrepikatuPasahitza = new JLabel("Errepikatu pasahitza:");
		lblErrepikatuPasahitza.setBounds(32, 223, 109, 14);
		contentPane.add(lblErrepikatuPasahitza);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 189, 102, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(151, 220, 102, 20);
		contentPane.add(passwordField_1);
		
		textField = new JTextField();
		textField.setBounds(196, 150, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 125, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 100, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 75, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 0, 382, 25);
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
		
		JButton btnNewButton = new JButton("Erregistratu!");
		btnNewButton.setBounds(301, 175, 109, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnJabea.isSelected()){					
					if (!passwordField.getText().isEmpty() && !passwordField_1.getText().isEmpty() && !textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_3.getText().isEmpty()){
						if (passwordField.getText().equals(passwordField_1.getText())){	
							if (textField.getText().equals("JBK00")){
								
								//MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
								ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
								textArea.setText(" ");
								//if (existsOverlappingOwner(textField_3.getText(),textField_2.getText())==false){
								
								try {
									if(facade!=null){
										facade.createOwner(textField_3.getText(),textField_2.getText(), textField_1.getText(),passwordField.getText());
										
										Vector<Owner> a;
										try {
										a = facade.getOwners();
										System.out.println("Jabe zerrenda:");
											System.out.println(a.size());
											for(int i=0; i<a.size(); i++)
												System.out.println(a.get(i));
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									

										JFrame s = new Sartu();
										s.setVisible(true);
									}else{
										System.out.println("facade null");
									}
								} catch (OverlappingOwnerExists e1) {
									// TODO Auto-generated catch block
									//e1.printStackTrace();
									textArea.setText("Jabe hori existitzen da jada");
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	
							}else{textArea.setText("Jabe kodea ez da zuzena");}
						}else{
							textArea.setText("Bi pasahitzak ez datoz bat");
						}
					}else{
						textArea.setText("Beharrezko datuak sartu behar dituzu");
					}
				}else if (rdbtnErabiltzailea.isSelected()){
					if (!passwordField.getText().isEmpty() && !passwordField_1.getText().isEmpty() && !textField_3.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()){
						if (passwordField.getText().equals(passwordField_1.getText())){
							ApplicationFacadeInterface facade = MainWindow.getBusinessLogic();
							textArea.setText(" ");
							
							
								try {
									facade.createErabiltzailea(textField_3.getText(), textField_2.getText(),textField_1.getText(),passwordField.getText());
									System.out.println("ondo");
									
									Vector<Erabiltzailea> a;
									try {
										a = facade.getErabiltzaileak();
										System.out.println("Erabiltzaile zerrenda:");
										System.out.println(a.size());
										for(int i=0; i<a.size(); i++)
											System.out.println(a.get(i));
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								
								
									JFrame s = new Sartu();
									s.setVisible(true);
								} catch (OverlappingErabiltzaileaExists e1) {
									// TODO Auto-generated catch block
									//e1.printStackTrace();
									textArea.setText("Erabiltzaile hori existitzen da jada");
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								
						}else{
							textArea.setText("Bi pasahitzak ez datoz bat");	
						}	
					}else{
						textArea.setText("Beharrezko datuak sartu behar dituzu");
					}
				}else{
					textArea.setText("Erabiltzaile edo Jabe modua aukeratu behar duzu");
				}
			}	
		});
		contentPane.add(btnNewButton);
		
	}
	
	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
