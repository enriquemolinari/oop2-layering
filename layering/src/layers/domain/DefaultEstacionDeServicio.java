package layers.domain;

import java.util.Map;
import layers.db.api.VentasDb;
import layers.domain.api.DomainException;
import layers.domain.api.EstacionDeServicio;
import layers.domain.api.TipoDeCombustible;

public class DefaultEstacionDeServicio implements EstacionDeServicio {

  private Map<String, Nafta> nafta;
  private VentasDb ventasDb;

  public DefaultEstacionDeServicio(VentasDb dbVentas) {
    nafta = Map.of(TipoDeCombustible.SUPER.toString(), new Super(0.03f),
        TipoDeCombustible.RESUPER.toString(), new ReSuper(0.1f));
    this.ventasDb = dbVentas;
  }

  @Override
  public void nuevaVenta(TipoDeCombustible tipoConbustible,
      float litrosCargados) {
    var combustibleCargado = nafta.get(tipoConbustible.toString());

    if (combustibleCargado == null) {
      throw new DomainException("Debe seleccionar un tipo de Nafta");
    }

    if (litrosCargados < 0) {
      throw new DomainException("Ingrese en litros un valor positivo");
    }

    float monto = combustibleCargado.calcularMonto(litrosCargados);

    ventasDb.insertarVenta(monto);
  }
}
