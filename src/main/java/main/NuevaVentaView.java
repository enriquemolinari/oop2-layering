package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NuevaVentaView {
    private static final float PRECIO_SUPER = 500f;
    private static final float PRECIO_RESUPER = 600f;
    private static final float DESCUENTO_SUPER = 0.03f;
    private static final float DESCUENTO_RESUPER = 0.10f;
    private static final String ERROR_TIPO_NAFTA = "Debe seleccionar un tipo de Nafta";
    private static final String ERROR_LITROS = "Ingrese en litros un valor positivo";

    public static final String DEFAULT_NUMERO_LITROS = "0";
    public static final String RE_SUPER = "ReSuper";
    public static final String SUPER = "Super";
    public static final String INDICACION_INICIAL = "Indique lo que desea cargar:";
    public static final String CANTIDAD_LITROS = "Litros";
    public static final String CONFIRMAR_COMPRA = "Confirmar compra";
    public static final String NUEVA_VENTA = "Nueva Venta";

    private JRadioButton rbReSuper;
    private JRadioButton rbSuper;
    private JTextField litrosInput;
    private JFrame frame;

    protected JComponent createOptionControls() {
        JLabel label1 = new JLabel(INDICACION_INICIAL);
        var bGroup = new ButtonGroup();

        this.rbReSuper = new JRadioButton();
        rbReSuper.setText(RE_SUPER);
        bGroup.add(rbReSuper);

        this.rbSuper = new JRadioButton();
        rbSuper.setText(SUPER);
        bGroup.add(rbSuper);

        this.litrosInput = new JTextField();
        this.litrosInput.setText(DEFAULT_NUMERO_LITROS);

        Box box = Box.createVerticalBox();
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        box.add(rbReSuper);
        box.add(rbSuper);

        Box box2 = Box.createHorizontalBox();
        JLabel label = new JLabel(CANTIDAD_LITROS);
        box2.add(label);
        box2.add(Box.createHorizontalStrut(5));
        box2.add(litrosInput);
        box.add(box2);

        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return box;
    }

    protected JComponent createButtonPane() {
        JButton button = new JButton(CONFIRMAR_COMPRA);
        var nuevaVentaView = this;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TipoDeCombustible tipo = nuevaVentaView.rbReSuper.isSelected()
                            ? TipoDeCombustible.RESUPER
                            : (nuevaVentaView.rbSuper.isSelected() ? TipoDeCombustible.SUPER : null);
                    float litrosCargados = Float.parseFloat(nuevaVentaView.litrosInput.getText());
                    if (tipo == null) {
                        throw new RuntimeException(ERROR_TIPO_NAFTA);
                    }
                    if (litrosCargados <= 0) {
                        throw new RuntimeException(ERROR_LITROS);
                    }

                    float monto = 0;
                    float subtotal = tipo == TipoDeCombustible.SUPER
                            ? litrosCargados * PRECIO_SUPER
                            : litrosCargados * PRECIO_RESUPER;

                    if (tipo == TipoDeCombustible.SUPER  && LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
                        monto = subtotal - subtotal * DESCUENTO_SUPER;
                    } else if (tipo == TipoDeCombustible.RESUPER && LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
                        monto = subtotal - subtotal * DESCUENTO_RESUPER;
                    } else {
                        monto = subtotal;
                    }
                    LocalDateTime fechaDeVenta = LocalDateTime.now();
                    String tipo1 = tipo.toString();

                    if (tipo1.length() > 10) {
                        throw new RuntimeException("Tipo de combustible no puede superar 10 caracteres");
                    }

                    try (var conexion = DriverManager.getConnection(Main.CONNSTR, Main.USERNAME, Main.PWD);
                         var stmt = conexion.prepareStatement(
                                 "insert into ventas(fecha_venta, total, litros_cargados, tipo_combustible) values(?,?,?,?)")) {
                        stmt.setTimestamp(1, Timestamp.valueOf(fechaDeVenta));
                        stmt.setFloat(2, monto);
                        stmt.setFloat(3, litrosCargados);
                        stmt.setString(4, tipo1);
                        stmt.executeUpdate();
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    float totalGastado = monto;
                    JOptionPane.showMessageDialog(frame, "Compra realizada con exito, gastaste: " + totalGastado);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.add(button);

        return pane;
    }

    public void createAndShowUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);

            frame = new JFrame(NUEVA_VENTA);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            Container contentPane = frame.getContentPane();
            contentPane.add(this.createOptionControls(), BorderLayout.CENTER);
            contentPane.add(this.createButtonPane(), BorderLayout.PAGE_END);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

