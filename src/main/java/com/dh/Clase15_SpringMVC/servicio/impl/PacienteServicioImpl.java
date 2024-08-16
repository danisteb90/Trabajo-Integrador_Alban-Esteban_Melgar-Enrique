package com.dh.Clase15_SpringMVC.servicio.impl;

import com.dh.Clase15_SpringMVC.dao.IDAO;
import com.dh.Clase15_SpringMVC.dao.impl.ImplementacionPaciente;
import com.dh.Clase15_SpringMVC.modelo.Paciente;
import com.dh.Clase15_SpringMVC.servicio.IPacienteServicio;

import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {
    private IDAO<Paciente> interfazDAO;

    public PacienteServicioImpl() {
        this.interfazDAO = new ImplementacionPaciente();
    }
    @Override
    public Paciente guardar(Paciente paciente) {
        return interfazDAO.guardar(paciente);
    }

    @Override
    public Paciente consultarPorId(Integer id) {
        return interfazDAO.consultarPorId(id);
    }
    @Override
    public void eliminar(Integer id){
        interfazDAO.eliminarPorId(id);
    }
    @Override
    public List<Paciente> listarTodos() {
        return interfazDAO.listarTodos();
    }

    @Override
    public void actualizar(Paciente paciente) {
        interfazDAO.actualizar(paciente);
    }

}
