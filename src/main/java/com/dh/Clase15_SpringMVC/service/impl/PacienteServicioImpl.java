package com.dh.Clase15_SpringMVC.service.impl;

import com.dh.Clase15_SpringMVC.entity.Paciente;
import com.dh.Clase15_SpringMVC.exception.BadRequestException;
import com.dh.Clase15_SpringMVC.exception.ResourceNotFoundException;
import com.dh.Clase15_SpringMVC.repository.IPacienteRepository;
import com.dh.Clase15_SpringMVC.service.IPacienteServicio;
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
        //revisar si el objeto paciente tiene todos los datos necesarios
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del paciente es obligatorio");
        }
        if (paciente.getApellido() == null || paciente.getApellido().isEmpty()) {
            throw new BadRequestException("El apellido del paciente es obligatorio");
        }
        if (paciente.getDni() == null || paciente.getDni().isEmpty()) {
            throw new BadRequestException("El DNI del paciente es obligatorio");
        }
        if (paciente.getFechaAlta() == null) {
            throw new BadRequestException("La fecha de alta del paciente es obligatoria");
        }
        if (paciente.getDomicilio() == null) {
            throw new BadRequestException("El domicilio del paciente es obligatorio");
        }
        return iPacienteRepository.save(paciente);


    }

    @Override
    public Paciente consultarPorId(Long id) {
        Optional<Paciente> pacienteBuscado= iPacienteRepository.findById(id);
        if(pacienteBuscado.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        return pacienteBuscado.get();
    }
    @Override
    public void eliminar(Long id){
        Optional<Paciente> pacienteBuscado= iPacienteRepository.findById(id);
        if(pacienteBuscado.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado para eliminar");
        }
        iPacienteRepository.deleteById(id);
    }
    @Override
    public List<Paciente> listarTodos() {
        return iPacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Optional<Paciente> pacienteExistente = Optional.ofNullable(consultarPorId(paciente.getId()));
        //si no existe lanzamos bad request
        if (pacienteExistente.isEmpty()) {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }

            Paciente pacienteActualizado = pacienteExistente.get();

            if (paciente.getNombre() != null) {
                pacienteActualizado.setNombre(paciente.getNombre());
            }
            if (paciente.getApellido() != null) {
                pacienteActualizado.setApellido(paciente.getApellido());
            }
            if (paciente.getDni() != null) {
                pacienteActualizado.setDni(paciente.getDni());
            }
            if (paciente.getFechaAlta() != null) {
                pacienteActualizado.setFechaAlta(paciente.getFechaAlta());
            }

            if (paciente.getDomicilio() != null) {
                pacienteActualizado.setDomicilio(paciente.getDomicilio());
            }

       return   iPacienteRepository.save(pacienteActualizado);
    }

}
