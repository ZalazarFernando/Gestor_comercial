package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SuperiorPanel extends JPanel{
	
	private JButton stockButton, employee, supplier, register;
	
	public SuperiorPanel(JPanel panel) {
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
				StockWindow sw = new StockWindow();
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
				EmployeeWindow ew = new EmployeeWindow();
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
			SupplierWindow sw = new SupplierWindow();
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
				RegisterWindow rw = new RegisterWindow();
				rw.setVisible(true);
			}
			
		});
	}
}