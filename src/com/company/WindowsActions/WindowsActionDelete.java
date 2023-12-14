package com.company.WindowsActions;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.company.DataBase.DataBaseManager;

public class WindowsActionDelete extends WindowsActionArchetype {
	private String index;
	
	public WindowsActionDelete(DataBaseManager databaseManager, 
			String table, String index) {
		super(databaseManager, table);
		
		this.index = index;
		
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

	@Override
	protected void addActionDoneBtn(JButton btn) {
		databaseManager.deleteIndex(
				databaseManager.createQuery(
						"DELETE", 
						"?", 
						preTable,
						"?"
						),
				Integer.valueOf(this.index)
				);
	}
}
