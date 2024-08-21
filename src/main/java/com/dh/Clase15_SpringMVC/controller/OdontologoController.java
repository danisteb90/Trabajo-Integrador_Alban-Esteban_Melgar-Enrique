package com.dh.Clase15_SpringMVC.controller;

import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.OdontologoServicioImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

//Controller -> THYMELEAF
//RestController -> API REST
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoServicio odontologoServicio;
    public OdontologoController() {
        this.odontologoServicio = new OdontologoServicioImpl();
    }

    // -------------ENDPOINTS--------------------//

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoServicio.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId( @PathVariable Integer id) {
       return ResponseEntity.ok(odontologoServicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoServicio.guardar(odontologo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Integer id, @RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoExistente = Optional.ofNullable(odontologoServicio.buscarPorId(id));
        if (odontologoExistente.isPresent()) {
            odontologo.setId(id);  // Aseguramos que el ID en el objeto coincide con el de la ruta
            return ResponseEntity.ok(odontologoServicio.actualizar(odontologo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = odontologoServicio.eliminar(id);

        if (eliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }







}
