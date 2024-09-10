package com.dh.Clase15_SpringMVC.controller;

import com.dh.Clase15_SpringMVC.entity.Odontologo;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Controller -> THYMELEAF
//RestController -> API REST
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private IOdontologoServicio odontologoServicio;

    // -------------ENDPOINTS--------------------//

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoServicio.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId( @PathVariable Long id) {
       return ResponseEntity.ok(odontologoServicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Odontologo odontologo, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(odontologoServicio.guardar(odontologo));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoExistente = Optional.ofNullable(odontologoServicio.buscarPorId(id));
        if (odontologoExistente.isPresent()) {

            Odontologo odontologoActualizado = odontologoExistente.get();
            if (odontologo.getNombre() != null) {
                odontologoActualizado.setNombre(odontologo.getNombre());
            }
            if (odontologo.getApellido() != null) {
                odontologoActualizado.setApellido(odontologo.getApellido());
            }
            if (odontologo.getMatricula() != null) {
                odontologoActualizado.setMatricula(odontologo.getMatricula());
            }
            odontologoActualizado.setId(id);
            return ResponseEntity.ok(odontologoServicio.actualizar(odontologoActualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
