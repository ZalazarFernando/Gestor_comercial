package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.company.DataBase.DataBaseManager;

public class WindowsActionAdd extends WindowsActionArchetype{
	protected List<String> wordOfAssistance = new ArrayList<String>();
	
	
	
	public WindowsActionAdd(DataBaseManager databaseManager) {
		super(databaseManager, "");
	}
	
	public WindowsActionAdd(String[] words, DataBaseManager databaseManager, String table) {
		super(databaseManager, table);
		
		
		setTitle("Add article");
		Initialize(words);
	}
	
	protected void Initialize(String[] words) {
		super.Initialize();
		
		this.setWordOfAssistance(words);
		createLeftPanel();
		createRightPanel();
		
		createLeftTextBox();
		createRightTextBox();
	}
	
	//two panels within to up panel
	protected JPanel leftPanel;
	protected JPanel rightPanel;
	
	protected void createLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		upPanel.add(leftPanel, BorderLayout.WEST);
	}
	
	protected void createRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		upPanel.add(rightPanel, BorderLayout.EAST);
	}
	
	//Create textbox
	protected void createLeftTextBox() {
		String[] words;
		if(wordOfAssistance.size()>=4) {
			words = new String[4];
		}else {
			words = new String[wordOfAssistance.size()];
		}
		
		for(int i=0; i<words.length; i++) {
			words[i] = wordOfAssistance.get(i);
		}
		
		for(int i=0; i<words.length; i++) {
			wordOfAssistance.remove(words[i]);
		}
		
		for(int i=0; i<words.length ; i++) {
			leftPanel.add(createAutoTextBox(words[i]));
		}
	}
	
	protected void createRightTextBox() {
		if(wordOfAssistance.size()>0) {
			String[] words = new String[wordOfAssistance.size()];
			
			for(int i=0; i<words.length; i++) {
				words[i] = wordOfAssistance.get(i);
			}
			
			for(int i=0; i<words.length; i++) {
				wordOfAssistance.remove(words[i]);
			}
			
			for(int i=0; i<words.length ; i++) {
				rightPanel.add(createAutoTextBox(words[i]));
			}
		}
	}
	
	@Override
	protected void addActionDoneBtn(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!addTextBox.get("Name").getText().equals("Name") &&
						!addTextBox.get("Lastname").getText().equals("lastname") &&
						!addTextBox.get("Rol").getText().equals("Rol") &&
						!addTextBox.get("Phone").getText().equals("Phone") &&
						!addTextBox.get("Email").getText().equals("Email") &&
						!addTextBox.get("Salary").getText().equals("Salary")) {
					
						ArrayList<String> auxData = new ArrayList<String>();
						
						auxData.add(addTextBox.get("Name").getText());
						auxData.add(addTextBox.get("Lastname").getText());
						auxData.add(addTextBox.get("Email").getText());
						auxData.add(addTextBox.get("Phone").getText());
						auxData.add(addTextBox.get("Salary").getText());
						auxData.add(addTextBox.get("Rol").getText());
						
						databaseManager.setAllInfoTable(
								auxData.toArray(new String[0]),
								databaseManager.createQuery(
										"INSERT INTO",
										"?, ?, ?, ?, ?, ?",
										preTable,
										"Name_Employee, Lastname_Employee,"
										+ "Email_Address, Number_Phone, Salary, Rol")
								);
					}
			}
			
		});
	}

	//getters and setters
	public List getWordOfAssistance() {
		return wordOfAssistance;
	}

	public void setWordOfAssistance(String[] words) {
	    for(int i=0; i<words.length ; i++) {
	    	this.wordOfAssistance.add(words[i]);
	    }
	}
}
