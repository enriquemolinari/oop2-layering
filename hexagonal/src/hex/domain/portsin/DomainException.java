package hex.domain.portsin;

public class DomainException extends RuntimeException {

  public DomainException(Exception e, String msg) {
    super(msg, e);
  }

  public DomainException(String msg) {
    super(msg);
  }
}
