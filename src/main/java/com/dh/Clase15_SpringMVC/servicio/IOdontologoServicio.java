package com.dh.Clase15_SpringMVC.servicio;

import com.dh.Clase15_SpringMVC.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {

    Odontologo guardar (Odontologo odontologo);

    Odontologo buscarPorId(Integer id);

    boolean eliminar(Integer id);

    Odontologo actualizar (Odontologo odontologo);

    List<Odontologo> listarTodos();
}
