package com.dh.Clase15_SpringMVC.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    private static final Logger LOGGER = Logger.getLogger(BD.class);

//    private static final String DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS DOMICILIOS; CREATE TABLE " +
//            "DOMICILIOS (" +
//            "ID INT AUTO_INCREMENT PRIMARY KEY," +
//            "CALLE VARCHAR(100) NOT NULL," +
//            "NUMERO VARCHAR(100) NOT NULL," +
//            "LOCALIDAD VARCHAR(100) NOT NULL," +
//            "PROVINCIA VARCHAR(100) NOT NULL)";

//    private static final String DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS PACIENTES; CREATE TABLE " +
//            "PACIENTES (" +
//            "ID INT AUTO_INCREMENT PRIMARY KEY," +
//            "NOMBRE VARCHAR(100) NOT NULL," +
//            "APELLIDO VARCHAR(100) NOT NULL," +
//            "DNI VARCHAR(100) NOT NULL," +
//            "FECHA_ALTA DATE NOT NULL," +
//            "DOMICILIO_ID INT NOT NULL)";

//    private static final String SQL_INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) " +
//            "VALUES ('Calle Azul', '12', 'Bogota', 'Bogota')";

//    private static final String SQL_INSERT_PACIENTE = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_ALTA, DOMICILIO_ID) " +
//            "VALUES ('Carlos', 'Andrade', '789', '2024-08-30', 1)";

    public static void createTable() {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

//            LOGGER.info("Estamos creando la tabla odontologos");
//            statement.execute(DROP_CREATE_ODONTOLOGOS);
//            LOGGER.info("Estamos creando la tabla domicilios");
//            statement.execute(DROP_CREATE_DOMICILIOS);
//            LOGGER.info("Estamos creando la tabla pacientes");
//            statement.execute(DROP_CREATE_PACIENTES);


//            LOGGER.info("Estamos insertando un paciente en la tabla domicilios");
//            statement.execute(SQL_INSERT_DOMICILIO);
//            LOGGER.info("Estamos insertando un paciente en la tabla pacientes");
//            statement.execute(SQL_INSERT_PACIENTE);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./202407", "sa", "sa");
    }
}
