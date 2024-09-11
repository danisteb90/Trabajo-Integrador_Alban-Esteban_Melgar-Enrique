package com.dh.Clase15_SpringMVC.service;

import com.dh.Clase15_SpringMVC.entity.Paciente;

import java.util.List;

public interface IPacienteServicio {
    Paciente guardar (Paciente paciente);
    Paciente consultarPorId(Long id);
    void eliminar(Long id);
    Paciente actualizar (Paciente paciente);

    List<Paciente> listarTodos();
}
