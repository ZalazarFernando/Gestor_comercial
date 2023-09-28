package com.company;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class StockWindow extends JFrame{
	private JPanel panel;
	private JTabbedPane tabbedPane;
	
	public StockWindow() {
		setSize(500, 300);
		setResizable(false);
		
		initialize();
	}
	
	public void initialize() {
		createPanel();
		
		createSpecificationTextBox();
		createSearchTextBox();
		createTabPanel();
		createListBtn();
	}
	
	//main panel
	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		getContentPane().add(panel);
		
		createUpPanel();
		createDownPanel();
	}
	
	//two panels to main panel
	private JPanel upPanel;
	private JPanel downPanel;
	
	public void createUpPanel() {
		upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout());
		
		panel.add(upPanel, BorderLayout.NORTH);
	}
	
	public void createDownPanel() {
		downPanel = new JPanel();
		downPanel.setLayout(new FlowLayout());
		
		panel.add(downPanel, BorderLayout.SOUTH);
	}
	
	//create panels within up panel
	private void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		JTextField brandTextBox = new JTextField();
		brandTextBox.setPreferredSize(new Dimension(150, 30));

		JTextField supplierTextBox = new JTextField();
		supplierTextBox.setPreferredSize(new Dimension(150, 30));
		
		panelTextBox.add(brandTextBox);
		panelTextBox.add(supplierTextBox);
		
		upPanel.add(panelTextBox);
	}
	
	private void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		JTextField searchTextBox = new JTextField();
		searchTextBox.setPreferredSize(new Dimension(150, 30));
		
		panelSearch.add(searchTextBox);
		
		upPanel.add(panelSearch);
	}
	
	//create panel within down panel
	public void createTabPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel tabList1 = new JPanel();
		tabList1.add(setElementList());
		tabList1.setPreferredSize(new Dimension(150,150));
		
		tabbedPane.addTab("Meat", tabList1);
		downPanel.add(tabbedPane);
	}
	
	public void createListBtn() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		JButton btn1 = new JButton("Add art.");
		JButton btn2 = new JButton("Modify art.");
		JButton btn3 = new JButton("Delete art.");
		
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		
		downPanel.add(btnPanel);
	}
	
	private JScrollPane setElementList() {
		List<String> files = new ArrayList<>();
        files.add("test1");
        files.add("test2");
        files.add("test3");

        // Create a JList and add the elements directly from the list.
        JList<String> filesList = new JList<>(files.toArray(new String[0]));

        //Add the JList to a JScrollPane.
        JScrollPane scrollPane = new JScrollPane(filesList);
        
        scrollPane.setPreferredSize(new Dimension(150,150));
	        
	    return(scrollPane);
	}
}