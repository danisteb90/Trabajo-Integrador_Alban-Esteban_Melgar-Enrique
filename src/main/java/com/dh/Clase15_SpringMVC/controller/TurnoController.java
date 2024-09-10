package com.dh.Clase15_SpringMVC.controller;

import com.dh.Clase15_SpringMVC.entity.Turno;
import com.dh.Clase15_SpringMVC.exception.BadRequestException;
import com.dh.Clase15_SpringMVC.servicio.ITurnoServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.TurnoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoServicio iTurnoServicio;

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        // revisar que turno no sea null
        if (turno == null) {
            throw new BadRequestException("El turno debe contener datos");
        }
        // checar si el turno tiene odontologo y paciente, si no tiene alguno de los dos, devolver un bad request

        return ResponseEntity.ok(iTurnoServicio.guardar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(iTurnoServicio.buscarPorId(id));
    }
}
