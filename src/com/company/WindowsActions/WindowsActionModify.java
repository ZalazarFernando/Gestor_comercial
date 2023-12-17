package com.company.WindowsActions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
		
		ArrayList<String> auxNames = databaseManager.getAllInfoTable(
				auxColumns, 
				databaseManager.createQuery(
						"SELECT", 
						nameColumns, 
						preTable, 
						"WHERE ID = " + index)
			).get(0);
		
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
			String[] aux = new String[wordOfAssistance.size()];
			
			for(int i=0; i<aux.length; i++) {
				aux[i] = wordOfAssistance.get(i);
			}
			
			for(int i=0; i<aux.length; i++) {
				wordOfAssistance.remove(aux[i]);
			}
			
			for(int i=0; i<aux.length ; i++) {
				rightPanel.add(createAutoTextBox(namesForTextBox[i+4]));
				words[i+4] = namesForTextBox[i+4];
			}
		}
	}

	@Override
	protected void createLeftTextBox() {
		String[] aux;
		if(wordOfAssistance.size()>=4) {
			aux = new String[4];
		}else {
			aux = new String[wordOfAssistance.size()];
		}
		
		for(int i=0; i<aux.length; i++) {
			aux[i] = wordOfAssistance.get(i);
		}
		
		for(int i=0; i<aux.length; i++) {
			wordOfAssistance.remove(aux[i]);
		}
		
		for(int i=0; i<aux.length ; i++) {
			leftPanel.add(createAutoTextBox(namesForTextBox[i]));
			words[i] = namesForTextBox[i];
		}
	}

	@Override
	protected JTextField createAutoTextBox(String name) {
		JTextField textBox = new JTextField(name);
		textBox.setPreferredSize(new Dimension(150, 25));
		textBox.setForeground(Color.BLACK);
		
		addTextBox.put(name, textBox);
		
		return(textBox);
	}

	@Override
	protected void addActionDoneBtn(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> auxData = new ArrayList<String>();
				
				for (int i = 0; i < words.length; i++) {
					auxData.add(addTextBox.get(words[i]).getText());
				}
				
				String[] aux = nameColumns.split(",");
				
				for (int i = 0; i < aux.length; i++) {
					if (!aux[i].equals(" Deleted_At")) {
						aux[i] += " = ?";
					}
				}
				for (int i = 0; i < aux.length; i++) {
					if (aux[i+1].contains("= ?")) {
						aux[i] += ",";
					} else {
						break;
					}
				}
				nameColumns = "";
				for (int i = 0; i < aux.length; i++) {
					if (!aux[i].equals(" Deleted_At")) {
						nameColumns += aux[i];
					}
				}
				
				databaseManager.updateAllInfoTable(
						auxData.toArray(new String[0]),
						databaseManager.createQuery(
								"UPDATE",
								nameColumns,
								preTable,
								index)
						);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        currentFrame.dispose();
			}
			
		});
	}
	
	
}
