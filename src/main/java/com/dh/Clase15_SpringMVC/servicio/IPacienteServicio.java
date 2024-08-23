package com.dh.Clase15_SpringMVC.servicio;

import com.dh.Clase15_SpringMVC.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    Paciente guardar (Paciente paciente);
    Paciente consultarPorId(Integer id);
    boolean eliminar(Integer id);
    Paciente actualizar (Paciente paciente);

    List<Paciente> listarTodos();
}
