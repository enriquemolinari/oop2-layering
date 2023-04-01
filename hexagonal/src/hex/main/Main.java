package hex.main;

import hex.domain.model.DefaultEstacionDeServicio;
import hex.infrastructure.data.JdbcVentas;
import hex.infrastructure.ui.CompraView;

public class Main {

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          new CompraView(new DefaultEstacionDeServicio(
              new JdbcVentas("jdbc:hsqldb:mem;create=true"))).createAndShowUI();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

}
