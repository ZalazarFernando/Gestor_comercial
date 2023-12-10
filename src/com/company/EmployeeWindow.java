package com.company;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.company.DataBase.DataBaseManager;
import com.company.WindowsActions.WindowsActionAdd;
import com.company.WindowsActions.WindowsActionArchetype;
import com.company.WindowsActions.WindowsActionDelete;
import com.company.WindowsActions.WindowsActionModify;

public class EmployeeWindow extends WindowArchetype{
	
	public EmployeeWindow(DataBaseManager databaseManager) {
		super(databaseManager);
		
		setSize(700, 500);
		setTitle("Employee");
		setResizable(false);
	}

	@Override
	protected void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		String[] namesText = {"Name"};
		for(int i=0; i<namesText.length ; i++) {
			panelSearch.add(createAutoTextBox(namesText[i]));
		}
		
		leftPanel.add(panelSearch);
	}

	@Override
	protected void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		String[] namesText = {"Workstation"};
		for(int i=0; i<namesText.length ; i++) {
			panelTextBox.add(createAutoTextBox(namesText[i]));
		}
		
		rightPanel.add(panelTextBox);
	}
	
	@Override
	protected void addActionBtn() {
		btns.get(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] words = {
						"Name",
						"Lastname",
						"DNI",
						"Department",
						"Phone",
						"Email"};
				
				WindowsActionAdd waa = new WindowsActionAdd(words);
				waa.setVisible(true);
			}
		});
		
		btns.get(1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionModify wam = new WindowsActionModify();
				wam.setVisible(true);
			}
		});
		btns.get(2).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionDelete wad = new WindowsActionDelete();
				wad.setVisible(true);
			}
		});
	}

	@Override
	protected JScrollPane setElementList() {
		JScrollPane scrollPane = null;
		
		//get data
		String[] array = {
				"ID",
				"Name_Employee",
				"Lastname_Employee",
				"Email_Address",
				"Number_Phone",
				"Salary",
				"Rol"
			 };
		ArrayList<ArrayList> infoAllEmployee = this.databaseManager.getAllInfoTable(array);
		 
        ArrayList<String> auxFilesList = new ArrayList<String>();
        
        for (ArrayList<String> listOfFields : infoAllEmployee) {
        	String auxText = "";
        	for (String field : listOfFields) {
        		auxText += " " + String.valueOf(field);
        	}
        	auxFilesList.add(auxText);
		}
        
        JList filesList = new JList<>(auxFilesList.toArray(new String[0]));

        //Add the JList to a JScrollPane.
        scrollPane = new JScrollPane(filesList);
        
        scrollPane.setPreferredSize(new Dimension(450,500));
        
		return scrollPane;
	}
	
	
}
