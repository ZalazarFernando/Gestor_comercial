package com.company;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.company.DataBase.DataBaseManager;

public class RegisterWindow extends WindowArchetype{
	
	public RegisterWindow(DataBaseManager databaseManager) {
		super(databaseManager);
		setSize(700, 500);
		setTitle("Register");
		setResizable(false);
		
		initialize();
	}
	
	

	@Override
	protected void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		String[] namesText = {"Customer"};
		for(int i=0; i<namesText.length ; i++) {
			panelSearch.add(createAutoTextBox(namesText[i]));
		}
		
		leftPanel.add(panelSearch);
	}



	@Override
	protected void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		String[] namesText = {"Type","Date"};
		for(int i=0; i<namesText.length ; i++) {
			panelTextBox.add(createAutoTextBox(namesText[i]));
		}
		
		rightPanel.add(panelTextBox);
	}

	@Override
	protected void createListBtn() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		btns = new ArrayList<JButton>();
		
		btns.add(new JButton("Invoice"));//factura
		btns.add(new JButton("Delivery note"));//Remito
		btns.add(new JButton("Receipt"));//Recibo
		
		for(int i=0; i<btns.size(); i++) {
			addPreferencesBtn(btns.get(i));
		}
		
		for(int i=0; i<btns.size(); i++) {
			btnPanel.add(btns.get(i));
		}
		
		leftPanel.add(btnPanel);
		
		leftPanel.add(btnPanel);
	}
	
	
}
