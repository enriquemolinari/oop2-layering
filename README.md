# Layering

Mostramos como implementar una aplicacion estructurada en capas en Java. Cada layer esta representado por un paquete.

## Requerimientos

Registrar las ventas de una estación de servicio. La estación de servicio vende dos tipos de Nafta: Super y ReSuper.
Los días domingo la nafta Super tiene un 3% de descuento y los días jueves la nafta ReSuper tiene 10% de descuento.
Se necesita registrar cada venta con su fecha, litros cargados, el tipo de combustible y el monto total.
Además se necesita acceder a un listado de ventas.

## Visualizando Dependencias entre Paquetes

Veamos las dependencias entre packages
usando [jdeps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdeps.html). Parado en la raiz del proyecto
podemos escribir:

`jdeps -verbose:package -p database -p model -p ui .` 