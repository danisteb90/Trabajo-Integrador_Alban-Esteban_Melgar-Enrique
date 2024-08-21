package com.dh.Clase15_SpringMVC.servicio.impl;

import com.dh.Clase15_SpringMVC.dao.IDAO;
import com.dh.Clase15_SpringMVC.dao.impl.ImplementacionOdontologo;
import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;

import java.util.List;

public class OdontologoServicioImpl implements IOdontologoServicio {
    //CREO UN ATRIBUTO DE LA INTERFAZ IDAO
    private IDAO<Odontologo> odontologoIDAO;

    //EN EL CONSTRUCTOR INICIALIZO ESA INTERFAZ INDICANDOLE
    //CUAL ES LA IMPLEMENTACION
    public OdontologoServicioImpl() {
        this.odontologoIDAO = new ImplementacionOdontologo();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDAO.guardar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Integer id){
        return odontologoIDAO.consultarPorId(id);
    }

    @Override
    public boolean eliminar(Integer id) {
        return odontologoIDAO.eliminarPorId(id);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoIDAO.actualizar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDAO.listarTodos();
    }

}
