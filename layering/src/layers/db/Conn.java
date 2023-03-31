package layers.db;

import java.sql.Connection;
import java.sql.DriverManager;
import layers.db.api.VentasDbException;

class Conn {
  
  private static final String PWD = "app";
  private static final String USER = "app";
  private String conn;
  private Connection connection;
  
  public Conn(String conn) {
    this.conn = conn;
  }
  
  Connection open() {
    try {
      String url = this.conn;
      String user = USER;
      String password = PWD;

      connection = DriverManager.getConnection(url, user, password);

      return connection;
    } catch (Exception ex) {
      throw new VentasDbException("No fue posible conectarse a la base de datos", ex);
    }
  }
}
