package com.company.WindowsActions;

import javax.swing.JLabel;

public class WindowsActionModify extends WindowsActionAdd {
	
	public WindowsActionModify() {
		super();
		setTitle("Modify article");
		
		super.Initialize();
	}

	public WindowsActionModify(String[] words) {
		super(words);
		setTitle("Modify article");
		
		super.Initialize();
		//The stored data of the selected item should be displayed.
	}
}
