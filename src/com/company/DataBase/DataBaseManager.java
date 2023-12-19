package com.company.DataBase;

import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    
    public ArrayList<String> getNameColumns(String nameTable) {
    	 try {
             // Consulta SQL para obtener todos los datos de la tabla (sin resultados)
             String query = "SELECT * FROM " + nameTable + " WHERE 1 = 0";

             // Crear una declaración
             Statement statement = connection.createStatement();

             // Ejecutar la consulta
             ResultSet resultSet = statement.executeQuery(query);

             // Obtener metadatos del resultado
             ResultSetMetaData metaData = resultSet.getMetaData();

             // Obtener el número de columnas
             int numColumns = metaData.getColumnCount();
             
             ArrayList<String> namesColumns = new ArrayList<String>();

             // Imprimir los nombres de las columnas
             for (int i = 1; i <= numColumns; i++) {
            	 namesColumns.add(metaData.getColumnName(i));
             }

             // Cerrar recursos
             resultSet.close();
             statement.close();
             
             return namesColumns;
         } catch (Exception e) {
             e.printStackTrace();
         }
		return null;
    }
    
    public String createQuery(String typeQuery, String data, String principalTable, String extraQuery) {
    	if(typeQuery == "SELECT") {
    		return createSelectQuery(typeQuery, data, principalTable, extraQuery);
    	} else if (typeQuery == "INSERT INTO") {
    		return createInsertQuery(typeQuery, data, principalTable, extraQuery);
    	} else if (typeQuery == "DELETE") {
    		return createSoftDeleteQuery("UPDATE", data, principalTable, extraQuery);
    	} else if (typeQuery == "UPDATE") {
    		return createUpdateQuery("UPDATE", data, principalTable, extraQuery);
    	} else {
    		return "";
    	}
    }
    
    private String createInsertQuery(String typeQuery, String data, String principalTable, String extraQuery) {
    		return typeQuery + " " + principalTable + " (" + extraQuery + ") " + "VALUES" + " (" + data + ")";
    }
    
    private String createSelectQuery(String typeQuery, String data, String principalTable, String extraQuery) {
    	if(extraQuery == null) {
    		return typeQuery + " " + data + " FROM " + principalTable;
    	} else {
    		return typeQuery + " " + data + " FROM " + principalTable + " " + extraQuery;
    	}
    }
    
    private String createSoftDeleteQuery(String typeQuery, 
    									String data, String principalTable,
    									String extraQuery) {
    	
    	//update tabla set nombreColumna = valor WHERE ID
    	return typeQuery + " " + principalTable + " SET" 
    			+ " Deleted_At = " + data + " WHERE ID = " 
    			+ extraQuery;
    }
    
    private String createUpdateQuery(String typeQuery, String columns,
    		String principalTable, String extraQuery) {
		//update tabla set nombreColumna = valor WHERE ID
		return typeQuery + " " + principalTable + " SET" 
		+ " " + columns + " WHERE ID = " 
		+ extraQuery;
    }
    
    public void updateAllInfoTable(String[] values, String createdQuery) {
        String query = createdQuery;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Actualización exitosa.");
            } else {
                System.out.println("No se encontraron filas para actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setAllInfoTable(String[] data, String createdQuery) {
    	String query = createdQuery;
    	
    	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Establecer los valores de los parámetros
    		for (int i = 0; i < data.length; i++) {
    			preparedStatement.setString(i+1, data[i]);
    		}

            // Ejecutar la consulta
            int filasAfectadas = preparedStatement.executeUpdate();
        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    public void deleteIndex(String createdQuery,int index) {
    	String query = createdQuery;
    	
    	PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			if (connection != null) {
	            try {
	            	Date currentDate = new Date();
	            	Timestamp timestamp = new Timestamp(currentDate.getTime());

	            	// Formatear la fecha y hora según tus necesidades
	            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	String formattedDateTime = dateFormat.format(currentDate);
	            	
			        // Establecer los valores de los parámetros
			        preparedStatement.setString(1, formattedDateTime); // Nuevo horario
			        preparedStatement.setInt(2, index); // ID del empleado que deseas actualizar
			
			        // Ejecutar la consulta
			        int filasAfectadas = preparedStatement.executeUpdate();
			
			        // Verificar si la actualización fue exitosa
			        if (filasAfectadas > 0) {
			            System.out.println("Actualización exitosa.");
			        } else {
			            System.out.println("No se encontró el empleado con el ID especificado.");
			        }
			
			        // Cerrar la conexión y otros recursos
			        preparedStatement.close();
		        
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
