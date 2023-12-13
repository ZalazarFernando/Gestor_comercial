package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.company.DataBase.DataBaseManager;

public class WindowsActionAdd extends WindowsActionArchetype{
	protected List<String> wordOfAssistance = new ArrayList<String>();
	protected String[] words;
	protected String nameColumns; 
	
	
	public WindowsActionAdd(DataBaseManager databaseManager) {
		super(databaseManager, "");
	}
	
	public WindowsActionAdd(String[] words, 
			DataBaseManager databaseManager, String table, String nameColumns) {
		super(databaseManager, table);
		
		this.nameColumns = nameColumns;
		
		setTitle("Add article");
		Initialize(words);
	}
	
	protected void Initialize(String[] words) {
		super.Initialize();
		
		this.words = words;
		
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
				int count = 0;
				
				for (int i = 0; i < words.length; i++) {
					if (!addTextBox.get(words[i]).getText().equals(words[i])) {
						count++;
					}
				}
				
				if(count == words.length) {
					
						ArrayList<String> auxData = new ArrayList<String>();
						String aux = "";
						
						for (int i = 0; i < words.length; i++) {
							auxData.add(addTextBox.get(words[i]).getText());
							aux += "?";
							if(i < words.length - 1 && 
				        		words[i+1] != null) {
								aux += ", ";
							}
						}
						
						databaseManager.setAllInfoTable(
								auxData.toArray(new String[0]),
								databaseManager.createQuery(
										"INSERT INTO",
										aux,
										preTable,
										nameColumns)
								);
					}
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        currentFrame.dispose();
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
