package hex.infrastructure.data;

import hex.domain.portsout.VentasException;

public class SetUpDatabase {

  private Conn conn;

  public SetUpDatabase(String conn) {
    this.conn = new Conn(conn);
  }

  public void iniciar() {
    try(var connection = this.conn.open()) {
      var stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE ventas (id_venta INT NOT NULL \"\n"
          + " + \"primary key generated always as identity (start with 1, increment by 1), \"\n"
          + " + \"fecha timestamp, litros number)");
    } catch(Exception e) {
      throw new VentasException(e, "No fue posible crear la tabla ventas"); 
    } 
  }
}
