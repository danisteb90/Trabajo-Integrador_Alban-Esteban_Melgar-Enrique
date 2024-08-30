package com.dh.Clase15_SpringMVC.servicio.impl;

import com.dh.Clase15_SpringMVC.entity.Odontologo;
import com.dh.Clase15_SpringMVC.repository.IOdontologoRepository;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    //CREO UN ATRIBUTO DE LA INTERFAZ IDAO
//    private IDAO<Odontologo> odontologoIDAO;
    @Autowired
    private IOdontologoRepository iOdontologoRepository;

    //EN EL CONSTRUCTOR INICIALIZO ESA INTERFAZ INDICANDOLE
    //CUAL ES LA IMPLEMENTACION
//    public OdontologoServicioImpl() {
//        this.odontologoIDAO = new ImplementacionOdontologo();
//    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id){
        Optional<Odontologo> odontologoBuscado = iOdontologoRepository.findById(id);
        odontologoBuscado.orElse(null);
        return odontologoBuscado.get();
    }

    @Override
    public void eliminar(Long id) {
         iOdontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iOdontologoRepository.findAll();
    }

}
