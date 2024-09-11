package com.dh.Clase15_SpringMVC.service.impl;


import com.dh.Clase15_SpringMVC.entity.Domicilio;
import com.dh.Clase15_SpringMVC.entity.Paciente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteServicioImpl pacienteServicio;

    public void cargarData() {
        Domicilio domicilio = new Domicilio("Calle", 123, "Localidad", "Provincia");
        Paciente p = pacienteServicio.guardar(new Paciente("Nombre", "Apellido", 12345678, domicilio));
        Domicilio domicilio1    = new Domicilio("Calle1", 123, "Localidad", "Provincia");
        Paciente p1 = pacienteServicio.guardar(new Paciente("Nombre1", "Apellido1", 12345678, domicilio1));

    }

    @Test
    public void testGuardarPaciente() {
        cargarData();
        Paciente p = pacienteServicio.consultarPorId(1L);
        assert p != null;
    }

    @Test
    public void testConsultarPorId() {
       cargarData();
        Paciente p = pacienteServicio.consultarPorId(1L);
        assert p != null;
    }

    @Test
    public void testConsultarTodos() {
        List<Paciente> pacientes = pacienteServicio.listarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }


}
