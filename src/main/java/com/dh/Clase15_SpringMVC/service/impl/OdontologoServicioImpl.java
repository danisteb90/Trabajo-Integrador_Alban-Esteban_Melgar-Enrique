package com.dh.Clase15_SpringMVC.service.impl;

import com.dh.Clase15_SpringMVC.entity.Odontologo;
import com.dh.Clase15_SpringMVC.exception.BadRequestException;
import com.dh.Clase15_SpringMVC.exception.ResourceNotFoundException;
import com.dh.Clase15_SpringMVC.repository.IOdontologoRepository;
import com.dh.Clase15_SpringMVC.service.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {

    @Autowired
    private IOdontologoRepository iOdontologoRepository;


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del odontólogo es obligatorio");
        }
        if (odontologo.getApellido() == null || odontologo.getApellido().isEmpty()) {
            throw new BadRequestException("El apellido del odontólogo es obligatorio");
        }
        if (odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {
            throw new BadRequestException("La matrícula del odontólogo es obligatoria");
        }
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id){
        Optional<Odontologo> odontologoBuscado = iOdontologoRepository.findById(id);
        if(odontologoBuscado.isEmpty()) {
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
        return odontologoBuscado.get();
    }

    @Override
    public void eliminar(Long id) {
        Optional<Odontologo> odontologoBuscado = iOdontologoRepository.findById(id);
        if(odontologoBuscado.isEmpty()) {
            throw new ResourceNotFoundException("Odontólogo no encontrado para eliminar");
        }
         iOdontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoExistente = Optional.ofNullable(buscarPorId(odontologo.getId()));
        //si no existe lanzamos resource not found
        if (odontologoExistente.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        Odontologo odontologoActualizado = odontologoExistente.get();
        if (odontologo.getNombre() != null) {
            odontologoActualizado.setNombre(odontologo.getNombre());
        }
        if (odontologo.getApellido() != null) {
            odontologoActualizado.setApellido(odontologo.getApellido());
        }
        if (odontologo.getMatricula() != null) {
            odontologoActualizado.setMatricula(odontologo.getMatricula());
        }
        return iOdontologoRepository.save(odontologoActualizado);

    }

    @Override
    public List<Odontologo> listarTodos() {
        return iOdontologoRepository.findAll();
    }

}
