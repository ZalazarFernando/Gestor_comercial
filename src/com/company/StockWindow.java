package com.company;

import java.awt.BorderLayout;
import java.awt.Component;
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
		setSize(700, 500);
		setTitle("Articles");
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
	private JPanel rightPanel;
	private JPanel leftPanel;
	
	public void createUpPanel() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		panel.add(rightPanel, BorderLayout.WEST);
	}
	
	public void createDownPanel() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		panel.add(leftPanel, BorderLayout.EAST);
	}
	
	//create panels within left panel
	private void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		JTextField brandTextBox = new JTextField();
		brandTextBox.setPreferredSize(new Dimension(150, 25));

		JTextField supplierTextBox = new JTextField();
		supplierTextBox.setPreferredSize(new Dimension(150, 25));
		
		panelTextBox.add(brandTextBox);
		panelTextBox.add(supplierTextBox);
		
		rightPanel.add(panelTextBox);
	}
	
	public void createTabPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel tabList1 = new JPanel();
		tabList1.add(setElementList());
		
		tabbedPane.addTab("Meat", tabList1);
		rightPanel.add(tabbedPane);
	}
	
	//create panel within right panel
	private void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		JTextField searchTextBox = new JTextField();
		searchTextBox.setPreferredSize(new Dimension(200, 25));
		
		panelSearch.add(searchTextBox);
		
		leftPanel.add(panelSearch);
	}
	
	public void createListBtn() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		JButton btn1 = new JButton("Add art.");
		JButton btn2 = new JButton("Modify art.");
		JButton btn3 = new JButton("Delete art.");
		
		btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btn3.setAlignmentX(Component.CENTER_ALIGNMENT);

	    btn1.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn1.getMinimumSize().width));
	    btn2.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn2.getMinimumSize().width));
	    btn3.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn3.getMinimumSize().width));
		
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		
		leftPanel.add(btnPanel);
	}
	
	//method extra
	private JScrollPane setElementList() {
		List<String> files = new ArrayList<>();
        files.add("test1");
        files.add("test2");
        files.add("test3");

        // Create a JList and add the elements directly from the list.
        JList<String> filesList = new JList<>(files.toArray(new String[0]));

        //Add the JList to a JScrollPane.
        JScrollPane scrollPane = new JScrollPane(filesList);
        
        scrollPane.setPreferredSize(new Dimension(450,400));
	        
	    return(scrollPane);
	}
}