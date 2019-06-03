package hello.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;

import hello.model.PreguntaRespuesta;

public class ConnectionMySQL {

	Connection conn;
	private static ConnectionMySQL connectionObj = null;

	// singleton
	private ConnectionMySQL() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/utn", "root", "");
				
			} catch (Exception e) {
				// TODO: handle exception
			}		
	}
	
	public static ConnectionMySQL createInstance() {
		if(connectionObj == null) {
			connectionObj = new ConnectionMySQL();
		}
		return connectionObj;		
	}
	
	public void guardoPregunta(PreguntaRespuesta pregunta) {
		try {
			String sql = "insert into preguntas (pregunta,respuesta) values (?,?)";
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			ps.setString(1,pregunta.getPregunta());
			ps.setString(2,pregunta.getRespuesta());
			ps.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	 * 
	 * public void getAutos() {
	 * 
	 * try { Statement st = (Statement) getConnection().createStatement(); ResultSet
	 * rs = st.executeQuery("select * from auto"); while (rs.next()) {
	 * System.out.println("VEHICULO N " + rs.getInt("id"));
	 * System.out.println(rs.getString("marca"));
	 * System.out.println(rs.getString("modelo"));
	 * System.out.println(rs.getString("color")); }
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage());
	 * System.out.println("Rompio"); } }
	 * 
	 * public boolean insertAuto(String marca, String modelo, String color) { try {
	 * Statement st = (Statement) getConnection().createStatement();
	 * 
	 * String query = "insert into auto (marca,modelo,color) " + "values('" + marca
	 * + "','" + modelo + "','" + color + "')";
	 * 
	 * return st.execute(query);
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println(e.getMessage()); return false; } }
	 * 
	 * public boolean updateAuto(String color, int id) { try { Statement st =
	 * (Statement) getConnection().createStatement(); String query =
	 * "update auto set color = '" + color + "' where id=" + id;
	 * 
	 * return !st.execute(query);
	 * 
	 * } catch (Exception e) {
	 * 
	 * return true; } }
	 * 
	 * public boolean deleteAutoById(int id) { try { Statement st = (Statement)
	 * getConnection().createStatement();
	 * 
	 * String query = "delete from auto where id="+id;
	 * 
	 * return st.execute(query);
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); return true; } }
	 * 
	 * public List<Auto> getListOfAutos() {
	 * 
	 * try { Statement st = (Statement) getConnection().createStatement();
	 * List<Auto> lista = new ArrayList<Auto>(); ResultSet rs =
	 * st.executeQuery("select * from auto"); while (rs.next()) { lista.add(new
	 * Auto(rs.getString("marca"),rs.getString("modelo"),rs.getString("color"))); }
	 * 
	 * return lista; } catch (Exception e) { return null; } finally {
	 * 
	 * } }
	 */
}
