package ihk_auswertungs_demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SchuelerListFrame {

	
	public SchuelerListFrame() throws IOException {
		
		Database obj = new Database();
		
		JFrame homePage = new JFrame("Home-Page");
		homePage.setSize(800, 600);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//--------------------------------------------------------------------//
		//							Title									  //
		//--------------------------------------------------------------------//
		
		JPanel title = new JPanel();
		homePage.add(title);
		title.setBounds(0, 0, 800, 50);
		title.setBackground(new Color(64, 224, 208));
		title.setLayout(null);
		
		JLabel home = new JLabel("Willkommen IPA - IHK Prüfung Auswertungsmodul");
		home.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		home.setBounds(20, 15, 400, 30 );
		
		JMenu menu = new JMenu("Menu");
		
		JMenuItem home_frame = new JMenuItem("Home");
		home_frame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					new HomeFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JMenuItem ueber = new JMenuItem("Über uns");
		//ueber.addActionListener(this);
		
		JMenuItem kontakt = new JMenuItem("Kontakt");
		//kontakt.addActionListener(this);
		
		menu.add(home_frame);
		menu.add(ueber);
		menu.add(kontakt);
		
		JMenuBar mbar = new JMenuBar();
		mbar.setBounds(700, 15, 50, 30);
		mbar.add(menu);
		
		title.add(mbar);
		title.add(home);
		
		
		//--------------------------------------------------------------------//
		//							Body									  //
		//--------------------------------------------------------------------//
		
		JPanel body = new JPanel();
		homePage.add(body);
		body.setBounds(0, 50, 800, 500);
		body.setLayout(null);
		
		
		// <<< Labels
		
		JLabel registration = new JLabel("Schüler List");
		registration.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		registration.setBounds(10, 20, 150, 30);
		body.add(registration);
		
		// <<< Tabel
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 765, 350);
		body.add(scrollPane);
		
		JTable table = new JTable();
		DefaultTableModel modelim = new DefaultTableModel();
		Object [] spalten = {"Id", "Nachname", "Vorname", "Strasse", "Postzahl", "Ort"};
		Object [] row= new Object[6];
		modelim.setColumnIdentifiers(spalten);
		
		table.setBounds(10, 60, 690, 350);
		scrollPane.setViewportView(table);
		
		// <<< Button
		// <<< Zum List die Schueller von Datenbank
		
		JButton b_list =  new JButton("List");
		b_list.setBounds(10, 420, 70, 30);
		b_list.setBackground(new Color(64, 224, 208));
		b_list.setForeground(Color.white);
		body.add(b_list);
		b_list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				modelim.setRowCount(0);
				
				ResultSet rs = obj.select();
				
				try {
					while(rs.next()) {
						row[0]=rs.getInt("prueflings_id");
						row[1]=rs.getString("prueflings_nachname");
						row[2]=rs.getString("prueflings_vorname");
						row[3]=rs.getString("prueflings_strasse");
						row[4]=rs.getString("prueflings_postzahl");
						row[5]=rs.getString("prueflings_ort");
						
						modelim.addRow(row);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);
				
			}

		});
		
		JLabel l_id_schueller = new JLabel("Schüler Id:");
		l_id_schueller.setBounds(365, 420, 70, 30);
		body.add(l_id_schueller);
		
		JTextField t_id_schueller = new JTextField();
		t_id_schueller.setBounds(455, 420, 50, 30);
		body.add(t_id_schueller);
		
		JButton b_update = new JButton("Update");
		b_update.setBounds(515, 420, 80, 30);
		b_update.setBackground(new Color(64, 224, 208));
		b_update.setForeground(Color.white);
		body.add(b_update);
		b_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					SchuelerUpdateFrame updateObject = new SchuelerUpdateFrame();
					updateObject.t_id.setText(modelim.getValueAt(table.getSelectedRow(), 0).toString());
					updateObject.t_nachname.setText(modelim.getValueAt(table.getSelectedRow(), 1).toString());
					updateObject.t_vorname.setText(modelim.getValueAt(table.getSelectedRow(), 2).toString());
					updateObject.t_strasse.setText(modelim.getValueAt(table.getSelectedRow(), 3).toString());
					updateObject.t_postzahl.setText(modelim.getValueAt(table.getSelectedRow(), 4).toString());
					updateObject.t_ort.setText(modelim.getValueAt(table.getSelectedRow(), 5).toString());
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		});
		
		
		JButton b_delete = new JButton("Delete");
		b_delete.setBounds(605, 420, 80, 30);
		b_delete.setBackground(new Color(64, 224, 208));
		b_delete.setForeground(Color.white);
		body.add(b_delete);
		b_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(t_id_schueller.getText());
				obj.delete(id);
				
			}
			
			
		});
		
		JButton b_pruefung = new JButton("Prüfung");
		b_pruefung.setBounds(695, 420, 80, 30);
		b_pruefung.setBackground(Color.BLUE);
		b_pruefung.setForeground(Color.white);
		body.add(b_pruefung);
		b_pruefung.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//int id = Integer.valueOf(t_id_schueller.getText());
				try {
					ErgebnisFrame erg_frm = new ErgebnisFrame();
					erg_frm.t_id.setText(modelim.getValueAt(table.getSelectedRow(), 0).toString());
					erg_frm.t_nachname.setText(modelim.getValueAt(table.getSelectedRow(), 1).toString());
					erg_frm.t_vorname.setText(modelim.getValueAt(table.getSelectedRow(), 2).toString());
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
			
		});
		
		// <<< Desktop-Img
		
		BufferedImage myPicture;
		ImageIcon img;
		JLabel picLabel = new JLabel();
		
		myPicture = ImageIO
				.read(new File("C:\\Users\\CC-Student\\Desktop\\Desktop_Library_App\\img\\Backround.jpg"));
		
		img=new ImageIcon(myPicture);
		
		picLabel.setEnabled(false);
		picLabel.setBounds(0, 0, 800, 500);
		picLabel.setDisabledIcon(img);
		body.add(picLabel);
		
		// <<< sub-title
		JPanel sub = new JPanel();
		homePage.add(sub);
		sub.setBounds(0, 550, 800, 50);
		sub.setBackground(Color.black);
		sub.setLayout(null);
		
		
		homePage.setVisible(true);
		
	}
	
	public static void main(String [] args) throws IOException {
		
		
	}
}
