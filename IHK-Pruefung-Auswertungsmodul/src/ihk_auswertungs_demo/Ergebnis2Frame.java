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

public class Ergebnis2Frame {
	JTextField t_id;
	JTextField t_nachname;
	JTextField t_vorname;
	JLabel pa_min_30_punkt;
	JLabel pa_punkte_gewischtet;
	JLabel pd_min_30_punkt;
	JLabel pd_punkte_gewischtet;
	JLabel pf_min_30_punkt;
	JLabel pf_punkte_gewischtet;
	JLabel pp_min_30_punkt;
	JLabel pp_punkte_gewischtet;
	JLabel f_min_30_punkt;
	JLabel f_punkte_gewischtet;
	JLabel k_min_30_punkt;
	JLabel k_punkte_gewischtet;
	JLabel w_min_30_punkt;
	JLabel w_punkte_gewischtet;
	JLabel summe_A_erg_note;
	JLabel summe_A_erg_wort;
	JLabel summe_B_erg_note;
	JLabel summe_B_erg_wort;

	public Ergebnis2Frame() throws IOException {

		Database obj = new Database();

		JFrame homePage = new JFrame("Pruefung Ergebnis-Page");
		homePage.setSize(800, 600);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// --------------------------------------------------------------------//
		// 									Title 								//
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
		// 							Body 										//
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

		JLabel erreichte_punkt = new JLabel("min. 30 Punkt");
		erreichte_punkt.setBounds(190, 90, 120, 20);
		body.add(erreichte_punkt);

		JLabel gewichte_punkt = new JLabel("Punkte gewichtet");
		gewichte_punkt.setBounds(320, 90, 120, 20);
		body.add(gewichte_punkt);

																				// <<< Projektarbeit

		JLabel p_arbeit = new JLabel("Projektarbeit");
		p_arbeit.setBounds(25, 120, 120, 20);
		body.add(p_arbeit);

		pa_min_30_punkt = new JLabel();
		pa_min_30_punkt.setBounds(190, 120, 100, 20);
		body.add(pa_min_30_punkt);

		pa_punkte_gewischtet = new JLabel();
		pa_punkte_gewischtet.setBounds(320, 120, 100, 20);
		body.add(pa_punkte_gewischtet);

																				// <<< Projekt Dokumentation

		JLabel p_dokumentation = new JLabel("Projekt Dokumentation");
		p_dokumentation.setBounds(25, 150, 130, 20);
		body.add(p_dokumentation);

		pd_min_30_punkt = new JLabel();
		pd_min_30_punkt.setBounds(190, 150, 100, 20);
		body.add(pd_min_30_punkt);

		pd_punkte_gewischtet = new JLabel();
		pd_punkte_gewischtet.setBounds(320, 150, 100, 20);
		body.add(pd_punkte_gewischtet);

																				// <<< Projekt Praesentation

		JLabel p_presantation = new JLabel("Projekt Präsentation");
		p_presantation.setBounds(25, 180, 130, 20);
		body.add(p_presantation);

		pp_min_30_punkt = new JLabel();
		pp_min_30_punkt.setBounds(190, 180, 100, 20);
		body.add(pp_min_30_punkt);

		pp_punkte_gewischtet = new JLabel();
		pp_punkte_gewischtet.setBounds(320, 180, 100, 20);
		body.add(pp_punkte_gewischtet);

																				// <<< Projekt Fachgespräch

		JLabel p_fachgesprach = new JLabel("Projekt Fachgespräch");
		p_fachgesprach.setBounds(25, 210, 130, 20);
		body.add(p_fachgesprach);

		pf_min_30_punkt = new JLabel();
		pf_min_30_punkt.setBounds(190, 210, 100, 20);
		body.add(pf_min_30_punkt);

		pf_punkte_gewischtet = new JLabel();
		pf_punkte_gewischtet.setBounds(320, 210, 100, 20);
		body.add(pf_punkte_gewischtet);

																				// <<< Summe Teil A
		
		JLabel summe_A = new JLabel("Summe Teil A:");
		summe_A.setBounds(500, 90, 150, 20);
		summe_A.setForeground(Color.BLUE);
		body.add(summe_A);

		summe_A_erg_note = new JLabel();
		summe_A_erg_note.setBounds(500, 120, 100, 20);
		body.add(summe_A_erg_note);

		summe_A_erg_wort = new JLabel();
		summe_A_erg_wort.setBounds(500, 150, 100, 20);
		body.add(summe_A_erg_wort);

																				// Teil B Schriftliche Prüfung

		JLabel teil_b = new JLabel("Teil B Schriftliche Prüfung");
		teil_b.setBounds(10, 270, 150, 20);
		body.add(teil_b);

		JLabel b_erreichte_punkt = new JLabel("min. 30 Punkt");
		b_erreichte_punkt.setBounds(190, 300, 120, 20);
		body.add(b_erreichte_punkt);

		JLabel b_gewichte_punkt = new JLabel("Punkte gewichtet");
		b_gewichte_punkt.setBounds(320, 300, 140, 20);
		body.add(b_gewichte_punkt);

																				// <<< Fachqualifikation

		JLabel fachqualifikation = new JLabel("Fachqualifikation");
		fachqualifikation.setBounds(25, 330, 120, 20);
		body.add(fachqualifikation);

		f_min_30_punkt = new JLabel();
		f_min_30_punkt.setBounds(190, 330, 100, 20);
		body.add(f_min_30_punkt);

		f_punkte_gewischtet = new JLabel();
		f_punkte_gewischtet.setBounds(320, 330, 100, 20);
		body.add(f_punkte_gewischtet);

																				// <<< Kernqualifikation

		JLabel kernqualifikation = new JLabel("Kernqualifikation");
		kernqualifikation.setBounds(25, 360, 120, 20);
		body.add(kernqualifikation);

		k_min_30_punkt = new JLabel();
		k_min_30_punkt.setBounds(190, 360, 100, 20);
		body.add(k_min_30_punkt);

		k_punkte_gewischtet = new JLabel();
		k_punkte_gewischtet.setBounds(320, 360, 100, 20);
		body.add(k_punkte_gewischtet);

																				// <<< Wirtschaftlich

		JLabel wirtschaftlich = new JLabel("Wirtschaftlich");
		wirtschaftlich.setBounds(25, 390, 120, 20);
		body.add(wirtschaftlich);

		w_min_30_punkt = new JLabel();
		w_min_30_punkt.setBounds(190, 390, 100, 20);
		body.add(w_min_30_punkt);

		w_punkte_gewischtet = new JLabel();
		w_punkte_gewischtet.setBounds(320, 390, 100, 20);
		body.add(w_punkte_gewischtet);

																				// <<< Summe Teil B
		
		JLabel summe_B = new JLabel("Summe Teil B:");
		summe_B.setBounds(500, 300, 150, 20);
		summe_B.setForeground(Color.BLUE);
		body.add(summe_B);

		summe_B_erg_note = new JLabel();
		summe_B_erg_note.setBounds(500, 330, 100, 20);
		body.add(summe_B_erg_note);

		summe_B_erg_wort = new JLabel();
		summe_B_erg_wort.setBounds(500, 360, 100, 20);
		body.add(summe_B_erg_wort);
		
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
