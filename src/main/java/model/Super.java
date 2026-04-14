package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

class Super implements Nafta {
    public static final String CANTIDAD_LITROS_INCORRECTO = "Ingrese en litros un valor positivo";
    private final float preciosXLitro;
    private float descuento;

    Super(float descuento, float preciosXLitro) {
        this.descuento = descuento;
        this.preciosXLitro = preciosXLitro;
    }

    @Override
    public float calcularMonto(float litros) {
        if (litros <= 0) {
            throw new RuntimeException(CANTIDAD_LITROS_INCORRECTO);
        }
        float total = litros * preciosXLitro;
        if (esDomingo()) {
            total -= total * descuento;
        }
        return total;
    }

    private boolean esDomingo() {
        LocalDate fechaActual = LocalDate.now();
        DayOfWeek diaDeLaSemana = fechaActual.getDayOfWeek();
        return diaDeLaSemana == DayOfWeek.SUNDAY;
    }

}
