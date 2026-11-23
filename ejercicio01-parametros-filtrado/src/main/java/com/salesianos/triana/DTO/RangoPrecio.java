package com.salesianos.triana.DTO;

public record RangoPrecio(Double min, Double max) {

    public static RangoPrecio parse(String texto) {
        int guion = texto.indexOf('-');
        if (guion < 0) {
            throw new IllegalArgumentException("Rango inválido (formato min-max): " + texto);
        }
        String a = texto.substring(0, guion).trim();
        String b = texto.substring(guion + 1).trim();

        Double min = a.isEmpty() ? null : Double.valueOf(a);
        Double max = b.isEmpty() ? null : Double.valueOf(b);

        if (min == null && max == null) {
            throw new IllegalArgumentException("Rango vacío: " + texto);
        }
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException("El mínimo supera al máximo: " + texto);
        }
        return new RangoPrecio(min, max);
    }
}
