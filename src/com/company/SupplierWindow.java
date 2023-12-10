package com.company;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.company.DataBase.DataBaseManager;
import com.company.WindowsActions.WindowsActionAdd;
import com.company.WindowsActions.WindowsActionDelete;
import com.company.WindowsActions.WindowsActionModify;

public class SupplierWindow extends WindowArchetype{

	public SupplierWindow(DataBaseManager databaseManager) {
		super(databaseManager);
		setSize(700, 500);
		setTitle("Supplier");
		setResizable(false);
	}

	@Override
	protected void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		String[] namesText = {"Name"};
		for(int i=0; i<namesText.length ; i++) {
			panelSearch.add(createAutoTextBox(namesText[i]));
		}
		
		leftPanel.add(panelSearch);
	}

	@Override
	protected void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		String[] namesText = {"Brand","Type"};
		for(int i=0; i<namesText.length ; i++) {
			panelTextBox.add(createAutoTextBox(namesText[i]));
		}
		
		rightPanel.add(panelTextBox);
	}
	
	@Override
	protected void addActionBtn() {
		btns.get(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] words = {
						"legal name",
						"CUIT",
						"Address"};
				
				WindowsActionAdd waa = new WindowsActionAdd(words);
				waa.setVisible(true);
			}
		});
		
		btns.get(1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionModify wam = new WindowsActionModify();
				wam.setVisible(true);
			}
		});
		btns.get(2).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionDelete wad = new WindowsActionDelete();
				wad.setVisible(true);
			}
		});
	}

	@Override
	protected JScrollPane setElementList() {
		//modificar para mostrar marcas
		String[] columnNames = {
	            "ID",
	            "Name_Supplier",
	            "Lastname_Supplier",
	            "Email_Address",
	            "Number_Phone",
	            "Type_Products"
	    };

	    ArrayList<ArrayList> infoAllEmployee = this.databaseManager.getAllInfoTable(
	    		columnNames, 
	    		this.databaseManager.createQuery(
	    				"SELECT",
	    				columnNames[0] + ", " 
	    	    		+ columnNames[1] + ", " 
	    	    		+ columnNames[2] + ", " 
	    	    		+ columnNames[3] + ", "
	    	    		+ columnNames[4] + ", "
	    	    		+ columnNames[5], 
	    				"Supplier", 
	    				null)
	    		);
	    
	    String[] columnNamesToTable = {
	            "ID",
	            "Name",
	            "Lastname",
	            "Email",
	            "Number Phone",
	            "Type"
	    };
	    
	    // Crear un modelo de tabla
	    DefaultTableModel tableModel = new DefaultTableModel();
	    
	    // Añadir nombres de columnas al modelo
	    Arrays.stream(columnNamesToTable).forEach(tableModel::addColumn);

	    // Añadir filas al modelo
	    infoAllEmployee.forEach(listOfFields -> {
	        Object[] rowData = listOfFields.toArray();
	        tableModel.addRow(rowData);
	    });

	    // Crear JTable con el modelo
	    JTable jTable = new JTable(tableModel);

	    // Crear JScrollPane con el JTable
	    JScrollPane scrollPane = new JScrollPane(jTable);
	    scrollPane.setPreferredSize(new Dimension(450, 500));

	    return scrollPane;
	}
}
