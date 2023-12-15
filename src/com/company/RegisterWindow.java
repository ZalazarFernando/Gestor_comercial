package com.company;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.company.DataBase.DataBaseManager;
import com.company.WindowsActions.WindowsActionAdd;
import com.company.WindowsActions.WindowsActionDelete;
import com.company.WindowsActions.WindowsActionModify;

public class RegisterWindow extends WindowArchetype{
	
	public RegisterWindow(DataBaseManager databaseManager) {
		super(databaseManager);
		setSize(700, 500);
		setTitle("Register");
		setResizable(false);
	}

	@Override
	protected void createSearchTextBox() {
		JPanel panelSearch = new JPanel();
		
		String[] namesText = {"Customer"};
		for(int i=0; i<namesText.length ; i++) {
			panelSearch.add(createAutoTextBox(namesText[i]));
		}
		
		leftPanel.add(panelSearch);
	}



	@Override
	protected void createSpecificationTextBox() {
		JPanel panelTextBox = new JPanel();
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		
		String[] namesText = {"Type","Date"};
		for(int i=0; i<namesText.length ; i++) {
			panelTextBox.add(createAutoTextBox(namesText[i]));
		}
		
		rightPanel.add(panelTextBox);
	}

	@Override
	protected void createListBtn() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		btns = new ArrayList<JButton>();
		
		btns.add(new JButton("Search"));
		btns.add(new JButton("Invoice"));//factura
		btns.add(new JButton("Delivery note"));//Remito
		btns.add(new JButton("Receipt"));//Recibo
		
		for(int i=0; i<btns.size(); i++) {
			addPreferencesBtn(btns.get(i));
		}
		
		for(int i=0; i<btns.size(); i++) {
			btnPanel.add(btns.get(i));
		}
		
		leftPanel.add(btnPanel);
		
		addActionBtn();
	}
	
	@Override
	protected void addActionBtn() {
		btns.get(0).addActionListener(new ActionListener() {
			
			public void refreshPanel() {
				articleList.removeAll();
				articleList.revalidate();
				articleList.repaint();
			}
			//Search by name and others
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!listTextBox.get("Customer").getText().equals("Customer") &&
						listTextBox.get("Date").getText().equals("Date") &&
						listTextBox.get("Type").getText().equals("Type")) {
					
					refreshPanel();
					
					articleList.add(
							setElementList(
								" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
			    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
			    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product "
								+ "WHERE List_Receipt.Customer = '"
								+ listTextBox.get("Customer").getText()
								+ "'"
							)
						);
				} else if (listTextBox.get("Customer").getText().equals("Customer") &&
							!listTextBox.get("Date").getText().equals("Date") &&
							listTextBox.get("Type").getText().equals("Type")) {
					refreshPanel();
					articleList.add(
							setElementList(
								" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
			    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
			    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product "
								+ "WHERE List_Receipt.Date_Receipt = '"
								+ listTextBox.get("Date").getText()
								+ "'"
							)
						);
				} else if (listTextBox.get("Customer").getText().equals("Customer") &&
							listTextBox.get("Date").getText().equals("Date") &&
							!listTextBox.get("Type").getText().equals("Type")) {
				refreshPanel();
				articleList.add(
						setElementList(
							" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
		    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
		    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product "
							+ "WHERE List_Receipt.Type_Receipt = '"
							+ listTextBox.get("Type").getText()
							+ "'"
						)
					);
			}
				else if (!listTextBox.get("Customer").getText().equals("Customer") &&
						!listTextBox.get("Date").getText().equals("Date") &&
						!listTextBox.get("Type").getText().equals("Type")) {
					refreshPanel();
					articleList.add(
							setElementList(
									" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
				    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
				    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product "
									+ "WHERE List_Receipt.Customer = '"
									+ listTextBox.get("Customer").getText()
									+ "'"
									+ " AND List_Receipt.Date_Receipt = '"
									+ listTextBox.get("Date").getText()
									+ "'"
									+ " AND List_Receipt.Type_Receipt = '"
									+ listTextBox.get("Type").getText()
									+ "'"
							)
							);
					}else {
						refreshPanel();
						articleList.add(
								setElementList(
										" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
					    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
					    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product"
								)
							);
					}
			} 
		});
	}

	@Override
	protected JScrollPane setElementList(String queryExtra) {
		String[] columnNames = {
	            "List_Receipt.ID",
	            "List_Receipt.Date_Receipt",
	            "List_Receipt.Type_Receipt",
	            "List_Receipt.Customer",
	            "Receipt.Description_List",
	            "Product.Name_Product",
	            "Product.Final_Price"
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
	    				+ columnNames[6] + ", Deleted_At",
	    				"List_Receipt", 
	    				" INNER JOIN Receipt ON Receipt.ID_List_Receipt = List_Receipt.ID "
	    				+ "INNER JOIN Product_x_Receipt ON Product_x_Receipt.ID_Receipt = Receipt.ID "
	    				+ "INNER JOIN Product ON Product.ID = Product_x_Receipt.ID_Product")
	    		);
	    
	    String[] columnNamesToTable = {
	            "ID",
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
