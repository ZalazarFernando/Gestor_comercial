package com.company;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.company.DataBase.DataBaseManager;
import com.company.WindowsActions.WindowsActionAdd;
import com.company.WindowsActions.WindowsActionArchetype;
import com.company.WindowsActions.WindowsActionDelete;
import com.company.WindowsActions.WindowsActionModify;

public class StockWindow extends WindowArchetype{
	
	public StockWindow(DataBaseManager databaseManager) {
		super(databaseManager);
		setSize(700, 500);
		setTitle("Articles");
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
		
		String[] namesText = {"Type","Brand"};
		for(int i=0; i<namesText.length ; i++) {
			panelTextBox.add(createAutoTextBox(namesText[i]));
		}
		
		rightPanel.add(panelTextBox);
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
				if (!listTextBox.get("Name").getText().equals("Name") &&
						listTextBox.get("Brand").getText().equals("Brand") &&
						listTextBox.get("Type").getText().equals("Type")) {
					refreshPanel();
					articleList.add(
							setElementList(
								" INNER JOIN Product_x_Brand "
								+ "ON Product_x_Brand.ID_Product = Product.ID "
								+ "INNER JOIN Brand "
								+ "ON Brand.ID = Product_x_Brand.ID_Brand "
								+ "WHERE Product.Name_Product = '"
								+ listTextBox.get("Name").getText()
								+ "'"
							)
						);
				} else if (listTextBox.get("Name").getText().equals("Name") &&
							!listTextBox.get("Brand").getText().equals("Brand") &&
							listTextBox.get("Type").getText().equals("Type")) {
					refreshPanel();
					articleList.add(
							setElementList(
								" INNER JOIN Product_x_Brand "
								+ "ON Product_x_Brand.ID_Product = Product.ID "
								+ "INNER JOIN Brand "
								+ "ON Brand.ID = Product_x_Brand.ID_Brand "
								+ "WHERE Brand.Name_Brand = '"
								+ listTextBox.get("Brand").getText()
								+ "'"
							)
						);
				} else if (listTextBox.get("Name").getText().equals("Name") &&
							listTextBox.get("Brand").getText().equals("Brand") &&
							!listTextBox.get("Type").getText().equals("Type")) {
				refreshPanel();
				articleList.add(
						setElementList(
							" INNER JOIN Product_x_Brand "
							+ "ON Product_x_Brand.ID_Product = Product.ID "
							+ "INNER JOIN Brand "
							+ "ON Brand.ID = Product_x_Brand.ID_Brand "
							+ "WHERE Brand.Type_Products = '"
							+ listTextBox.get("Type").getText()
							+ "'"
						)
					);
			}
				else if (!listTextBox.get("Name").getText().equals("Name") &&
						!listTextBox.get("Brand").getText().equals("Brand") &&
						!listTextBox.get("Type").getText().equals("Type")) {
					refreshPanel();
					articleList.add(
							setElementList(
									" INNER JOIN Product_x_Brand "
									+ "ON Product_x_Brand.ID_Product = Product.ID "
									+ "INNER JOIN Brand "
									+ "ON Brand.ID = Product_x_Brand.ID_Brand "
									+ "WHERE Product.Name_Product = '"
									+ listTextBox.get("Name").getText()
									+ "'"
									+ " AND Brand.Name_Brand = '"
									+ listTextBox.get("Brand").getText()
									+ "'"
									+ " AND Brand.Type_Products = '"
									+ listTextBox.get("Type").getText()
									+ "'"
							)
							);
					}else {
						refreshPanel();
						articleList.add(
								setElementList(
									" INNER JOIN Product_x_Brand "
									+ "ON Product_x_Brand.ID_Product = Product.ID "
									+ "INNER JOIN Brand "
									+ "ON Brand.ID = Product_x_Brand.ID_Brand "
								)
							);
					}
			} 
		});
		
		btns.get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] words = {
						"Name",
						"Brand",
						"Supplier",
						"Type",
						"Code",
						"Purchase price",
						"Final price",
						"Discount"};
				
				WindowsActionAdd waa = new WindowsActionAdd(words, databaseManager, "Product");
				waa.setVisible(true);
			}
		});
		btns.get(2).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionModify wam = new WindowsActionModify(databaseManager);
				wam.setVisible(true);
			}
		});
		btns.get(3).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowsActionDelete wad = new WindowsActionDelete(databaseManager, "Product");
				wad.setVisible(true);
			}
		});
	}

	@Override
	protected JScrollPane setElementList(String queryExtra) {
		if (queryExtra == null) {
			queryExtra = " INNER JOIN Product_x_Brand "
							+ "ON Product_x_Brand.ID_Product = Product.ID "
							+ "INNER JOIN Brand "
							+ "ON Brand.ID = Product_x_Brand.ID_Brand ";
		}
		
		String[] columnNames = {
			    "Product.ID",
			    "Product.Name_Product",
			    "Product.List_Price",
			    "Product.Final_Price",
			    "Product.ID_Supplier",
			    "Brand.Name_Brand",
			    "Brand.Type_Products"
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
	    				"Product", 
	    				queryExtra)
	    		);
	    
	    String[] columnNamesToTable = {
	            "ID",
	            "Name",
	            "List price",
	            "Final price",
	            "ID supplier",
	            "Brand",
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