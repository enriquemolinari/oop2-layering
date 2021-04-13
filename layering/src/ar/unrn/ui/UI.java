package ar.unrn.ui;

import ar.unrn.modelo.Producto;
import ar.unrn.modelo.RepositorioDeProductos;

public class UI {

	private RepositorioDeProductos repo;
	
	public UI(RepositorioDeProductos repo) {
		this.repo = repo;
	}
	
	public void mostrarVentana() {

		//codigo swing para leventar una ventana y un form
		
		
		
		Producto p = new Producto(21, "descp", 200, 234.56);
		this.repo.nuevoProducto(p);
	}
}
