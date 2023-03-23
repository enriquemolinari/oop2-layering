package ar.unrn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ar.unrn.db.api.VentasDb;

public class JdbcVentasDb implements VentasDb {

  private String connStr;
  
  public JdbcVentasDb(String connStr) {
    this.connStr = connStr;
  }

  public void insertarVenta(double precio) {
//    try (Connection conn = obtenerConexionBD();
//        PreparedStatement statement = conn.prepareStatement(
//            "insert into product(id, description, pointstogrant, price) values (?, ?, ?, ?)");) {
//
//      statement.setInt(1, id);
//      statement.setString(2, descripcion);
//      statement.setInt(3, puntos);
//      statement.setDouble(4, precio);
//      statement.executeUpdate();
//
//    } catch (SQLException ex) {
//      throw new RuntimeException(ex);
//    }
  }

  
}
