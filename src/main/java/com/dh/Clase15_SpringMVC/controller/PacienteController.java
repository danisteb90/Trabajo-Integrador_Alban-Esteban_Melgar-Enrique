package com.dh.Clase15_SpringMVC.controller;


import com.dh.Clase15_SpringMVC.modelo.Odontologo;
import com.dh.Clase15_SpringMVC.modelo.Paciente;
import com.dh.Clase15_SpringMVC.servicio.IOdontologoServicio;
import com.dh.Clase15_SpringMVC.servicio.IPacienteServicio;
import com.dh.Clase15_SpringMVC.servicio.impl.OdontologoServicioImpl;
import com.dh.Clase15_SpringMVC.servicio.impl.PacienteServicioImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Controller -> THYMELEAF
//RestController -> API REST
@Controller
@RequestMapping("/paciente")
public class PacienteController {
    private IPacienteServicio pacienteServicio;

    public PacienteController() {
        this.pacienteServicio = new PacienteServicioImpl();
    }

    @GetMapping("/id")
    public String consultarPacientePorId(Model model,
                                        @RequestParam Integer id) {
        Paciente paciente = pacienteServicio.consultarPorId(id);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        model.addAttribute("fechaAlta", paciente.getFechaAlta());
        return "consultarPaciente";
    }



}
