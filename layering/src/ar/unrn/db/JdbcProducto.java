package ar.unrn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ar.unrn.db.api.ProductoDatabase;

public class JdbcProducto implements ProductoDatabase {

	public void insertarProducto(int id, String descripcion, int puntos, double precio) {
		try (Connection conn = obtenerConexionBD();
				PreparedStatement statement = conn.prepareStatement(
						"insert into product(id, description, pointstogrant, price) values (?, ?, ?, ?)");) {

			statement.setInt(1, id);
			statement.setString(2, descripcion);
			statement.setInt(3, puntos);
			statement.setDouble(4, precio);
			statement.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private Connection obtenerConexionBD() {
		try {
			String url = "jdbc:derby://localhost:1527/libro;create=false";
			String user = "app";
			String password = "app";

			Connection connection = DriverManager.getConnection(url, user, password);

			return connection;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
