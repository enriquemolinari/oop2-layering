package layers.db.api;

public class VentasDbException extends RuntimeException {
  public VentasDbException(String msg, Exception e) {
    super(msg, e);
  }
}
