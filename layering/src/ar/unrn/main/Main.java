package ar.unrn.main;

import ar.unrn.db.JdbcVentasDb;
import ar.unrn.domain.DefaultEstacionDeServicio;
import ar.unrn.ui.CompraView;

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
