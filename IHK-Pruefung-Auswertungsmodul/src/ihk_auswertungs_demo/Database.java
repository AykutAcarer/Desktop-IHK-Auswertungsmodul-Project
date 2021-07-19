package ihk_auswertungs_demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class Database {

	
	static Connection conn;

	static Statement stmt;

	ResultSet rs;

																				// <<<< Connection Method
	public Connection verbinden() {

		try {


			String treiber = "com.mysql.jdbc.Driver";
			String anwender = "hbstudent";
			String password = "hbstudent";
			String url = "jdbc:mysql://localhost:3306/ihk_auswertungsmodul";

																				// <<<< Datenbanktreiber
			Class.forName(treiber);

																				// <<<< Connection-URL, benutzername und password
			conn = DriverManager.getConnection(url, anwender, password);

			System.out.println("<<<<< Verbindung ist in Ordnung mit " + url);

			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

																				// <<<< Select Method
	public ResultSet select() {

		try {

																				// <<<< Connection zur Datenbank
			conn = verbinden();

																				// <<<< Sql_Befehl für Select vorbereiten
			String sqlQuery = "select * from umschueler";
			System.out.println("<<<<<SQL-Befehl ist : " + sqlQuery);

			// <<<< Anweisung zum Senden der SQL-Abfrage an die Datenbank vorbereiten
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// result-Objekt
			rs = stmt.executeQuery(sqlQuery);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return rs;

	}

	// <<<< Insert Method
	public void insert(String nachname, String vorname, String strasse, String postzahl, String ort) {

		try {
			
			conn = verbinden();

			String sqlBefehl = "insert into umschueler (prueflings_nachname, prueflings_vorname, prueflings_strasse, prueflings_postzahl, prueflings_ort) values('"
					+ nachname + "','" + vorname + "', '" + strasse + "','" + postzahl + "','" + ort + "')";
			System.out.println(sqlBefehl);

			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			stmt.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	

	// Update Method für Umschüler
	public void update(String nachname, String vorname, String strasse, String postzahl, String ort, int id) {

		try {

			conn = verbinden();

			String sqlBefehl = "update umschueler set prueflings_nachname=?, prueflings_vorname=?, prueflings_strasse=?, prueflings_postzahl=?, prueflings_ort=? where prueflings_id=?";

			// <<<< Übertragung der SQL-Anweisung an die Datenbank vorbereiten
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);
			stmt.setString(1, nachname);
			stmt.setString(2, vorname);
			stmt.setString(3, strasse);
			stmt.setString(4, postzahl);
			stmt.setString(5, ort);
			stmt.setInt(6, id);

			stmt.executeUpdate();

			System.out.println("Updated succesfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Delete Method
	public void delete(int id) {

		try {

			conn = verbinden();

			String sqlBefehl = "delete from umschueler where prueflings_id=?";

			// <<<< Übertragung der SQL-Anweisung an die Datenbank vorbereiten
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);
			stmt.setInt(1, id);
			
			
			stmt.executeUpdate();

			System.out.println("Deleted succesfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// <<<< Insert Projektarbeit Method

	public void insertpArbeit(int id, double arbeit_erreichte_punkte, double arbeit_gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into project_arbeit (arbeit_erreichte_punkte, arbeit_mind_30_punkte, arbeit_gewichtungsfaktor, arbeit_punkte_gewischtet, prueflings_id) values("
					+ arbeit_erreichte_punkte + ",'', " + arbeit_gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			stmt.executeUpdate();

			// <<< Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			
			callpArbeit1(id, arbeit_erreichte_punkte, arbeit_gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projektarbeit Method
	public void callpArbeit1(int id, double arbeit_erreichte_punkte, double arbeit_gewichtungsfaktor) {

		try {
			
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt(?)}");

			stmt1.setDouble(2, arbeit_erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update project_arbeit set arbeit_mind_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callpArbeit2(id, str, arbeit_erreichte_punkte, arbeit_gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projektarbeit Method
	public void callpArbeit2(int id, String arbeit_mind_30_punkte, double arbeit_erreichte_punkte,
			double arbeit_gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet(?,?,?)}");
			stmt1.setString(2, arbeit_mind_30_punkte);
			stmt1.setDouble(3, arbeit_erreichte_punkte);
			stmt1.setDouble(4, arbeit_gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update project_arbeit set arbeit_punkte_gewischtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Dokumentation Method

	public void insertpDokumentation(int id, double d_erreichte_punkte, double d_gewichtungsfaktor) {

		try {
			
			conn = verbinden();

		
			String sqlBefehl = "insert into p_dokumentation (d_erreichte_punkte, d_min_30_punkte, d_gewichtungsfaktor, d_punkte_gewichtet, prueflings_id) values("
					+ d_erreichte_punkte + ",'', " + d_gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callpDokumentation1(id, d_erreichte_punkte, d_gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Dokumentation Method
	public void callpDokumentation1(int id, double d_erreichte_punkte, double d_gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_dokumentation(?)}");

			stmt1.setDouble(2, d_erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str1 = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_dokumentation set d_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str1);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callpDokumentation2(id, str1, d_erreichte_punkte, d_gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Dokumentation Method
	public void callpDokumentation2(int id, String d_min_30_punkte, double d_erreichte_punkte, double d_gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_dokumentation(?,?,?)}");
			stmt1.setString(2, d_min_30_punkte);
			stmt1.setDouble(3, d_erreichte_punkte);
			stmt1.setDouble(4, d_gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_dokumentation set d_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Fachgesprach Method

	public void insertpFachgesprach(int id, double pf_erreichte_punkt, double pf_gewischte_punkt) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into p_fachgesprach (f_erreichte_punkte, f_min_30_punkte, f_gewichtungsfaktor, f_punkte_gewichtet, prueflings_id) values("
					+ pf_erreichte_punkt + ",'', " + pf_gewischte_punkt + ",''," + id + ")";
			System.out.println(sqlBefehl);

			// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
			// Zweite Möglichkeit---> stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// Datenbank
			// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callpFachgesprach1(id, pf_erreichte_punkt, pf_gewischte_punkt);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Fachgesprach Method
	public void callpFachgesprach1(int id, double pf_erreichte_punkt, double pf_gewischte_punkt) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_fachgesprach(?)}");

			stmt1.setDouble(2, pf_erreichte_punkt);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str3 = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_fachgesprach set f_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str3);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callpFachgesprach2(id, str3, pf_erreichte_punkt, pf_gewischte_punkt);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Fachgesprach Method
	public void callpFachgesprach2(int id, String f_min_30_punkte, double pf_erreichte_punkt, double pf_gewischte_punkt) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_fachgesprach(?,?,?)}");
			stmt1.setString(2, f_min_30_punkte);
			stmt1.setDouble(3, pf_erreichte_punkt);
			stmt1.setDouble(4, pf_gewischte_punkt);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str4 = stmt1.getString(1);
			
			System.out.println("\n\n<<<<<<"+str4);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_fachgesprach set f_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str4);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Presantation Method

	public void insertpPresentation(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into p_prasentation (p_erreichte_punkte, p_min_30_punkte, p_gewichtungsfaktor, p_punkte_gewichtet, prueflings_id) values("
					+ erreichte_punkte + ",'', " + gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
			// Zweite Möglichkeit---> stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// Datenbank
			// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callpPresantation1(id, erreichte_punkte, gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Presantation Method
	public void callpPresantation1(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_prasentation(?)}");

			stmt1.setDouble(2, erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_prasentation set p_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callpPresantation2(id, str, erreichte_punkte, gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Projekt Presantation Method
	public void callpPresantation2(int id, String mind_30_punkte, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_prasentation(?,?,?)}");
			stmt1.setString(2, mind_30_punkte);
			stmt1.setDouble(3, erreichte_punkte);
			stmt1.setDouble(4, gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update p_prasentation set p_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Fachqualifikation Method

	public void insertFachqualifikation(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into fachqualifikation (f_q_erreichte_punkte, f_q_min_30_punkte, f_q_gewichtungsfaktor, f_q_punkte_gewichtet, prueflings_id) values("
					+ erreichte_punkte + ",'', " + gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
			// Zweite Möglichkeit---> stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// Datenbank
			// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callFachqualifikation1(id, erreichte_punkte, gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Fachqualifikation Method
	public void callFachqualifikation1(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_fachqualifikation(?)}");

			stmt1.setDouble(2, erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update fachqualifikation set f_q_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callFachqualifikation2(id, str, erreichte_punkte, gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Fachqualifikation Method
	public void callFachqualifikation2(int id, String mind_30_punkte, double erreichte_punkte,
			double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_fachqualifikation(?,?,?)}");
			stmt1.setString(2, mind_30_punkte);
			stmt1.setDouble(3, erreichte_punkte);
			stmt1.setDouble(4, gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update fachqualifikation set f_q_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Kernqualifikation Method

	public void insertKernqualifikation(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into kernqualifikation (k_q_erreichte_punkte, k_q_min_30_punkte, k_q_gewichtungsfaktor, k_q_punkte_gewichtet, prueflings_id) values("
					+ erreichte_punkte + ",'', " + gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
			// Zweite Möglichkeit---> stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// Datenbank
			// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callKernqualifikation1(id, erreichte_punkte, gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Kernqualifikation Method
	public void callKernqualifikation1(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_kernqualifikation(?)}");

			stmt1.setDouble(2, erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update kernqualifikation set k_q_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callKernqualifikation2(id, str, erreichte_punkte, gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Kernqualifikation Method
	public void callKernqualifikation2(int id, String mind_30_punkte, double erreichte_punkte,
			double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_kernqualifikation(?,?,?)}");
			stmt1.setString(2, mind_30_punkte);
			stmt1.setDouble(3, erreichte_punkte);
			stmt1.setDouble(4, gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update kernqualifikation set k_q_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Wirtschaftlich Method

	public void insertWirtschaftlich (int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< SQL_Anweisung für den Insert Into vorbereiten
			String sqlBefehl = "insert into wirtschaft (w_erreichte_punkte, w_min_30_punkte, w_gewichtungsfaktor, w_punkte_gewichtet, prueflings_id) values("
					+ erreichte_punkte + ",'', " + gewichtungsfaktor + ",''," + id + ")";
			System.out.println(sqlBefehl);

			// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
			// Zweite Möglichkeit---> stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// Datenbank
			// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
			stmt.executeUpdate();

			// Aufruf die method --- min_punkt()--- in SQL DB erstellt wurde----
			callWirtschaftlich1(id, erreichte_punkte, gewichtungsfaktor);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Wirtschaftlich Method
	public void callWirtschaftlich1(int id, double erreichte_punkte, double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call min_punkt_wirtschaft(?)}");

			stmt1.setDouble(2, erreichte_punkte);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update wirtschaft set w_min_30_punkte=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			callWirtschaftlich2(id, str, erreichte_punkte, gewichtungsfaktor);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// <<<< Insert Wirtschaftlich Method
	public void callWirtschaftlich2(int id, String mind_30_punkte, double erreichte_punkte,
			double gewichtungsfaktor) {

		try {
			// <<<< Connection zur Datenbank
			conn = verbinden();

			CallableStatement stmt1 = conn.prepareCall("{?=call punkte_gewischtet_wirtschaft(?,?,?)}");
			stmt1.setString(2, mind_30_punkte);
			stmt1.setDouble(3, erreichte_punkte);
			stmt1.setDouble(4, gewichtungsfaktor);
			stmt1.registerOutParameter(1, Types.VARCHAR);

			stmt1.execute();

			String str = stmt1.getString(1);

			PreparedStatement stmt3 = conn
					.prepareStatement("update wirtschaft set w_punkte_gewichtet=? where prueflings_id= ?");
			stmt3.setString(1, str);
			stmt3.setInt(2, id);

			stmt3.executeUpdate();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public ResultSet ergebnisSelect(String sqlQuery) {

	
		try {

			// <<<< Connection zur Datenbank
			conn = verbinden();

			// <<<< Anweisung zum Senden der SQL-Abfrage an die Datenbank vorbereiten
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
			// result-Objekt
			rs = stmt.executeQuery(sqlQuery);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return rs;

	}
	
	// <<<< Insert Ergebnis Method
		public void insertErgebnis(double summeTeilA, String teilAimWortlaut, double summeTeilB, String teilBimWortlaut, int id) {

			try {
				// <<<< Connection zur Datenbank
				conn = verbinden();

				// <<<< SQL_Anweisung für den Insert Into vorbereiten
				String sqlBefehl = "insert into ergebnis (summe_teil_a, teil_a_im_wortlaut, summe_teil_b, teil_b_im_wortlaut, prueflings_id) values('"
						+ summeTeilA + "','" + teilAimWortlaut + "', '" + summeTeilB + "','" + teilBimWortlaut + "','" + id + "')";
				System.out.println(sqlBefehl);

				// <<<< Anweisung zum Senden der SQL-Befehl an die Datenbank vorbereiten
				// Zweite Möglichkeit---> stmt = conn.createStatement();
				PreparedStatement stmt = conn.prepareStatement(sqlBefehl);

				// <<<< Ausfuehren der SQL-Abfrage und das Speichern der erhaltenen Daten im
				// Datenbank
				// Zweite Möglichkeit---> stmt.executeUpdate(sqlBefehl);
				stmt.executeUpdate();

				conn.close();

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

}
