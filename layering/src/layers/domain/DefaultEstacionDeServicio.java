package layers.domain;

import java.util.Map;
import layers.domain.api.DomainException;
import layers.domain.api.EstacionDeServicio;
import layers.domain.api.TipoDeCombustible;

public class DefaultEstacionDeServicio implements EstacionDeServicio {

  private Map<String, Nafta> nafta;

  public DefaultEstacionDeServicio() {
    nafta = Map.of(TipoDeCombustible.SUPER.toString(), new Super(0.03f),
        TipoDeCombustible.RESUPER.toString(), new ReSuper(0.1f));
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
    
    combustibleCargado.calcularMonto(litrosCargados);
    

  }
}
