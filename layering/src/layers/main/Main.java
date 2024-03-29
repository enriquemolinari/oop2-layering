package layers.main;

import layers.db.JdbcVentasDb;
import layers.domain.DefaultEstacionDeServicio;
import layers.ui.CompraView;

public class Main {

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          new CompraView(new DefaultEstacionDeServicio(
              new JdbcVentasDb("jdbc:hsqldb:mem;create=true")))
                  .createAndShowUI();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}
