package ar.unrn.db.api;

public class DbException extends RuntimeException {
  public DbException(String msg, Exception e) {
    super(msg, e);
  }
}
