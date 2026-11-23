package com.salesianos.triana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Producto {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private double precio;
    private String marca;
}
