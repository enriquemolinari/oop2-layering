package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ListaVentasView {
    private static final String FORMATO = "MM/dd HH:mm";

    public void createAndShowUI() {
        JFrame frame = new JFrame("Lista de Ventas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fecha de Venta");
        model.addColumn("Litros Cargados");
        model.addColumn("Combustible");
        model.addColumn("Monto Total");
        List<VentaData> result;
        final String sqlTodasLasVentas = "select id_venta, fecha_venta, total, litros_cargados, tipo_combustible " +
                "from ventas order by fecha_venta desc";

        var listaVentas = new ArrayList<VentaData>();
        try (var conexion = DriverManager.getConnection(Main.CONNSTR, Main.USERNAME, Main.PWD);
             var stmt = conexion.createStatement();
             var resultSet = stmt.executeQuery(sqlTodasLasVentas)) {
            while (resultSet.next()) {
                listaVentas.add(new VentaData(resultSet.getLong("id_venta"),
                        resultSet.getTimestamp("fecha_venta").toLocalDateTime(),
                        resultSet.getFloat("total"),
                        resultSet.getFloat("litros_cargados"),
                        resultSet.getString("tipo_combustible").trim()));
            }
            result = listaVentas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (VentaData venta : result) {
            model.addRow(new Object[]{
                    formatearFecha(venta.fechaDeVenta()),
                    String.valueOf(venta.litrosCargados()),
                    venta.tipo(),
                    String.valueOf(venta.montoTotal())
            });
        }
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String formatearFecha(LocalDateTime fecha) {
        return fecha.getDayOfWeek().name().substring(0, 1).toUpperCase()
                + fecha.getDayOfWeek().name().substring(1).toLowerCase()
                + " "
                + fecha.format(DateTimeFormatter.ofPattern(FORMATO));
    }
}

