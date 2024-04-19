package model;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroDeVentas {
    void nuevaVenta(LocalDateTime fechaDeVenta,
                    float montoTotal,
                    float cantidadLitros,
                    String tipo);

    List<Venta> ventas();
}
