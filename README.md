# Layering

Mostramos como implementar una aplicacion estructurada en capas en Java. Cada layer esta representado por un paquete.

## Requerimientos

Registrar las ventas de una estación de servicio. La estación de servicio vende dos tipos de Nafta: Super y ReSuper.
Los días domingo la nafta Super tiene un 3% de descuento y los días jueves la nafta ReSuper tiene 10% de descuento.
Se necesita registrar cada venta con su fecha, litros cargados, el tipo de combustible y el monto total.
Además se necesita acceder a un listado de ventas.

## Problemas de la implementación sin estructura

- Imposibilidad de testear el código en forma automatizada.
- Código mezclando cuestiones de vista con lógica de negocios con acceso a datos, lo que hace difícil de mantener y extender.
- Dificultad para reutilizar código, ya que no hay una separación clara de responsabilidades.
- Requerimientos que podrían llegar como cambiar la tecnologia de la UI es más complejo ya que tengo que tener cuidado de no romper lógica de negocios.
- De similar forma si quiero cambiar la forma de persistir los datos, tengo que tener cuidado de no romper la lógica de negocios.

## Visualizando Dependencias entre Paquetes

Veamos las dependencias entre packages
usando [jdeps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html). Parado en la raiz del proyecto
podemos escribir:

`jdeps -verbose:package -p persistencia -p modelo -p vista .` 