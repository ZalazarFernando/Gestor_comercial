package com.company;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class StockWindow extends WindowArchetype{
	private JTabbedPane tabbedPane;
	
	public StockWindow() {
		setSize(700, 500);
		setTitle("Articles");
		setResizable(false);
		
		initialize();
	}

}