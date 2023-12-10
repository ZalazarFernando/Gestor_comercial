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
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    public String createQuery(String typeQuery, String data, String principalTable, String innerJoinTables) {
    	if(innerJoinTables == null) {
    		return typeQuery + " " + data + " FROM " + principalTable;
    	} else {
    		return typeQuery + " " + data + " FROM " + principalTable + " " + innerJoinTables;
    	}
    }
    
    public ArrayList<ArrayList> getAllInfoTable(String[] data, String createdQuery) {
    	String query = createdQuery;
    	
    	ArrayList<ArrayList> infoAll = new ArrayList<ArrayList>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	
            while (resultSet.next()) {
            	String[] getInfo = new String[data.length];
                // Obtén los datos de cada fila
            	for (int i = 0; i < getInfo.length; i++) {
            		getInfo[i] = resultSet.getString(data[i]);
            	}
            	
                /*String id = resultSet.getString("ID");
                String name = resultSet.getString("Name_Employee");
                String lastName = resultSet.getString("Lastname_Employee");
                String emailAddress = resultSet.getString("Email_Address");
                String numberPhone = resultSet.getString("Number_Phone");
                String salary = resultSet.getString("Salary");
                String rol = resultSet.getString("Rol");*/
            	
            	ArrayList<String> info = new ArrayList<String>();
            	for (int i = 0; i < getInfo.length; i++) {
            		info.add(getInfo[i]);
            	}
            	
                /*ArrayList<String> infoEmployee = new ArrayList<String>();
                infoEmployee.add(String.valueOf(id));
                infoEmployee.add(name);
                infoEmployee.add(lastName);
                infoEmployee.add(emailAddress);
                infoEmployee.add(numberPhone);
                infoEmployee.add(salary);
                infoEmployee.add(rol);*/
                
            	infoAll.add(info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return infoAll;
    }
}
