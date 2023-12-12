package com.company.WindowsActions;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.company.DataBase.DataBaseManager;

public class WindowsActionDelete extends WindowsActionArchetype {
	public WindowsActionDelete(DataBaseManager databaseManager, String table) {
		super(databaseManager, table);
		
		setTitle("Delete article");
		
		Initialize();
	}

	protected void Initialize() {
		super.Initialize();
		
		createLabel();
	}
	
	private void createLabel() {
		JLabel ask = new JLabel("You are sure delete this element?");
		
		upPanel.add(ask);
	}
}
