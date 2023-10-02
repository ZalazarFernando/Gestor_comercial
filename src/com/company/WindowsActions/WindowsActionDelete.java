package com.company.WindowsActions;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class WindowsActionDelete extends WindowsActionArchetype {
	public WindowsActionDelete() {
		super();
		
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
