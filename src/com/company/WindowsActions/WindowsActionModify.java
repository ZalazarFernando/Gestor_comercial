package com.company.WindowsActions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.company.DataBase.DataBaseManager;

public class WindowsActionModify extends WindowsActionAdd {
	
	String index;
	String[] namesForTextBox;
	
	public WindowsActionModify(DataBaseManager databaseManager) {
		super(databaseManager);
		setTitle("Modify article");
		
		super.Initialize();
	}

	public WindowsActionModify(String[] words, 
					DataBaseManager databaseManager, String table, 
					String nameColumns, String index) {
		
		super(words, databaseManager, table, nameColumns, index);
		
		setTitle("Modify article");
	}
	
	public WindowsActionModify(String[] words, String[] secondWords,
			DataBaseManager databaseManager, String table, 
				String nameColumns,  String secondTable,
					String secondColumn) {
		super(words, secondWords, databaseManager, table, nameColumns,
				secondTable, secondColumn);
		
		setTitle("Modify article");
		
	}
	
	@Override
	protected void Initialize(String[] words, String index) {
		this.index = index;
		getInfoToTextBox();
		
		super.Initialize(words, index);
	}

	protected void getInfoToTextBox() {
		String[] auxColumns = nameColumns.split(",");
		
		for ( int i = 0; i < auxColumns.length; i++ ) {
			String auxC = auxColumns[i].replace(" ", "");
			auxColumns[i] = auxC;
		}
		
		System.out.println(databaseManager.getAllInfoTable(
				auxColumns, 
				databaseManager.createQuery(
						"SELECT", 
						nameColumns, 
						preTable, 
						"WHERE ID = " + index)
			));
		
		ArrayList<String> auxNames = databaseManager.getAllInfoTable(
				auxColumns, 
				databaseManager.createQuery(
						"SELECT", 
						nameColumns, 
						preTable, 
						"WHERE ID = " + index)
			).get(0);
		
		System.out.println(auxNames);
		
		for ( int i = 0; i < auxNames.size(); i++ ) {
		    if (auxNames.get(i) == null) {
		    	auxNames.set(i, "");
		    }
		}
		
		this.namesForTextBox = auxNames.toArray(new String[0]);
	}

	

	@Override
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
				rightPanel.add(createAutoTextBox(namesForTextBox[i]));
			}
		}
	}

	@Override
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
			leftPanel.add(createAutoTextBox(namesForTextBox[i]));
		}
	}

	@Override
	protected JTextField createAutoTextBox(String name) {
		JTextField textBox = new JTextField(name);
		textBox.setPreferredSize(new Dimension(150, 25));
		textBox.setForeground(Color.GRAY);
		
		addTextBox.put(name, textBox);
		
		return(textBox);
	}
	
	
}
