package ar.unrn.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CompraView extends WindowAdapter {

  private JRadioButton rbInfinia;
  private JRadioButton rbSuper;
  private JTextField litros;

  public CompraView() {

  }

  protected JComponent createOptionControls() {
    JLabel label1 = new JLabel("Indique lo que desea cargar:");
    ButtonGroup bGroup = new ButtonGroup();

    this.rbInfinia = new JRadioButton();
    rbInfinia.setText("Infinia");
    rbInfinia.setSelected(true);
    bGroup.add(rbInfinia);

    this.rbSuper = new JRadioButton();
    rbSuper.setText("Super");
    bGroup.add(rbSuper);

    this.litros = new JTextField();

    Box box = Box.createVerticalBox();
    box.add(label1);
    box.add(Box.createVerticalStrut(5)); // spacer
    box.add(rbInfinia);
    box.add(rbSuper);

    Box box2 = Box.createHorizontalBox();
    JLabel label = new JLabel("Litros");
    box2.add(label);
    box2.add(Box.createHorizontalStrut(5));
    box2.add(litros);
    box.add(box2);

    box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    return box;
  }

  protected JComponent createButtonPane() {
    JButton button = new JButton("Confirmar compra");
    var self = this;
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("super: " + self.rbSuper.isSelected());
        System.out.println("infinia: " + self.rbInfinia.isSelected());
        System.out.println(self.litros.getText());

      }
    });


    JPanel pane = new JPanel();
    pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    pane.add(button);

    return pane;
  }

  private static void createAndShowGUI()
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);

    JFrame frame = new JFrame("Compras");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    CompraView demo = new CompraView();

    Container contentPane = frame.getContentPane();
    contentPane.add(demo.createOptionControls(), BorderLayout.CENTER);

    contentPane.add(demo.createButtonPane(), BorderLayout.PAGE_END);

    frame.pack();
    frame.setLocationRelativeTo(null); // center it
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          createAndShowGUI();
        } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

  class MyFrame extends JFrame implements ActionListener {

    public MyFrame() {
      super("Sistema de Compras");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      JButton button = new JButton("Cerrar");
      button.addActionListener(this);

      Container contentPane = getContentPane();
      contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
      contentPane.add(Box.createVerticalGlue()); // takes all extra space
      contentPane.add(button);
      button.setAlignmentX(Component.CENTER_ALIGNMENT); // horizontally centered
      contentPane.add(Box.createVerticalStrut(5)); // spacer
    }

    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      dispose();
    }
  }
}
