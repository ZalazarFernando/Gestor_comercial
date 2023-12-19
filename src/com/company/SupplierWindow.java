	package com.company;

import java.awt.Component;
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
import javax.swing.JViewport;
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
		btns.get(1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> arrayNameColumns = databaseManager.getNameColumns("Supplier");
				
				String nameColumns = "";
				for (int i = 0; i < arrayNameColumns.size(); i++) {
				    String name = arrayNameColumns.get(i);

				    if (name != null && !name.contains("ID")) {
				        nameColumns += name;
				        
				        if (i < arrayNameColumns.size() - 1 && 
				        		arrayNameColumns.get(i+1) != null) {
				            nameColumns += ", ";
				        }
				    }
				    
				}
				
				ArrayList<String> secondArrayNameColumns = databaseManager.getNameColumns("Supplier_x_Brand");
				
				String secondColumns = "";
				for (int i = 0; i < secondArrayNameColumns.size(); i++) {
				    String name = secondArrayNameColumns.get(i);
				    secondColumns += name;

				    if (i < secondArrayNameColumns.size() - 1 &&
				        secondArrayNameColumns.get(i + 1) != null) {
				        secondColumns += ", ";
				    }
				}
				
				String[] words = {
						"Name",
						"Lastname",
						"Email",
						"Phone",
						"Type"
						};
				
				String[] secondWords = {
						"Brand"
						};
				
				WindowsActionAdd waa = new WindowsActionAdd(
						words,
						secondWords,
						databaseManager, 
						"Supplier", 
						nameColumns,
						"Supplier_x_Brand",
						secondColumns);
				waa.setVisible(true);
			}
		});
		
		btns.get(2).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> arrayNameColumns = 
							databaseManager.getNameColumns("Supplier");
				
				String nameColumns = "";
				for (int i = 0; i < arrayNameColumns.size(); i++) {
				    String name = arrayNameColumns.get(i);

				    if (name != null && !name.contains("ID")) {
				        nameColumns += name;
				        
				        if (i < arrayNameColumns.size() - 1 && 
				        		arrayNameColumns.get(i+1) != null) {
				            nameColumns += ", ";
				        }
				    }
				}
				
				String[] words = {
						"Name",
						"Lastname",
						"Email",
						"Phone",
						"Type"
						};
				
				JTable jTable = new JTable();
				
				Component[] components = articleList.getComponents();
				
				for (Component component : components) {
				    if (component instanceof JScrollPane) {
				        // Hemos encontrado el JScrollPane
				        JScrollPane scrollPane = (JScrollPane) component;
				        
				        // Ahora, obten la JViewport dentro del JScrollPane
				        JViewport viewport = scrollPane.getViewport();
				        
				        // Finalmente, obtén la JTable desde la JViewport
				        jTable = (JTable) viewport.getView();
				    }
				}
				
				int selectedRow = jTable.getSelectedRow();
				
				if (selectedRow != -1) {
					Object value = jTable.getValueAt(selectedRow, 0);
				
					WindowsActionModify wam = new WindowsActionModify(
							words,
							databaseManager,
							"Supplier",
							nameColumns,
							value.toString());
					wam.setVisible(true);
				}
			}
		});
		btns.get(3).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
JTable jTable = new JTable();
				
				Component[] components = articleList.getComponents();
				
				for (Component component : components) {
				    if (component instanceof JScrollPane) {
				        // Hemos encontrado el JScrollPane
				        JScrollPane scrollPane = (JScrollPane) component;
				        
				        // Ahora, obten la JViewport dentro del JScrollPane
				        JViewport viewport = scrollPane.getViewport();
				        
				        // Finalmente, obtén la JTable desde la JViewport
				        jTable = (JTable) viewport.getView();
				    }
				}
				
				int selectedRow = jTable.getSelectedRow();
				
				if (selectedRow != -1) {
					Object value = jTable.getValueAt(selectedRow, 0);
					
					WindowsActionDelete wad = new WindowsActionDelete(
							databaseManager, 
							"Supplier",
							(String) value);
					wad.setVisible(true);
				}
			}
		});
	}

	@Override
	protected JScrollPane setElementList(String queryExtra) {
		//modificar para mostrar marcas
		String[] columnNames = {
	            "ID",
	            "Name_Supplier",
	            "Lastname_Supplier",
	            "Email_Address",
	            "Number_Phone",
	            "Type_Products",
	            "Deleted_At"
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
	    	    		+ columnNames[5] + ", "
	    	    		+ columnNames[6], 
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
