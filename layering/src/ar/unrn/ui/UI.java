package ar.unrn.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import ar.unrn.modelo.RepositorioDeProductos;

public class UI {

  private RepositorioDeProductos repo;

  public UI(RepositorioDeProductos repo) {
    this.repo = repo;
  }

  public void mostrarVentana() {

    JFrame frame = new JFrame("FrameDemo");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    var button = new JButton("Press here!"); 
    frame.setSize(new Dimension(170, 100));
    frame.getContentPane().add(button, BorderLayout.CENTER);

    frame.pack();

    frame.setVisible(true);



//    Producto p = new Producto(21, "descp", 200, 234.56);
//    this.repo.nuevoProducto(p);
  }
}
