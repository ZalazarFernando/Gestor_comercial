package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.company.DataBase.DataBaseManager;

public class WindowsActionAddRegister extends WindowsActionArchetype{
	private JPanel btnDownPanel;
	private JButton saveBtn;
	private JButton deleteBtn;
	
	public WindowsActionAddRegister(
			DataBaseManager databaseManager) {
		
		super(databaseManager, "");
	}
	
	@Override
	protected void Initialize() {
		super.Initialize();
		setSize(400,600);
		setTitle("");
		createUpTextBox();
		createTextBoxArea();
		createDownTextBox();
		btnDownPanel();
		createBtns();
	}

	@Override
	protected void createUpPanel() {
		upPanel = new JPanel();
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
	}

	private void createUpTextBox() {
		String[] namesTextBox = {
				"Date",
	            "Type",
	            "Customer",
	            "Description",
	            "Name product", //cambiar para que se puedan elegir solo productos existentes
	            "Final price"};
		for ( int i = 0; i < namesTextBox.length; i++ ) {
			upPanel.add(createAutoTextBox(namesTextBox[i]));
		}
	}
	
	private void createTextBoxArea() {
		upPanel.add(setElementList());
	}
	
	private void createDownTextBox() {
		upPanel.add(createAutoTextBox("Description list"));
	}
	
	private void btnDownPanel() {
		btnDownPanel = new JPanel();
		btnDownPanel.setLayout(new BoxLayout(btnDownPanel, BoxLayout.X_AXIS));
		
		upPanel.add(btnDownPanel, BorderLayout.NORTH);
	}
	
	private void createBtns() {
		createSaveBtn();
		createDeleteBtn();
	}
	
	private void createSaveBtn() {
		saveBtn = new JButton("Save");
		btnDownPanel.add(saveBtn);
	}
	
	private void createDeleteBtn() {
		deleteBtn = new JButton("Delete");
		btnDownPanel.add(deleteBtn);
	}
	
	//method extra
	protected JScrollPane setElementList() {
			String[] columnNamesToTable = {
		            "Date",
		            "Type",
		            "Customer",
		            "Description",
		            "Name product",
		            "Final price"
		    };
		    
		    // Crear un modelo de tabla
		    DefaultTableModel tableModel = new DefaultTableModel();
		    
		    // Añadir nombres de columnas al modelo
		    Arrays.stream(columnNamesToTable).forEach(tableModel::addColumn);

		    /*// Añadir filas al modelo
		    infoAllEmployee.forEach(listOfFields -> {
		        Object[] rowData = listOfFields.toArray();
		        tableModel.addRow(rowData);
		    });*/

		    // Crear JTable con el modelo
		    JTable jTable = new JTable(tableModel);

		    // Crear JScrollPane con el JTable
		    JScrollPane scrollPane = new JScrollPane(jTable);
		    scrollPane.setPreferredSize(new Dimension(450, 300));

		    return scrollPane;
		}
}
