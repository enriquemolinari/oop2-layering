package ar.unrn.modelo;

public class Producto {

	private int id;
	private String descripcion;
	private int puntos;
	private double precio;
	
	public Producto(int id, String descripcion, int puntos, double precio) {
		this.id = id;
		this.descripcion = descripcion;
		this.puntos = puntos;
		this.precio = precio;
	}
	
	// ...
	// lógica de negocios
	// ...
	
	public int id() {
		return this.id;
	}
	
	public String descripcion() {
		return this.descripcion;
	}
	
	public int puntosPorComprar() {
		return this.puntos;
	}
	
	public double precio() {
		return this.precio;
	}
}
