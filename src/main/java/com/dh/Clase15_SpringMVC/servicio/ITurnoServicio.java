package com.dh.Clase15_SpringMVC.servicio;

import com.dh.Clase15_SpringMVC.modelo.Turno;

import java.util.List;

public interface ITurnoServicio {
    //CRUD
    Turno guardar(Turno turno);
    Turno buscarPorId(Integer id);
    List<Turno> listarTodos();
}
