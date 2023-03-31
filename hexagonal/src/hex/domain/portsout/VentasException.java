package hex.domain.portsout;

public class VentasException extends RuntimeException {

  public VentasException(Exception ex, String msg) {
    super(msg, ex);
  }
}
