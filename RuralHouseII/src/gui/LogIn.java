package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccessLocal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {
	private static final long serialVersionUID = 1L;
	public LogIn() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("Log In");
		getContentPane().setLayout(null);
		
		JButton btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame s = new Sartu();
				s.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnSartu.setBounds(242, 74, 89, 23);
		getContentPane().add(btnSartu);
		
		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new Erregistratu();
				a.setVisible(true);
			}
		});
		btnErregistratu.setBounds(242, 113, 89, 23);
		getContentPane().add(btnErregistratu);
		
		JLabel lblBadutKontuBat = new JLabel("Badut kontu bat:");
		lblBadutKontuBat.setBounds(97, 78, 89, 14);
		getContentPane().add(lblBadutKontuBat);
		
		JLabel lblEzDutErabiltzaile = new JLabel("Ez dut erabiltzaile konturik:");
		lblEzDutErabiltzaile.setBounds(97, 117, 135, 14);
		getContentPane().add(lblEzDutErabiltzaile);
		
		JLabel lblZerEginNahi = new JLabel("ZER EGIN NAHI DUZU?");
		lblZerEginNahi.setBounds(154, 39, 118, 14);
		getContentPane().add(lblZerEginNahi);
		
		JButton btnEzabatuKontua = new JButton("Kontua Ezabatu");
		btnEzabatuKontua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame ke = new KontuaEzabatu();
				ke.setVisible(true);
			}
		});
		btnEzabatuKontua.setBounds(149, 177, 123, 50);
		contentPane.add(btnEzabatuKontua);
		
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
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LogIn frame = new LogIn();
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
	/*public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}*/
}
