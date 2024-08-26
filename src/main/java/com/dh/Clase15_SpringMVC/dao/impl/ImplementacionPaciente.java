package com.dh.Clase15_SpringMVC.dao.impl;

import com.dh.Clase15_SpringMVC.dao.BD;
import com.dh.Clase15_SpringMVC.dao.IDAO;
import com.dh.Clase15_SpringMVC.modelo.Domicilio;
import com.dh.Clase15_SpringMVC.modelo.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionPaciente implements IDAO<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(ImplementacionPaciente.class);

    ImplementacionDomicilio implementacionDomicilio = new ImplementacionDomicilio();

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            ImplementacionDomicilio implementacionDomicilio = new ImplementacionDomicilio();
            implementacionDomicilio.guardar(paciente.getDomicilio());
            LOGGER.info("Estamos guardando un paciente");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PACIENTES (" +
                            "NOMBRE, APELLIDO, DNI, FECHA_ALTA, DOMICILIO_ID) VALUES " +
                            "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaAlta()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());

            preparedStatement.execute();

            //guardé el paciente y se generó el id

            ResultSet rs = preparedStatement.getGeneratedKeys();


            while (rs.next()) {
                paciente.setId(rs.getInt(1));
                System.out.println("Se guardó el paciente con nombre " +
                        paciente.getNombre());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info("Guardamos el paciente con nombre " + paciente.getNombre());
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = null;

        List<Paciente> pacienteList = new ArrayList<>();
        Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM PACIENTES"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Domicilio domicilio = implementacionDomicilio.consultarPorId(rs.getInt(6));
                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(), domicilio);

                pacienteList.add(paciente);

                System.out.println(paciente.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return pacienteList;
    }

    @Override
    public Paciente consultarPorId(Integer id) {
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psConsultarPorId = connection.prepareStatement(
                    "SELECT * FROM PACIENTES WHERE ID=?"
            );
            psConsultarPorId.setInt(1, id);
            ResultSet rs = psConsultarPorId.executeQuery();

            while (rs.next()) {
                Domicilio domicilio = implementacionDomicilio.consultarPorId(rs.getInt(6));

                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(), domicilio);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        Connection connection = null;
        boolean eliminado = false;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM PACIENTES WHERE ID = ?"
            );

            preparedStatement.setLong(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            eliminado = filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return eliminado;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;

        try {
            LOGGER.info("Estamos actualizando un paciente");

            Paciente pacienteExistente = consultarPorId(paciente.getId());
            if (pacienteExistente == null) {
                LOGGER.warn("No se encontró el paciente con ID " + paciente.getId());
                return null;
            }

            if (paciente.getNombre() != null) pacienteExistente.setNombre(paciente.getNombre());
            if (paciente.getApellido() != null) pacienteExistente.setApellido(paciente.getApellido());
            if (paciente.getDni() != null) pacienteExistente.setDni(paciente.getDni());
            if (paciente.getFechaAlta() != null) pacienteExistente.setFechaAlta(paciente.getFechaAlta());

            if (paciente.getDomicilio() != null) {
                ImplementacionDomicilio implementacionDomicilio = new ImplementacionDomicilio();
                Domicilio domicilioActualizado;
                if (paciente.getDomicilio().getId() != null) {
                    domicilioActualizado = implementacionDomicilio.actualizar(paciente.getDomicilio());
                } else {
                    domicilioActualizado = implementacionDomicilio.guardar(paciente.getDomicilio());
                }
                if (domicilioActualizado != null) {
                    pacienteExistente.setDomicilio(domicilioActualizado);
                } else {
                    LOGGER.warn("No se pudo actualizar o guardar el domicilio del paciente");
                    return null;
                }
            }

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_ALTA=?, DOMICILIO_ID=? WHERE ID=?"
            );

            preparedStatement.setString(1, pacienteExistente.getNombre());
            preparedStatement.setString(2, pacienteExistente.getApellido());
            preparedStatement.setString(3, pacienteExistente.getDni());
            preparedStatement.setDate(4, Date.valueOf(pacienteExistente.getFechaAlta()));
            preparedStatement.setInt(5, pacienteExistente.getDomicilio().getId());
            preparedStatement.setInt(6, pacienteExistente.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                LOGGER.info("Se actualizó el paciente con nombre " + pacienteExistente.getNombre());
            } else {
                LOGGER.warn("No se pudo actualizar el paciente con ID " + pacienteExistente.getId());
                return null;
            }

            return pacienteExistente;

        } catch (Exception e) {
            LOGGER.error("Error al actualizar el paciente", e);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar la conexión", ex);
                ex.printStackTrace();
            }
        }

        return paciente;
    }
}