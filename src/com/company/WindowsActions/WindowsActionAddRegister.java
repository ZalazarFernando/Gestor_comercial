package com.company.WindowsActions;

import java.util.ArrayList;
import java.util.List;

import com.company.DataBase.DataBaseManager;

public class WindowsActionAddRegister extends WindowsActionArchetype{
	
	protected List<String> wordOfAssistance = new ArrayList<String>();
	protected String[] words;
	protected String[] secondWords;
	protected String nameColumns; 
	protected String secondColumns; 
	
	protected String secondTable;
	
	public WindowsActionAddRegister(
			DataBaseManager databaseManager, 
			String table) {
		
		super(databaseManager, table);
		
	}
	
	public WindowsActionAddRegister(String[] words, String[] secondWords,
			DataBaseManager databaseManager, String table, 
				String nameColumns,  String secondTable,
					String secondColumn) {
		
	super(databaseManager, table);
	
	this.nameColumns = nameColumns;
	this.secondColumns = secondColumn;
	this.words = words;
	this.secondWords = secondWords;
	this.secondTable = secondTable;
	
	setTitle("Add article");
}
}
