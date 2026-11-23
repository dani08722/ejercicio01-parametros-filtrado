package com.salesianos.triana.Controller;

import com.salesianos.triana.model.Producto;
import com.salesianos.triana.Repository.ProductoRepository;
import com.salesianos.triana.Specs.ProductoSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoRepository repository;

    @GetMapping
    public List<Producto> filtrar(
            @RequestParam(name = "rangos", required = false) List<String> rangos,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "marca", required = false) String marca) {

        log.info("Filtrado de productos -> rangos: {}, nombre: {}, marca: {}", rangos, nombre, marca);

        try {
            return repository.findAll(ProductoSpecs.desde(rangos, nombre, marca));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
