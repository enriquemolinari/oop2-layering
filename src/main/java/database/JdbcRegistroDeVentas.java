package database;

import model.RegistroDeVentas;
import model.TipoDeCombustible;
import model.Venta;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRegistroDeVentas implements RegistroDeVentas {

    private Conn conn;

    public JdbcRegistroDeVentas(String conn, String username, String pwd) {
        this.conn = new Conn(conn, username, pwd);
    }

    @Override
    public void nuevaVenta(LocalDateTime fechaDeVenta,
                           float montoTotal,
                           float cantidadLitros,
                           String tipo) {
        assertLenTipoCombustible(tipo);

        try (var conexion = this.conn.open();
             var stmt = conexion.prepareStatement(
                     "insert into ventas(fecha_venta, total, litros_cargados, tipo_combustible) " +
                             "values(?,?,?,?)");) {
            stmt.setTimestamp(1, Timestamp.valueOf(fechaDeVenta));
            stmt.setFloat(2, montoTotal);
            stmt.setFloat(3, cantidadLitros);
            stmt.setString(4, tipo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Venta> ventas() {
        final String SQL_TODAS_LAS_VENTAS = "select id_venta, fecha_venta, total, litros_cargados, tipo_combustible " +
                "from ventas order by fecha_venta desc";
        var listaVentas = new ArrayList<Venta>();
        try (var conexion = this.conn.open();
             var stmt = conexion.createStatement();
             var resultSet = stmt.executeQuery(SQL_TODAS_LAS_VENTAS);) {
            while (resultSet.next()) {
                listaVentas.add(new Venta(resultSet.getLong("id_venta"),
                        resultSet.getTimestamp("fecha_venta").toLocalDateTime(),
                        TipoDeCombustible.valueOf(resultSet.getString("tipo_combustible").trim()),
                        resultSet.getFloat("total"),
                        resultSet.getFloat("litros_cargados")
                ));
            }
            return listaVentas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertLenTipoCombustible(String tipo) {
        if (tipo.length() > 10) {
            throw new RuntimeException("Tipo de combustible no puede superar 10 caracteres");
        }
    }
}
