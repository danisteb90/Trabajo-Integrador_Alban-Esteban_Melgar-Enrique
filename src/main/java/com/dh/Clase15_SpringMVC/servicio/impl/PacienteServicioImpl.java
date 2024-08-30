package com.dh.Clase15_SpringMVC.servicio.impl;

import com.dh.Clase15_SpringMVC.entity.Paciente;
import com.dh.Clase15_SpringMVC.repository.IPacienteRepository;
import com.dh.Clase15_SpringMVC.servicio.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServicioImpl implements IPacienteServicio {
    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Override
    public Paciente guardar(Paciente paciente) {
        return iPacienteRepository.save(paciente);
    }

    @Override
    public Paciente consultarPorId(Long id) {
        Optional<Paciente> pacienteBuscado= iPacienteRepository.findById(id);
        return pacienteBuscado.orElse(null);
    }
    @Override
    public void eliminar(Long id){
        iPacienteRepository.deleteById(id);
    }
    @Override
    public List<Paciente> listarTodos() {
        return iPacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
       return iPacienteRepository.save(paciente);
    }

}
