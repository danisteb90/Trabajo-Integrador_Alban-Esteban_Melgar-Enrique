package com.dh.Clase15_SpringMVC.controller;


import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.OdontologoServicioImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller -> THYMELEAF
//RestController -> API REST
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoServicio odontologoServicio;

    public OdontologoController() {
        this.odontologoServicio = new OdontologoServicioImpl();
    }


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






}
