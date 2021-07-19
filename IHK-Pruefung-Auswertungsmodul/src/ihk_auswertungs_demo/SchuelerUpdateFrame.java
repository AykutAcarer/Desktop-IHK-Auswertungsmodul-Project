package ihk_auswertungs_demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SchuelerUpdateFrame {

	JTextField t_id;
	JTextField t_nachname;
	JTextField t_vorname;
	JTextField t_strasse;
	JTextField t_postzahl;
	JTextField t_ort;
	
	
	
	public SchuelerUpdateFrame() throws IOException {
		
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
		
		JMenuItem ueber = new JMenuItem("Über uns");
		//ueber.addActionListener(this);
		
		JMenuItem kontakt = new JMenuItem("Kontakt");
		//kontakt.addActionListener(this);
		
		JMenuItem s_list = new JMenuItem("Schüler List");
		s_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					new SchuelerListFrame();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		});
		
		menu.add(s_list);
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
		
		JPanel registerForm = new JPanel();
		registerForm.setBackground(Color.LIGHT_GRAY);
		body.add(registerForm);
		registerForm.setBounds(20, 20, 280, 350);
		registerForm.setLayout(null);
		
		
		// <<< Desktop-Img
		
		BufferedImage myPicture;
		ImageIcon img;
		JLabel picLabel = new JLabel();
		
		myPicture = ImageIO
				.read(new File("C:\\Users\\CC-Student\\Desktop\\Desktop_Library_App\\img\\desktop_home.jpg"));
		
		img=new ImageIcon(myPicture);
		
		picLabel.setEnabled(false);
		picLabel.setBounds(0, 0, 800, 500);
		picLabel.setDisabledIcon(img);
		body.add(picLabel);
		
		
		
		
		// <<< Labels
		
		JLabel registration = new JLabel("Update-Form");
		registration.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		registration.setBounds(10, 20, 150, 30);
		registerForm.add(registration);
		
		JLabel l_id = new JLabel("Id:");
		l_id.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		l_id.setBounds(10, 70, 100, 30);
		registerForm.add(l_id);
		
		JLabel f_nachname = new JLabel("Nachname:");
		f_nachname.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		f_nachname.setBounds(10, 110, 100, 30);
		registerForm.add(f_nachname);
		
		JLabel l_vorname = new JLabel("Vorname:");
		l_vorname.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		l_vorname.setBounds(10, 150, 100, 30);
		registerForm.add(l_vorname);
		
		JLabel strasse = new JLabel("Strasse:");
		strasse.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		strasse.setBounds(10, 190, 100, 30);
		registerForm.add(strasse);
		
		JLabel l_postzahl = new JLabel("Postzahl:");
		l_postzahl.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		l_postzahl.setBounds(10, 230, 100, 30);
		registerForm.add(l_postzahl);
		
		JLabel l_ort = new JLabel("Ort:");
		l_ort.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 16));
		l_ort.setBounds(10, 270, 100, 30);
		registerForm.add(l_ort);
		
		// <<< TextFields
		
		t_id=new JTextField();
		t_id.setBounds(120, 70, 150, 30);
		registerForm.add(t_id);
		
		t_nachname=new JTextField();
		t_nachname.setBounds(120, 110, 150, 30);
		registerForm.add(t_nachname);
		
		t_vorname=new JTextField();
		t_vorname.setBounds(120, 150, 150, 30);
		registerForm.add(t_vorname);
	
		t_strasse=new JTextField();
		t_strasse.setBounds(120, 190, 150, 30);
		registerForm.add(t_strasse);
		
		t_postzahl=new JTextField();
		t_postzahl.setBounds(120, 230, 150, 30);
		registerForm.add(t_postzahl);
		
		t_ort=new JTextField();
		t_ort.setBounds(120, 270, 150, 30);
		registerForm.add(t_ort);
		
		// <<< Button
		
		JButton c_account=new JButton("Update");
		c_account.setBounds(190, 310, 80, 35);
		c_account.setBackground(new Color(64, 224, 208));
		c_account.setForeground(Color.white);
		registerForm.add(c_account);
		c_account.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(t_id.getText());
				String n_name=t_nachname.getText();
				String v_name=t_vorname.getText();
				String strasse=t_strasse.getText();
				String postzahl=t_postzahl.getText();
				String ort=t_ort.getText();
				
				obj.update(n_name, v_name, strasse, postzahl, ort, id);
			}
			
			
		});
		
		
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
