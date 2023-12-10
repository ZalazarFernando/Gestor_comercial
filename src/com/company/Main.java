package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.company.DataBase.DataBaseManager;


public class Main {
    public static void main(String[] args) {
    	DataBaseManager databaseManager = new DataBaseManager();
    	
        FirstFrame sp = new FirstFrame(databaseManager);
        sp.setVisible(true);
    }
}
