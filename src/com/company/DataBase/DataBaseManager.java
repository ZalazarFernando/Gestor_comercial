package com.company.DataBase;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
	private static final String URL = "jdbc:mysql://localhost:3306/gestorcomercial";
    private static final String USER = "root";
    private static final String PASSWORD = "8deDiciembre2023";

    private Connection connection;

    public DataBaseManager() {
    	try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puedes manejar la excepción de cierre de conexión aquí según tus necesidades
        }
    }
    
    public ArrayList<ArrayList> getAllInfoTable() {
    	String query = "SELECT * FROM employee";
    	
    	ArrayList<ArrayList> infoAllEmployee = new ArrayList<ArrayList>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	
            while (resultSet.next()) {
            	
                // Obtén los datos de cada fila
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name_Employee");
                String lastName = resultSet.getString("Lastname_Employee");
                String emailAddress = resultSet.getString("Email_Address");
                String numberPhone = resultSet.getString("Number_Phone");
                String salary = resultSet.getString("Salary");
                String rol = resultSet.getString("Rol");

                ArrayList<String> infoEmployee = new ArrayList<String>();
                infoEmployee.add(String.valueOf(id));
                infoEmployee.add(name);
                infoEmployee.add(lastName);
                infoEmployee.add(emailAddress);
                infoEmployee.add(numberPhone);
                infoEmployee.add(salary);
                infoEmployee.add(rol);
                
                infoAllEmployee.add(infoEmployee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return infoAllEmployee;
    }
}
