package hex.infrastructure.data;

import hex.domain.portsout.Ventas;

public class JdbcVentas implements Ventas {

  private String connStr;
  
  public JdbcVentas(String connStr) {
    this.connStr = connStr;
  }

  public void nuevaVenta(double precio) {

  }

  
}
