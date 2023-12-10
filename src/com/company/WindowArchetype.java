package com.company;

import com.company.DataBase.DataBaseManager;
import com.company.WindowsActions.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class WindowArchetype extends JFrame{
	//protected JTabbedPane tabbedPane;
	protected JPanel mainPanel;
	protected ArrayList<JButton> btns;
	protected Map<String, JTextField> listTextBox = new HashMap<>();
	protected ArrayList<String> files;
	protected DataBaseManager databaseManager;
		
		public WindowArchetype(DataBaseManager databaseManager) {
			this.databaseManager = databaseManager;
			
			setSize(700, 500);
			setTitle("");
			setResizable(false);
			
			initialize();
		}
		
		protected void initialize() {
			createPanel();
			
			createSpecificationTextBox();
			createSearchTextBox();
			createTabPanel();
			createListBtn();
		}
		
		//main panel
		protected void createPanel() {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			
			getContentPane().add(mainPanel);
			
			createUpPanel();
			createDownPanel();
		}
		
		//two panels to main panel
		protected JPanel rightPanel;
		protected JPanel leftPanel;
		
		protected void createUpPanel() {
			rightPanel = new JPanel();
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
			
			mainPanel.add(rightPanel, BorderLayout.WEST);
		}
		
		protected void createDownPanel() {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
			
			mainPanel.add(leftPanel, BorderLayout.EAST);
		}
		
		//create panels within left panel
		protected void createSpecificationTextBox() {
			JPanel panelTextBox = new JPanel();
			panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
			
			String[] namesText = {""};
			for(int i=0; i<namesText.length ; i++) {
				panelTextBox.add(createAutoTextBox(namesText[i]));
			}
			
			rightPanel.add(panelTextBox);
		}
		
		protected void createTabPanel() {
			JPanel articleList = new JPanel();
			articleList.add(setElementList());
			
			rightPanel.add(articleList);
		}
		
		//create panel within right panel
		protected void createSearchTextBox() {
			JPanel panelSearch = new JPanel();
			
			String[] namesText = {""};
			for(int i=0; i<namesText.length ; i++) {
				panelSearch.add(createAutoTextBox(namesText[i]));
			}
			
			leftPanel.add(panelSearch);
		}
		
		protected void createListBtn() {
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
			
			btns = new ArrayList<JButton>();
			
			btns.add(new JButton("Add"));
			btns.add(new JButton("Modify"));
			btns.add(new JButton("Delete"));
			
			addActionBtn();
			
			for(int i=0; i<btns.size(); i++) {
				addPreferencesBtn(btns.get(i));
			}
			
			for(int i=0; i<btns.size(); i++) {
				btnPanel.add(btns.get(i));
			}
			
			leftPanel.add(btnPanel);
		}
		
		//method extra
		protected void addActionBtn() {
			
		}
		
		protected void addPreferencesBtn(JButton btn) {
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getMinimumSize().width));
		}
		
		protected JScrollPane setElementList() {
			files = new ArrayList<>();
	        files.add("test1");
	        files.add("test2");
	        files.add("test3");
	
	        // Create a JList and add the elements directly from the list.
	        JList<String> filesList = new JList<>(files.toArray(new String[0]));
	
	        //Add the JList to a JScrollPane.
	        JScrollPane scrollPane = new JScrollPane(filesList);
	        
	        scrollPane.setPreferredSize(new Dimension(450,500));
		        
		    return(scrollPane);
		}
		
		protected JTextField createAutoTextBox(String name) {
			JTextField textBox = new JTextField(name);
			textBox.setPreferredSize(new Dimension(150, 25));
			textBox.setForeground(Color.GRAY);
			
			textBox.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (textBox.getText().equals(name)) {
	                    textBox.setText("");
	                    textBox.setForeground(Color.BLACK);
	                }
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (textBox.getText().isEmpty()) {
	                    textBox.setText(name);
	                    textBox.setForeground(Color.GRAY);
	                }
				}
	        });
			
			listTextBox.put(name, textBox);
			
			return(textBox);
		}
}
