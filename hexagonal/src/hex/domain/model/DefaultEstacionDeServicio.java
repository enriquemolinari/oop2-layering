package hex.domain.model;

import java.util.Map;
import hex.domain.portsin.DomainException;
import hex.domain.portsin.EstacionDeServicio;
import hex.domain.portsin.TipoDeCombustible;


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
