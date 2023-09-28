package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class WindowArchetype extends JFrame{
	//protected JTabbedPane tabbedPane;
	protected JPanel mainPanel;
		
		public WindowArchetype() {
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
			
			String[] namesText = {"Brand","Supplier","Article type"};
			for(int i=0; i<namesText.length ; i++) {
				panelTextBox.add(createSpecification(namesText[i]));
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
			
			JTextField searchTextBox = new JTextField();
			searchTextBox.setPreferredSize(new Dimension(200, 25));
			
			panelSearch.add(searchTextBox);
			
			leftPanel.add(panelSearch);
		}
		
		protected void createListBtn() {
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
		protected JScrollPane setElementList() {
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
		
		protected JTextField createSpecification(String name) {
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
	                    textBox.setForeground(Color.GRAY); // Restaura el color del texto de marcador
	                }
				}
	        });
			
			return(textBox);
		}
}
