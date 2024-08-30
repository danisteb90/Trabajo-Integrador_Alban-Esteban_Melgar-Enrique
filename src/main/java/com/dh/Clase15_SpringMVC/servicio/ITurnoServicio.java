package com.dh.Clase15_SpringMVC.servicio;

import com.dh.Clase15_SpringMVC.entity.Turno;

import java.util.List;

public interface ITurnoServicio {
    //CRUD
    Turno guardar(Turno turno);
    Turno buscarPorId(Long id);
    List<Turno> listarTodos();
}
