package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Erabiltzailea;
import domain.Txangoa;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

import businessLogic.ApplicationFacadeInterface;

public class TxangoApDes extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TxangoApDes frame = new TxangoApDes();
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
	public TxangoApDes(final Erabiltzailea e) {
		setTitle("Apuntatu/Desapuntatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		final JScrollPane scrollPaneAp = new JScrollPane();
		scrollPaneAp.setBounds(44, 162, 236, -95);
		contentPane.add(scrollPaneAp);
		
		final JScrollPane scrollPaneDes = new JScrollPane();
		scrollPaneDes.setBounds(44, 316, 236, -113);
		contentPane.add(scrollPaneDes);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(44, 327, 368, 22);
		contentPane.add(textArea);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(345, 0, 89, 28);
		contentPane.add(btnAtzera);
		
		ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
		Vector<Txangoa> vt = new Vector<Txangoa>();
		try {
			vt=facade.getAllTxangoak();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String iz = e.getIzena();
		String ab = e.getAbizena();
		Vector<Txangoa> vap = new Vector<Txangoa>();
		try {
			vap=facade.getTxangoakE(iz,ab);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		final JList<Txangoa> listDesapuntatu = new JList<Txangoa>(vap);
		listDesapuntatu.setBounds(44, 203, 236, 102);
		contentPane.add(listDesapuntatu);
		
		final JList<Txangoa> listApuntatu = new JList<Txangoa>(vt);
		listApuntatu.setBounds(44, 78, 236, 95);
		contentPane.add(listApuntatu);
		
		JLabel lblAukeratuTxangoBat = new JLabel("Aukeratu txango bat apuntatzeko:");
		lblAukeratuTxangoBat.setBounds(20, 53, 213, 14);
		contentPane.add(lblAukeratuTxangoBat);
		
		JLabel lblAukeratuTxangoBat_1 = new JLabel("Aukeratu txango bat desapuntatzeko:");
		lblAukeratuTxangoBat_1.setBounds(20, 184, 213, 14);
		contentPane.add(lblAukeratuTxangoBat_1);
		btnAtzera.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	btnAtzera_actionPerformed(e);
	        }
	      });
		
		scrollPaneAp.setEnabled(false);
		scrollPaneDes.setEnabled(false);
		listApuntatu.setEnabled(false);
		listDesapuntatu.setEnabled(false);
		
		final JRadioButton rdbtnApuntatu = new JRadioButton("Apuntatu");
		rdbtnApuntatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPaneAp.setEnabled(true);
				scrollPaneDes.setEnabled(false);
				listApuntatu.setEnabled(true);
				listDesapuntatu.setEnabled(false);
				if(listApuntatu.size().equals(0)){textArea.setText("Ez dago txangorik. Sakatu 'Atzera'.");}	
			}
		});
		buttonGroup.add(rdbtnApuntatu);
		rdbtnApuntatu.setBounds(44, 21, 109, 23);
		contentPane.add(rdbtnApuntatu);
		
		final JRadioButton rdbtnDesapuntatu = new JRadioButton("Desapuntatu");
		rdbtnDesapuntatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPaneAp.setEnabled(false);
				scrollPaneDes.setEnabled(true);
				listApuntatu.setEnabled(false);
				listDesapuntatu.setEnabled(true);
				if(listDesapuntatu.size().equals(null)){textArea.setText("Ez dago txangorik. Sakatu 'Atzera'.");}	
			}
		});
		buttonGroup.add(rdbtnDesapuntatu);
		rdbtnDesapuntatu.setBounds(155, 21, 109, 23);
		contentPane.add(rdbtnDesapuntatu);
		
		JButton btnApDes = new JButton("Apuntatu / \r\nDesapuntatu");
		btnApDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnApuntatu.isSelected()){
					if(!listApuntatu.isSelectionEmpty()){
						Txangoa t = listApuntatu.getSelectedValue();
						Vector<Erabiltzailea> v = t.getApuntatuak();
						boolean b = false;
						for (int i=0; i<v.size();i++){
							if (v.get(i).equals(e)){
								b=true;
								System.out.println("errepikatuta");
							}
						}
						if (b==false){
							System.out.println("indu");
							v.addElement(e);
							t.setApuntatuak(v);
							ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
							try {
								facade.storeTxangoa(t);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					
							JFrame sw = new MainWindow(e);
							sw.setVisible(true);
							((MainWindow) sw).getBoton2().setEnabled(false);
							((MainWindow) sw).getbtnAddRH().setEnabled(false);
							((MainWindow) sw).btnIruzkindu().setEnabled(true);
							((MainWindow) sw).btnTxangoApDes().setEnabled(true);
						}else{textArea.setText("Dagoeneko apuntatu zara txango horretan.");}
					}else{textArea.setText("Txango bat aukeratu behar duzu.");}	
				}else if (rdbtnDesapuntatu.isSelected()){
					if(!listDesapuntatu.isSelectionEmpty()){
						Txangoa t = listDesapuntatu.getSelectedValue();
						Vector<Erabiltzailea> v = t.getApuntatuak();
						for (int i=0; i<v.size(); i++){
							if (v.get(i).equals(e)){v.remove(i); System.out.println("ezabatu da");}		
						}
						t.setApuntatuak(v);
						ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
						try {
							facade.storeTxangoa(t);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						JFrame sw = new MainWindow(e);
						sw.setVisible(true);
						((MainWindow) sw).getBoton2().setEnabled(false);
						((MainWindow) sw).getbtnAddRH().setEnabled(false);
						((MainWindow) sw).btnIruzkindu().setEnabled(true);
						((MainWindow) sw).btnTxangoApDes().setEnabled(true);
						
						
					}else{textArea.setText("Txango bat aukeratu behar duzu.");}					
				}else{textArea.setText("Aukeratu 'apuntatu' edo 'desapuntatu'.");}
			}
		});
		btnApDes.setBounds(290, 159, 178, 70);
		contentPane.add(btnApDes);

	}
	
	 private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
