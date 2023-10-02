package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowsActionAdd extends WindowsActionArchetype{
	protected List<String> wordOfAssistance = new ArrayList<String>();
	
	public WindowsActionAdd() {
		super();
	}
	
	public WindowsActionAdd(String[] words) {
		super();
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
