package ar.unrn.main;

import ar.unrn.db.JdbcProducto;
import ar.unrn.modelo.DbRepositorioDeProductos;
import ar.unrn.ui.UI;

public class Main {

 public static void main(String[] args) {
  UI ui = new UI(new DbRepositorioDeProductos(new JdbcProducto()));
  ui.mostrarVentana();
 }
}
