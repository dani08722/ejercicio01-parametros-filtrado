package com.salesianos.triana.Specs;

import com.salesianos.triana.DTO.RangoPrecio;
import com.salesianos.triana.model.Producto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.PredicateSpecification;

import java.util.List;

public final class ProductoSpecs {

    private ProductoSpecs() { }

    public static PredicateSpecification<Producto> desde(
            List<String> rangos, String nombre, String marca) {

        PredicateSpecification<Producto> spec = (root, cb) -> cb.conjunction();

        if (nombre != null && !nombre.isBlank()) {
            spec = spec.and(nombreContiene(nombre));
        }
        if (marca != null && !marca.isBlank()) {
            spec = spec.and(marcaIgual(marca));
        }
        if (rangos != null && !rangos.isEmpty()) {
            List<RangoPrecio> rangosPrecio = rangos.stream()
                    .filter(r -> r != null && !r.isBlank())
                    .map(RangoPrecio::parse)
                    .toList();
            if (!rangosPrecio.isEmpty()) {
                spec = spec.and(precioEnAlgunRango(rangosPrecio));
            }
        }
        return spec;
    }

    public static PredicateSpecification<Producto> nombreContiene(String texto) {
        return (root, cb) ->
                cb.like(cb.lower(root.get("nombre")), "%" + texto.trim().toLowerCase() + "%");
    }

    public static PredicateSpecification<Producto> marcaIgual(String marca) {
        return (root, cb) ->
                cb.equal(cb.lower(root.get("marca")), marca.trim().toLowerCase());
    }

    public static PredicateSpecification<Producto> precioEnAlgunRango(List<RangoPrecio> rangos) {
        return (root, cb) -> {
            Predicate[] predicados = rangos.stream().map(r -> {
                if (r.min() != null && r.max() != null) {
                    return cb.between(root.<Double>get("precio"), r.min(), r.max());
                } else if (r.min() != null) {
                    return cb.greaterThanOrEqualTo(root.<Double>get("precio"), r.min());
                } else {
                    return cb.lessThanOrEqualTo(root.<Double>get("precio"), r.max());
                }
            }).toArray(Predicate[]::new);
            return cb.or(predicados);
        };
    }
}