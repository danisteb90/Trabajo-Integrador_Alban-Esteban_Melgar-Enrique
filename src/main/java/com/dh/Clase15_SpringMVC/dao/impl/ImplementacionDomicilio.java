package com.dh.Clase15_SpringMVC.dao.impl;

import com.dh.Clase15_SpringMVC.dao.BD;
import com.dh.Clase15_SpringMVC.dao.IDAO;
import com.dh.Clase15_SpringMVC.modelo.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionDomicilio implements IDAO<Domicilio> {

    private static final Logger LOGGER = Logger.getLogger(ImplementacionPaciente.class);

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;

        try {
            LOGGER.info("Estamos guardando un odontologo");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO DOMICILIOS (" +
                            "CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES " +
                            "(?,?,?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            preparedStatement.execute();

            // guardé el paciente y se generó el id

            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                domicilio.setId(rs.getInt(1));
                System.out.println("Se guardó el domicilio con calle " +
                        domicilio.getCalle());
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
        LOGGER.info("Guardamos el domicilio con calle " + domicilio.getCalle());
        return domicilio;
    }

    @Override
    public List<Domicilio> listarTodos() {
        List<Domicilio> domicilios = new ArrayList<>();

        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();

            PreparedStatement buscarTodos = connection.prepareStatement(
                    "SELECT * FROM DOMICILIOS");
            ResultSet rs = buscarTodos.executeQuery();

            while (rs.next()) {
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5));
                domicilios.add(domicilio);
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

        return domicilios;
    }

    @Override
    public Domicilio consultarPorId(Integer id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psBuscarPorId = connection.prepareStatement(
                    "SELECT * FROM DOMICILIOS WHERE ID=?");
            psBuscarPorId.setInt(1, id);
            ResultSet rs = psBuscarPorId.executeQuery();

            while (rs.next()) {
                domicilio = new Domicilio();
                domicilio.setId(rs.getInt(1));
                domicilio.setCalle(rs.getString(2));
                domicilio.setNumero(rs.getInt(3));
                domicilio.setLocalidad(rs.getString(4));
                domicilio.setProvincia(rs.getString(5));
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

        return domicilio;
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        return false;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;

        try {
            LOGGER.info("Estamos actualizando un domicilio");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?"
            );

            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setInt(5, domicilio.getId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                LOGGER.info("Se actualizó el domicilio con ID " + domicilio.getId());
            } else {
                LOGGER.warn("No se encontró el domicilio con ID " + domicilio.getId());
                return null;
            }

        } catch (Exception e) {
            LOGGER.error("Error al actualizar el domicilio", e);
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar la conexión", ex);
                ex.printStackTrace();
            }
        }

        return domicilio;
    }
}