package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class StockWindow extends JFrame{
	JPanel panel;
	
	public StockWindow() {
		setSize(500, 300);
		setResizable(false);
		
		initialize();
	}
	
	public void initialize() {
		createPanel();
		createSpecificationTextBox();
		createSearchTextBox();
		createTabbedPane();
		createListBtn();
	}
	
	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		getContentPane().add(panel);
	}

	private void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new GridLayout(2,1));
		
		JTextField brandTextBox = new JTextField();
		JTextField SupplierTextBox = new JTextField();
		
		panelTextBox.add(brandTextBox);
		panelTextBox.add(SupplierTextBox);
		
		panel.add(panelTextBox);
	}
	
	private void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		JTextField searchTextBox = new JTextField(20);
		
		panelSearch.add(searchTextBox);
		
		panel.add(panelSearch);
	}
	
	public void createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel tab1 = new JPanel();
		tab1.add(setElementList());
		
		tabbedPane.addTab("List", tab1);
		
		panel.add(tabbedPane);
	}
	
	public void createListBtn() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(5,1));
		
		JButton btn1 = new JButton("test1");
		JButton btn2 = new JButton("test1");
		JButton btn3 = new JButton("test1");
		JButton btn4 = new JButton("test1");
		JButton btn5 = new JButton("test1");
		
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		btnPanel.add(btn4);
		btnPanel.add(btn5);
		
		panel.add(btnPanel);
	}
	
	private JScrollPane setElementList() {
		 List<String> files = new ArrayList<>();
	        files.add("test1");
	        files.add("test2");
	        files.add("test3");

	        // Crear una JList y agregar los elementos directamente desde la lista
	        JList<String> filesList = new JList<>(files.toArray(new String[0]));

	        // Agregar la JList a un JScrollPane
	        JScrollPane scrollPane = new JScrollPane(filesList);
	        
	     return(scrollPane);
	}
}