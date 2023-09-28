package com.company;

import java.awt.BorderLayout;
import javax.swing.*;

public class FirstFrame extends JFrame{
	
	private JPanel panel;
    
    
	public FirstFrame() {
		setSize(400,500);
		setTitle("Gestor");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializePanels();
	}
	
	public void initializePanels() {
		createPanel();
		new SuperiorPanel(panel);
	}
	
	public void createPanel() {
		panel = new JPanel();
        panel.setLayout(new BorderLayout());
		
		getContentPane().add(panel);
	}
	
	
}