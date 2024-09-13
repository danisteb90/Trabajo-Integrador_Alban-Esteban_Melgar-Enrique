package com.dh.Clase15_SpringMVC.service.impl;

import com.dh.Clase15_SpringMVC.entity.Turno;
import com.dh.Clase15_SpringMVC.exception.BadRequestException;
import com.dh.Clase15_SpringMVC.repository.ITurnoRepository;
import com.dh.Clase15_SpringMVC.service.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicioImpl implements ITurnoServicio {

    @Autowired
    private ITurnoRepository iTurnoRepository;

    @Override
    public Turno guardar(Turno turno) {
        if (turno.getOdontologo() == null || turno.getPaciente() == null) {
            throw new BadRequestException("El turno debe tener un odontólogo y un paciente");
        }
        return iTurnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoBuscado = iTurnoRepository.findById(id);
        if (turnoBuscado.isEmpty()) {
            throw new BadRequestException("Turno no encontrado");
        }
        return turnoBuscado.get();
    }

    @Override
    public List<Turno> listarTodos() {
        return iTurnoRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        iTurnoRepository.deleteById(id);
    }

    @Override
    public Turno actualizar(Turno turno) {
        Optional<Turno> turnoBuscado = iTurnoRepository.findById(turno.getId());
        if (turnoBuscado.isEmpty()) {
            throw new BadRequestException("Turno no encontrado");
        }
        Turno turnoActualizado = turnoBuscado.get();
        turnoActualizado.setFecha(turno.getFecha());
        turnoActualizado.setOdontologo(turno.getOdontologo());
        turnoActualizado.setPaciente(turno.getPaciente());
        return iTurnoRepository.save(turnoActualizado);

    }
}
