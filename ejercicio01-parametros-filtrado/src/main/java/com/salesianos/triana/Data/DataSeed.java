package com.salesianos.triana.Data;

import com.salesianos.triana.model.Producto;
import com.salesianos.triana.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {

    private final ProductoRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        repository.saveAll(List.of(
                p("Camiseta básica algodón", 25.00, "Nike"),
                p("Camiseta técnica running", 34.90, "Adidas"),
                p("Gorra deportiva", 27.50, "Puma"),
                p("Calcetines pack 3", 29.99, "Nike"),
                p("Mochila urbana", 45.00, "Adidas"),
                p("Sudadera con capucha", 59.95, "Puma"),
                p("Zapatillas running", 89.90, "Nike"),
                p("Zapatillas casual", 79.00, "Adidas"),
                p("Pantalón chándal", 49.90, "Puma"),
                p("Chaqueta cortavientos", 74.50, "Nike"),
                p("Auriculares bluetooth", 59.00, "Sony"),
                p("Altavoz portátil", 99.00, "Sony"),
                p("Teclado mecánico", 119.00, "Logitech"),
                p("Ratón inalámbrico", 39.90, "Logitech"),
                p("Webcam Full HD", 69.00, "Logitech"),
                p("Monitor 24 pulgadas", 149.00, "Samsung"),
                p("Monitor 27 pulgadas 4K", 329.00, "Samsung"),
                p("Tablet 10 pulgadas", 229.00, "Samsung"),
                p("Smartwatch deportivo", 189.00, "Samsung")
        ));
    }

    private Producto p(String nombre, double precio, String marca) {
        return Producto.builder().nombre(nombre).precio(precio).marca(marca).build();
    }
}
