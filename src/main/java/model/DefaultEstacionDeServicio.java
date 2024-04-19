package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DefaultEstacionDeServicio implements EstacionDeServicio {
    public static final float DESCUENTO_SUPER = 0.03f;
    public static final float DESCUENTO_RESUPER = 0.1f;
    public static final float PRECIOS_X_LITRO_SUPER = 500f;
    public static final float PRECIO_X_LITRO_RESUPER = 600f;
    public static final String FALTA_TIPO_NAFTA = "Debe seleccionar un tipo de Nafta";
    private Map<String, Nafta> naftas;
    private RegistroDeVentas registroDeVentas;

    public DefaultEstacionDeServicio(RegistroDeVentas registroDeVentas) {
        naftas = Map.of(TipoDeCombustible.SUPER.toString(),
                new Super(DESCUENTO_SUPER, PRECIOS_X_LITRO_SUPER),
                TipoDeCombustible.RESUPER.toString(),
                new ReSuper(DESCUENTO_RESUPER, PRECIO_X_LITRO_RESUPER));
        this.registroDeVentas = registroDeVentas;
    }

    @Override
    public float nuevaVenta(TipoDeCombustible tipoConbustible,
                            float litrosCargados) {
        if (tipoConbustible == null) {
            throw new RuntimeException(FALTA_TIPO_NAFTA);
        }
        var combustibleCargado = naftas.get(tipoConbustible.toString());
        float monto = combustibleCargado.calcularMonto(litrosCargados);
        this.registroDeVentas.nuevaVenta(LocalDateTime.now(),
                monto,
                litrosCargados,
                tipoConbustible.toString());
        return monto;
    }

    @Override
    public List<Venta> listarVentas() {
        return this.registroDeVentas.ventas();
    }
}
