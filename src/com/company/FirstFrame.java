package com.company;

import java.awt.BorderLayout;
import javax.swing.*;

import com.company.DataBase.DataBaseManager;

public class FirstFrame extends JFrame{
	private JPanel panel;
	DataBaseManager databaseManager;
    
	public FirstFrame( DataBaseManager databaseManager) {
		this.databaseManager = databaseManager;
		initializePanels();
	}
	
	public void initializePanels() {
		setSize(400,500);
		setTitle("Gestor");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanel();
		new SuperiorPanel(panel,databaseManager);
	}
	
	public void createPanel() {
		panel = new JPanel();
        panel.setLayout(new BorderLayout());
		
		getContentPane().add(panel);
	}
	
	
}