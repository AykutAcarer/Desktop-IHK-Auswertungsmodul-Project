package ihk_auswertungs_demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javax.swing.JTextField;

public class ErgebnisFrame {
	
	
	JTextField t_id;
	JTextField t_nachname;
	JTextField t_vorname;

	public ErgebnisFrame() throws IOException {

		Database obj = new Database();

		JFrame homePage = new JFrame("Pruefung Ergebnis-Page");
		homePage.setSize(800, 600);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// --------------------------------------------------------------------//
		// 							Title									 //
		// --------------------------------------------------------------------//

		JPanel title = new JPanel();
		homePage.add(title);
		title.setBounds(0, 0, 800, 50);
		title.setBackground(new Color(64, 224, 208));
		title.setLayout(null);

		JLabel home = new JLabel("Willkommen IPA - IHK Prüfung Auswertungsmodul");
		home.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		home.setBounds(20, 15, 400, 30);

																				// <<< Menu
		JMenu menu = new JMenu("Menu");

		JMenuItem homeFrame = new JMenuItem("Home");
		homeFrame.addActionListener(new ActionListener() {
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
		// ueber.addActionListener(this);

		JMenuItem kontakt = new JMenuItem("Kontakt");
		// kontakt.addActionListener(this);

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

		menu.add(homeFrame);
		menu.add(s_list);
		menu.add(ueber);
		menu.add(kontakt);

		JMenuBar mbar = new JMenuBar();
		mbar.setBounds(700, 15, 50, 30);
		mbar.add(menu);

		title.add(mbar);
		title.add(home);

		// --------------------------------------------------------------------//
		// 						Body 											//
		// --------------------------------------------------------------------//

		JPanel body = new JPanel();
		homePage.add(body);
		body.setBounds(0, 50, 800, 540);
		body.setLayout(null);

																				// <<< Labels

		JLabel schueler_id = new JLabel("Id:");
		schueler_id.setBounds(10, 20, 20, 20);
		body.add(schueler_id);

		t_id = new JTextField();
		t_id.setBounds(35, 20, 50, 20);
		body.add(t_id);

		JLabel schueler_nachname = new JLabel("Nachname:");
		schueler_nachname.setBounds(90, 20, 70, 20);
		body.add(schueler_nachname);

		t_nachname = new JTextField();
		t_nachname.setBounds(165, 20, 95, 20);
		body.add(t_nachname);

		JLabel schueler_vorname = new JLabel("Vorname:");
		schueler_vorname.setBounds(265, 20, 60, 20);
		body.add(schueler_vorname);

		t_vorname = new JTextField();
		t_vorname.setBounds(330, 20, 95, 20);
		body.add(t_vorname);

																				// <<< Teil A Projektarbeit

		JLabel teil_a = new JLabel("Teil A Projektarbeit");
		teil_a.setBounds(10, 60, 120, 20);
		body.add(teil_a);

		JLabel erreichte_punkt = new JLabel("erreichte Punkte");
		erreichte_punkt.setBounds(190, 90, 120, 20);
		body.add(erreichte_punkt);

		JLabel gewichte_punkt = new JLabel("Gewischtungsfaktor");
		gewichte_punkt.setBounds(320, 90, 120, 20);
		body.add(gewichte_punkt);

																				// <<< Projektarbeit

		JLabel p_arbeit = new JLabel("Projektarbeit");
		p_arbeit.setBounds(25, 120, 120, 20);
		body.add(p_arbeit);

		JTextField pa_erreichte_punkt = new JTextField();
		pa_erreichte_punkt.setBounds(190, 120, 100, 20);
		body.add(pa_erreichte_punkt);

		JTextField pa_gewischte_punkt = new JTextField();
		pa_gewischte_punkt.setBounds(320, 120, 100, 20);
		body.add(pa_gewischte_punkt);

																				// <<< Projekt Dokumentation

		JLabel p_dokumentation = new JLabel("Projekt Dokumentation");
		p_dokumentation.setBounds(25, 150, 130, 20);
		body.add(p_dokumentation);

		JTextField pd_erreichte_punkt = new JTextField();
		pd_erreichte_punkt.setBounds(190, 150, 100, 20);
		body.add(pd_erreichte_punkt);

		JTextField pd_gewischte_punkt = new JTextField();
		pd_gewischte_punkt.setBounds(320, 150, 100, 20);
		body.add(pd_gewischte_punkt);

																				// <<< Projekt Praesentation

		JLabel p_presantation = new JLabel("Projekt Präsentation");
		p_presantation.setBounds(25, 180, 130, 20);
		body.add(p_presantation);

		JTextField pp_erreichte_punkt = new JTextField();
		pp_erreichte_punkt.setBounds(190, 180, 100, 20);
		body.add(pp_erreichte_punkt);

		JTextField pp_gewischte_punkt = new JTextField();
		pp_gewischte_punkt.setBounds(320, 180, 100, 20);
		body.add(pp_gewischte_punkt);

																				// <<< Projekt Fachgespräch

		JLabel p_fachgesprach = new JLabel("Projekt Fachgespräch");
		p_fachgesprach.setBounds(25, 210, 130, 20);
		body.add(p_fachgesprach);

		JTextField pf_erreichte_punkt = new JTextField();
		pf_erreichte_punkt.setBounds(190, 210, 100, 20);
		body.add(pf_erreichte_punkt);

		JTextField pf_gewischte_punkt = new JTextField();
		pf_gewischte_punkt.setBounds(320, 210, 100, 20);
		body.add(pf_gewischte_punkt);
		
		
																				// Teil B Schriftliche Prüfung

		JLabel teil_b = new JLabel("Teil B Schriftliche Prüfung");
		teil_b.setBounds(10, 270, 150, 20);
		body.add(teil_b);

		JLabel b_erreichte_punkt = new JLabel("erreichte Punkt");
		b_erreichte_punkt.setBounds(190, 300, 120, 20);
		body.add(b_erreichte_punkt);

		JLabel b_gewichte_punkt = new JLabel("Gewischtungsfaktore");
		b_gewichte_punkt.setBounds(320, 300, 140, 20);
		body.add(b_gewichte_punkt);

																				// <<< Fachqualifikation

		JLabel fachqualifikation = new JLabel("Fachqualifikation");
		fachqualifikation.setBounds(25, 330, 120, 20);
		body.add(fachqualifikation);

		JTextField f_erreichte_punkt = new JTextField();
		f_erreichte_punkt.setBounds(190, 330, 100, 20);
		body.add(f_erreichte_punkt);

		JTextField f_gewischte_punkt = new JTextField();
		f_gewischte_punkt.setBounds(320, 330, 100, 20);
		body.add(f_gewischte_punkt);

																				// <<< Kernqualifikation

		JLabel kernqualifikation = new JLabel("Kernqualifikation");
		kernqualifikation.setBounds(25, 360, 120, 20);
		body.add(kernqualifikation);

		JTextField k_erreichte_punkt = new JTextField();
		k_erreichte_punkt.setBounds(190, 360, 100, 20);
		body.add(k_erreichte_punkt);

		JTextField k_gewischte_punkt = new JTextField();
		k_gewischte_punkt.setBounds(320, 360, 100, 20);
		body.add(k_gewischte_punkt);

																				// <<< Wirtschaftlich

		JLabel wirtschaftlich = new JLabel("Wirtschaftlich");
		wirtschaftlich.setBounds(25, 390, 120, 20);
		body.add(wirtschaftlich);

		JTextField w_erreichte_punkt = new JTextField();
		w_erreichte_punkt.setBounds(190, 390, 100, 20);
		body.add(w_erreichte_punkt);

		JTextField w_gewischte_punkt = new JTextField();
		w_gewischte_punkt.setBounds(320, 390, 100, 20);
		body.add(w_gewischte_punkt);

																				// Button zum die Noten einzufügen

		JButton insert_wirtschaftlich = new JButton("Enter");
		insert_wirtschaftlich.setBounds(190, 420, 230, 30);
		insert_wirtschaftlich.setBackground(new Color(64, 224, 208));
		insert_wirtschaftlich.setForeground(Color.white);
		body.add(insert_wirtschaftlich);
		insert_wirtschaftlich.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int x = Integer.valueOf(t_id.getText());
				
				
				// Projekarbeit
				double a1 = Double.valueOf(pa_erreichte_punkt.getText());
				double b1 = Double.valueOf(pa_gewischte_punkt.getText());

				obj.insertpArbeit(x, a1, b1);
				
				//ProjekDokumentation
				double a2 = Double.valueOf(pd_erreichte_punkt.getText());
				double b2 = Double.valueOf(pd_gewischte_punkt.getText());

				obj.insertpDokumentation(x, a2, b2);
				
				// Projekt Prasentation
				double a3 = Double.valueOf(pp_erreichte_punkt.getText());
				double b3 = Double.valueOf(pp_gewischte_punkt.getText());

				obj.insertpPresentation(x, a3, b3);
				
				// Projekt Fachgesprach
				double a4 = Double.valueOf(pf_erreichte_punkt.getText());
				double b4 = Double.valueOf(pf_gewischte_punkt.getText());

				obj.insertpFachgesprach(x, a4, b4);
				
				//Fachqualifikation
				double a5 = Double.valueOf(f_erreichte_punkt.getText());
				double b5 = Double.valueOf(f_gewischte_punkt.getText());

				obj.insertFachqualifikation(x, a5, b5);
				
				//Kernqualifikation
				double a6 = Double.valueOf(k_erreichte_punkt.getText());
				double b6 = Double.valueOf(k_gewischte_punkt.getText());

				obj.insertKernqualifikation(x, a6, b6);
				
				//Wirtschaft
				double a7 = Double.valueOf(w_erreichte_punkt.getText());
				double b7 = Double.valueOf(w_gewischte_punkt.getText());

				obj.insertWirtschaftlich(x, a7, b7);
				
				
				
			}

		});


	
																				// <<< Button für Ergebnis
		
		JButton ergebnis = new JButton("Ergebnis");
		ergebnis.setBounds(650, 450, 100, 30);
		ergebnis.setBackground(Color.BLUE);
		ergebnis.setForeground(Color.white);
		body.add(ergebnis);
		ergebnis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					Ergebnis2Frame erg = new Ergebnis2Frame();
					erg.t_id.setText(t_id.getText());
					erg.t_nachname.setText(t_nachname.getText());
					erg.t_vorname.setText(t_vorname.getText());
					
					// <<< Projektarbeit
					int id1 = Integer.valueOf(t_id.getText());
					String sqlQuery_p_arbeit = "select * from project_arbeit where prueflings_id ="+id1;
					obj.ergebnisSelect(sqlQuery_p_arbeit);
					
					String str1=null; 
					String str2=null;
					
					try {
						while(obj.rs.next()) {
							str1=obj.rs.getString(3);
							str2=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.pa_min_30_punkt.setText(str1);
					erg.pa_punkte_gewischtet.setText(str2);
					
					double erg_p_arbeit=0;
					if(!str2.equalsIgnoreCase("keine wertung")) {
						
						erg_p_arbeit = Double.valueOf(str2);					//Zum Teil A
					}
																				
									
					
					// <<< Project Dokumentation
					int id2 = Integer.valueOf(t_id.getText());
					String sqlQuery_p_dok = "select * from p_dokumentation where prueflings_id ="+id2;
					obj.ergebnisSelect(sqlQuery_p_dok);
					
					String str3=null; 
					String str4=null;
					
					try {
						while(obj.rs.next()) {
							str3=obj.rs.getString(3);
							str4=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.pd_min_30_punkt.setText(str3);
					erg.pd_punkte_gewischtet.setText(str4);
					
					double erg_p_dok=0;
					if(!str4.equalsIgnoreCase("keine wertung")) {
						
						erg_p_dok = Double.valueOf(str4);
								
					}
																				//Zum summe A
										
					
					// <<< Project Fachgesprach
					int id3 = Integer.valueOf(t_id.getText());
					String sqlQuery_p_fachge = "select * from p_fachgesprach where prueflings_id ="+id3;
					obj.ergebnisSelect(sqlQuery_p_fachge);
					
					String str5=null; 
					String str6=null;
					
					try {
						while(obj.rs.next()) {
							str5=obj.rs.getString(3);
							str6=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.pf_min_30_punkt.setText(str5);
					erg.pf_punkte_gewischtet.setText(str6);
					
					double erg_p_fach=0;
					if(!str6.equalsIgnoreCase("keine wertung")) {
						
						erg_p_fach = Double.valueOf(str6);
								
					}
																				//Zum summe A
					
					
					// <<< Project Präsentation
					int id4 = Integer.valueOf(t_id.getText());
					String sqlQuery_p_pras = "select * from p_prasentation where prueflings_id ="+id4;
					obj.ergebnisSelect(sqlQuery_p_pras);
					
					String str7=null; 
					String str8=null;
					
					try {
						while(obj.rs.next()) {
							str7=obj.rs.getString(3);
							str8=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.pp_min_30_punkt.setText(str7);
					erg.pp_punkte_gewischtet.setText(str8);
					
					double erg_p_pra=0;
					if(!str8.equalsIgnoreCase("keine wertung")) {
						
						erg_p_pra = Double.valueOf(str8);						//Zum summe A
								
					}
																				
					
					// <<< Summe Teil A 
					
																				// <<< Note
					double erg_summe_a = erg_p_arbeit+erg_p_dok+erg_p_fach+erg_p_pra;
					System.out.println("Ergebnis Summe Teil A: "+erg_summe_a);
					
					String ergebnis_summe_a=Double.toString(erg_summe_a);
					erg.summe_A_erg_note.setText(ergebnis_summe_a);
					
																				// <<< Note in Wortlaut
					
					if(erg_summe_a>=92) {
						erg.summe_A_erg_wort.setText("sehr gut");
						erg.summe_A_erg_wort.setForeground(Color.green);
						erg.summe_A_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_a>=81) {
						erg.summe_A_erg_wort.setText("gut");
						erg.summe_A_erg_wort.setForeground(Color.green);
						erg.summe_A_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_a>=67) {
						erg.summe_A_erg_wort.setText("befriedigend");
						erg.summe_A_erg_wort.setForeground(Color.green);
						erg.summe_A_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_a>=50) {
						erg.summe_A_erg_wort.setText("ausreichend");
						erg.summe_A_erg_wort.setForeground(Color.green);
						erg.summe_A_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_a>=30) {
						erg.summe_A_erg_wort.setText("mangelhaft");
						erg.summe_A_erg_wort.setForeground(Color.red);
						erg.summe_A_erg_note.setForeground(Color.red);
					}
					else {
						erg.summe_A_erg_wort.setText("ungenügend");
						erg.summe_A_erg_wort.setForeground(Color.red);
						erg.summe_A_erg_note.setForeground(Color.red);
					}
					
					String erg_summe_A_wort = erg.summe_A_erg_wort.getText();	
					
																				// <<< Fachqualifikation
					int id5 = Integer.valueOf(t_id.getText());
					String sqlQuery_Fach = "select * from fachqualifikation where prueflings_id ="+id5;
					obj.ergebnisSelect(sqlQuery_Fach);
					
					String str9=null; 
					String str10=null;
					
					try {
						while(obj.rs.next()) {
							str9=obj.rs.getString(3);
							str10=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.f_min_30_punkt.setText(str9);
					erg.f_punkte_gewischtet.setText(str10);
					
					double erg_fach=0;
					if(!str10.equalsIgnoreCase("keine wertung")) {
						
						erg_fach = Double.valueOf(str10);						//Zum Teil B
					}
					
																				// <<< Kernqualifikation
					int id6 = Integer.valueOf(t_id.getText());
					String sqlQuery_Kern = "select * from kernqualifikation where prueflings_id ="+id6;
					obj.ergebnisSelect(sqlQuery_Kern);
					
					String str11=null; 
					String str12=null;
					
					try {
						while(obj.rs.next()) {
							str11=obj.rs.getString(3);
							str12=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.k_min_30_punkt.setText(str11);
					erg.k_punkte_gewischtet.setText(str12);
					
					double erg_kern=0;
					if(!str12.equalsIgnoreCase("keine wertung")) {
						
						erg_kern = Double.valueOf(str12);						//Zum Teil B
					}
					
																				// <<< Wirtscahft
					int id7 = Integer.valueOf(t_id.getText());
					String sqlQuery_Wirtschaft = "select * from wirtschaft where prueflings_id ="+id7;
					obj.ergebnisSelect(sqlQuery_Wirtschaft);
					
					String str13=null; 
					String str14=null;
					
					try {
						while(obj.rs.next()) {
							str13=obj.rs.getString(3);
							str14=obj.rs.getString(5);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erg.w_min_30_punkt.setText(str13);
					erg.w_punkte_gewischtet.setText(str14);
					
					double erg_wirt=0;
					if(!str14.equalsIgnoreCase("keine wertung")) {
						
						erg_wirt = Double.valueOf(str14);						//Zum Teil B
					}
					
					// <<< Summe Teil B
					
					double erg_summe_b = erg_fach+erg_kern+erg_wirt;
					System.out.println("Ergebnis Summe Teil B: "+erg_summe_b);
					
					String ergebnis_summe_b=Double.toString(erg_summe_b);
					erg.summe_B_erg_note.setText(ergebnis_summe_b);
					
																				//Note in Wortlaut
					
					if(erg_summe_b>=92) {
						erg.summe_B_erg_wort.setText("sehr gut");
						erg.summe_B_erg_wort.setForeground(Color.green);
						erg.summe_B_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_b>=81) {
						erg.summe_B_erg_wort.setText("gut");
						erg.summe_B_erg_wort.setForeground(Color.green);
						erg.summe_B_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_b>=67) {
						erg.summe_B_erg_wort.setText("befriedigend");
						erg.summe_B_erg_wort.setForeground(Color.green);
						erg.summe_B_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_b>=50) {
						erg.summe_B_erg_wort.setText("ausreichend");
						erg.summe_B_erg_wort.setForeground(Color.green);
						erg.summe_B_erg_note.setForeground(Color.green);
					}
					else if(erg_summe_b>=30) {
						erg.summe_B_erg_wort.setText("mangelhaft");
						erg.summe_B_erg_wort.setForeground(Color.red);
						erg.summe_B_erg_note.setForeground(Color.red);
					}
					else {
						erg.summe_B_erg_wort.setText("ungenügend");
						erg.summe_B_erg_wort.setForeground(Color.red);
						erg.summe_B_erg_note.setForeground(Color.red);
					}
					
					String erg_summe_B_wort = erg.summe_B_erg_wort.getText();
					
					// insert die Ergebniss zum Datenbank
					
					obj.insertErgebnis(erg_summe_a, erg_summe_A_wort, erg_summe_b, erg_summe_B_wort, id1);
					

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
				
				
		JPanel sub = new JPanel();
		homePage.add(sub);
		sub.setBounds(0, 590, 800, 10);
		sub.setBackground(Color.black);
		sub.setLayout(null);

		homePage.setVisible(true);

	}

	public static void main(String[] args) throws IOException {

	}
}
