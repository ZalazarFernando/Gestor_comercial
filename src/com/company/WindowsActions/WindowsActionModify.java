package com.company.WindowsActions;

import javax.swing.JLabel;

import com.company.DataBase.DataBaseManager;

public class WindowsActionModify extends WindowsActionAdd {
	
	public WindowsActionModify(DataBaseManager databaseManager) {
		super(databaseManager);
		setTitle("Modify article");
		
		super.Initialize();
	}

	public WindowsActionModify(String[] words, DataBaseManager databaseManager, String table) {
		super(words, databaseManager, table);
		setTitle("Modify article");
		
		super.Initialize();
		//The stored data of the selected item should be displayed.
	}
}
