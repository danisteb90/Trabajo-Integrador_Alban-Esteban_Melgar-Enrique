package com.dh.Clase15_SpringMVC.controller;


import com.dh.Clase15_SpringMVC.entity.Domicilio;
import com.dh.Clase15_SpringMVC.entity.Paciente;
import com.dh.Clase15_SpringMVC.servicio.IPacienteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Controller -> THYMELEAF
//RestController -> API REST
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteServicio pacienteServicio;


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteServicio.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(pacienteServicio.guardar(paciente));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteExistente = Optional.ofNullable(pacienteServicio.consultarPorId(id));
        if (pacienteExistente.isPresent()) {
            Paciente pacienteActualizado = pacienteExistente.get();

            if (paciente.getNombre() != null) {
                pacienteActualizado.setNombre(paciente.getNombre());
            }
            if (paciente.getApellido() != null) {
                pacienteActualizado.setApellido(paciente.getApellido());
            }
            if (paciente.getDni() != null) {
                pacienteActualizado.setDni(paciente.getDni());
            }
            if (paciente.getFechaAlta() != null) {
                pacienteActualizado.setFechaAlta(paciente.getFechaAlta());
            }

            if (paciente.getDomicilio() != null) {
                Domicilio domicilioExistente = pacienteActualizado.getDomicilio();
                Domicilio nuevoDomicilio = paciente.getDomicilio();

                if (nuevoDomicilio.getCalle() != null) {
                    domicilioExistente.setCalle(nuevoDomicilio.getCalle());
                }
                if (nuevoDomicilio.getNumero() != null) {
                    domicilioExistente.setNumero(nuevoDomicilio.getNumero());
                }
                if (nuevoDomicilio.getLocalidad() != null) {
                    domicilioExistente.setLocalidad(nuevoDomicilio.getLocalidad());
                }
                if (nuevoDomicilio.getProvincia() != null) {
                    domicilioExistente.setProvincia(nuevoDomicilio.getProvincia());
                }
            }

            pacienteActualizado.setId(id);

            return ResponseEntity.ok(pacienteServicio.actualizar(pacienteActualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}