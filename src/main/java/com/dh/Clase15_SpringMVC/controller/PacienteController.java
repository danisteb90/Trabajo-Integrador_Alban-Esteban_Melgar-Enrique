package com.dh.Clase15_SpringMVC.controller;


import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.modelo.Paciente;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import com.dh.Clase15_SpringMVC.servicio.IPacienteServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.OdontologoServicioImpl;
import com.dh.Clase15_SpringMVC.servicio.impl.PacienteServicioImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller -> THYMELEAF
//RestController -> API REST
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteServicio pacienteServicio;

    public PacienteController() {
        this.pacienteServicio = new PacienteServicioImpl();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteServicio.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteServicio.guardar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = pacienteServicio.eliminar(id);

        if (eliminado) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Integer id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteExistente = Optional.ofNullable(pacienteServicio.consultarPorId(id));
        if (pacienteExistente.isPresent()) {
            paciente.setId(id);  // Aseguramos que el ID en el objeto coincide con el de la ruta
            return ResponseEntity.ok(pacienteServicio.actualizar(paciente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
