package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import domain.Erabiltzailea;
import domain.Iruzkina;
import domain.RuralHouse;

import javax.swing.JTextArea;

import businessLogic.ApplicationFacadeInterface;

public class IruzkinaEzabatu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					IruzkinaEzabatu frame = new IruzkinaEzabatu();
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
	public IruzkinaEzabatu(final Erabiltzailea er) {
		setTitle("Iruzkinak ezabatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
		ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(346, 5, 83, 23);
		contentPane.add(btnAtzera);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(60, 224, 312, 22);
		contentPane.add(textArea);
		btnAtzera.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	        	btnAtzera_actionPerformed(e);
	        }
	      });
		
		final JList list;
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 414, 140);
		contentPane.add(scrollPane);
		Vector<Iruzkina> all = new Vector();
		Vector<Iruzkina> my = new Vector();
		try {
			all=facade.getAllIruzkina();
			for (int i=0; i<all.size(); i++){
				if (all.get(i).getErabiltzailea().equals(er)){my.add(all.get(i));}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list = new JList(my);
		scrollPane.setViewportView(list);
		
		JButton btnEzabatu = new JButton("Ezabatu");
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.setBussinessLogic(MainWindow.getBusinessLogic());
				ApplicationFacadeInterface facade =  MainWindow.getBusinessLogic();
//				Iruzkina i= (Iruzkina) list.getSelectedValue();
//				RuralHouse r= i.getRH();
//				Erabiltzailea e = i.getErabiltzailea();
//				Date d = i.getData();
				if (!list.isSelectionEmpty()){
					try {
						Iruzkina i= (Iruzkina) list.getSelectedValue();
						RuralHouse r= i.getRH();
						Erabiltzailea e = i.getErabiltzailea();
						Date d = i.getData();
						facade.ezabatuIruzkina(r, e, d);
						textArea.setText("Ondo ezabatu da");
						System.out.println("Ondo ezabatu da");
					
					
						JFrame s = new MainWindow(er);
						s.setVisible(true);
						((MainWindow) s).getbtnAddRH().setEnabled(false);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JFrame sw = new MainWindow(er);
					sw.setVisible(true);
					((MainWindow) sw).getBoton2().setEnabled(false);
					((MainWindow) sw).getbtnAddRH().setEnabled(false);
					((MainWindow) sw).btnIruzkindu().setEnabled(true);
					
				}else{textArea.setText("Ez dago iruzkinik ezabatzeko");}
			}
		});
		btnEzabatu.setBounds(160, 190, 89, 23);
		contentPane.add(btnEzabatu);
		
	}
	
	private void btnAtzera_actionPerformed(ActionEvent e)
	  {
	    this.setVisible(false);
	  }
}
