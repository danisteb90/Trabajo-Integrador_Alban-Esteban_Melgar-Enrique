package com.dh.Clase15_SpringMVC.service.impl;

import com.dh.Clase15_SpringMVC.entity.Odontologo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoServicioImpl odontologoServicio;
    public void cargarData() {
        Odontologo o = odontologoServicio.guardar(new Odontologo("Nombre", "Apellido", 12345678));
        Odontologo o1 = odontologoServicio.guardar(new Odontologo("Nombre1", "Apellido1", 12345678));
    }

    @Test
    public void testGuardarOdontologo() {
        cargarData();
        Odontologo o = odontologoServicio.buscarPorId(1L);
        assert o != null;
    }

    @Test
    public void testBuscarPorId() {
        cargarData();
        Odontologo o = odontologoServicio.buscarPorId(1L);
        assert o != null;
    }

    @Test
    public void testListarTodos() {
        cargarData();
        assert !odontologoServicio.listarTodos().isEmpty();
    }

    @Test
    public void testEliminar() {
        cargarData();
        odontologoServicio.eliminar(1L);
        // si se eliminó debemos esperar que nos regrese un ResourceNotFoundException
        try {
            odontologoServicio.buscarPorId(1L);
            assert false;
        } catch (Exception e) {
            assert e.getClass().getSimpleName().equals("ResourceNotFoundException");
        }
    }
}
