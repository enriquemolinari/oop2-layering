package layers.main;

import layers.db.JdbcVentasDb;
import layers.domain.DefaultEstacionDeServicio;
import layers.ui.CompraView;

public class Main {

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        
        new JdbcVentasDb("jdbc:hsqldb:mem;create=true");
        
        try {
          new CompraView(new DefaultEstacionDeServicio()).createAndShowUI();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}
