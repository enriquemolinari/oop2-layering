package layers.db;

import layers.db.api.VentasDb;

public class JdbcVentasDb implements VentasDb {

  private String connStr;
  
  public JdbcVentasDb(String connStr) {
    this.connStr = connStr;
  }

  public void insertarVenta(double precio) {

  }

  
}
