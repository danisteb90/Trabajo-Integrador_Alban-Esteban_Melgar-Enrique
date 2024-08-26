package com.dh.Clase15_SpringMVC.controller;

import com.dh.Clase15_SpringMVC.modelo.Turno;
import com.dh.Clase15_SpringMVC.servicio.ITurnoServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.TurnoServicioImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoServicio iTurnoServicio;

    public TurnoController() {
        this.iTurnoServicio = new TurnoServicioImpl();
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(iTurnoServicio.guardar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) {

        //TODO que pasa si el id del paciente o del odontologo
        //que recibe el turno no existen
        return ResponseEntity.ok(iTurnoServicio.buscarPorId(id));
    }
}
