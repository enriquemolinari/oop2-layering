package ar.unrn.modelo;

import ar.unrn.db.ProductoDatabase;

public class DbRepositorioDeProductos implements RepositorioDeProductos {

 private ProductoDatabase pdb;

 public DbRepositorioDeProductos(ProductoDatabase pdb) {
  this.pdb = pdb;
 }

 @Override
 public void nuevoProducto(Producto p) {
  this.pdb.insertarProducto(p.id(), p.descripcion(), p.puntosPorComprar(),
    p.precio());
 }
}
