package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.company.DataBase.DataBaseManager;

public class WindowsActionArchetype extends JFrame{
	protected JPanel mainPanel;
	protected Map<String, JTextField> addTextBox = new HashMap<>();
	
	protected DataBaseManager databaseManager;
	protected String preTable;
	
	public WindowsActionArchetype(DataBaseManager databaseManager, String table) {
		this.databaseManager = databaseManager;
		
		preTable = table;
		setResizable(false);
		Initialize();
	}

	protected void Initialize() {
		setSize(400,200);
		setTitle("");
		createPanel();
		createDoneBtn();
		createCancelBtn();
	}
	
	//main panel
	protected void createPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		getContentPane().add(mainPanel);
		
		createUpPanel();
		createDownPanel();
	}
	
	//two panels within to main panel
	protected JPanel upPanel;
	protected JPanel downPanel;
	
	protected void createUpPanel() {
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
	}
	
	protected void createDownPanel() {
		downPanel = new JPanel();
		downPanel.setLayout(new BorderLayout());
		
		mainPanel.add(downPanel, BorderLayout.SOUTH);
	}
	
	//buttons done cancel
	protected void createDoneBtn() {
		JButton doneBtn = new JButton("Done");
		
		downPanel.add(doneBtn, BorderLayout.EAST);
		
		addActionDoneBtn(doneBtn);
	}
	
	protected void addActionDoneBtn(JButton btn) {
		
	}
	
	protected void createCancelBtn() {
		JButton cancelBtn = new JButton("Cancel");
		
		downPanel.add(cancelBtn, BorderLayout.WEST);
		
		addActionCancelBtn(cancelBtn);
	}
	
	protected void addActionCancelBtn(JButton btn) {
		btn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		        currentFrame.dispose();
			}
			
		});
	}
	
	//method extra
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
		
		addTextBox.put(name, textBox);
		
		return(textBox);
	}
	
}
