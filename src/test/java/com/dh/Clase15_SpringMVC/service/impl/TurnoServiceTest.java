package com.dh.Clase15_SpringMVC.service.impl;

import com.dh.Clase15_SpringMVC.entity.Domicilio;
import com.dh.Clase15_SpringMVC.entity.Odontologo;
import com.dh.Clase15_SpringMVC.entity.Paciente;
import com.dh.Clase15_SpringMVC.entity.Turno;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private TurnoServicioImpl turnoService;
    @Autowired
    private PacienteServicioImpl pacienteService;
    @Autowired
    private OdontologoServicioImpl odontologoService;

    public void cargarDatos() {
        Domicilio domicilio = new Domicilio("Calle privada", 123, "Buenos Aires", "Argentina");

        Paciente paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Perez");
        paciente.setDni("12345678");
        paciente.setDomicilio(domicilio);
        paciente.setFechaAlta(LocalDate.now());
        pacienteService.guardar(paciente);

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Perez");
        odontologo.setMatricula("1234");
        odontologoService.guardar(odontologo);
    }

    @Test
    public void testGuardarTurno() {
        cargarDatos();
        Turno turno = new Turno();
        turno.setFecha(LocalDate.now());
        turno.setPaciente(pacienteService.consultarPorId(1L));
        turno.setOdontologo(odontologoService.buscarPorId(1L));
        turnoService.guardar(turno);

        assert turno.getId() != null;
    }

    @Test
    public void testBuscarTurnoPorId() {
        cargarDatos();
        Turno turno = new Turno();
        turno.setFecha(LocalDate.now());
        turno.setPaciente(pacienteService.consultarPorId(1L));
        turno.setOdontologo(odontologoService.buscarPorId(1L));
        turnoService.guardar(turno);

        Turno turnoBuscado = turnoService.buscarPorId(1L);

        assert turnoBuscado != null;
    }

}
