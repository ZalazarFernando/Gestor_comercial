package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.company.DataBase.DataBaseManager;

public class SuperiorPanel extends JPanel{
	
	private JButton stockButton, employee, supplier, register;
	private DataBaseManager databaseManager;
	
	public SuperiorPanel(JPanel panel, DataBaseManager databaseManager) {
		this.databaseManager = databaseManager;
		
		setLayout(new FlowLayout());
		
		panel.add(this, BorderLayout.NORTH);
		
		initialize();
	}
	
	public void initialize() {
		createBtnStock();
		createBtnEmployee();
		createBtnSupplie();
		createBtnRegister();
	}
	
	public void createBtnStock() {
		stockButton = new JButton("Stock");
        
		add(stockButton);
		
		stockButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StockWindow sw = new StockWindow(databaseManager);
				sw.setVisible(true);
			}
		});
	}
	
	public void createBtnEmployee() {
		employee = new JButton("Employee");
        
		add(employee);
		
		employee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeWindow ew = new EmployeeWindow(databaseManager);
				ew.setVisible(true);
			}
			
		});
	}
	
	public void createBtnSupplie() {
		supplier = new JButton("Supplier");
		
		add(supplier);
		
		supplier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			SupplierWindow sw = new SupplierWindow(databaseManager);
			sw.setVisible(true);
			}
			
		});
	}
	
	public void createBtnRegister() {
		register = new JButton("Register");
		
		add(register);
		
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterWindow rw = new RegisterWindow(databaseManager);
				rw.setVisible(true);
			}
			
		});
	}
}