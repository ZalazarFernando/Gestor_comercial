package com.company.WindowsActions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
		
		saveBtn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Map<Integer,String> fieldValues = new HashMap<>();
				for ( Entry<String, JTextField> entry : addTextBox.entrySet() ) {
					String key = entry.getKey();
				    JTextField textField = entry.getValue();
				    
				    switch (key) {
				        case "Date":
				            LocalDateTime currentDateTime = LocalDateTime.now();
				            DateTimeFormatter formatter = 
				            		DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				            fieldValues.put(0 ,currentDateTime.format(formatter));
				            break;
				        case "Type":
				        	if (!textField.getText().equals(key)) {
				                fieldValues.put(1 ,textField.getText());
				            }
				        	break;
				        case "Customer":
				        	if (!textField.getText().equals(key)) {
				                fieldValues.put(2 ,textField.getText());
				            }
				        	break;
				        case "Description":
				        	if (!textField.getText().equals(key)) {
				                fieldValues.put(3 ,textField.getText());
				            }
				        	break;
				        case "Name product":
				        	if (!textField.getText().equals(key)) {
				                fieldValues.put(4 ,textField.getText());
				            }
				        	break;
				        case "Final price":
				        	if (!textField.getText().equals(key)) {
				                fieldValues.put(5 ,textField.getText());
				            }
				        	break;
				        default:
				            break;
				    }
				}
				ArrayList<String> auxString = new ArrayList<String>();
				for (int i = 0; i < fieldValues.size(); i++ ) {
					auxString.add(fieldValues.get(i));
				}
				
			    JScrollPane scrollPane = getScrollPane();
			    JTable jTable = getJTable(scrollPane);
			    DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

			    // Añadir fila
			    tableModel.addRow(auxString.toArray());

			    // Limpiar los JTextFields
			    clearTextFields();

			    // Actualizar la JTable
			    tableModel.fireTableDataChanged();
			}
			
		});
		
		btnDownPanel.add(saveBtn);
	}

	private void createDeleteBtn() {
		deleteBtn = new JButton("Delete");
		
		deleteBtn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener el modelo de la JTable
		        JScrollPane scrollPane = getScrollPane();
		        JTable jTable = getJTable(scrollPane);
		        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

		        // Obtener el índice de la fila seleccionada
		        int selectedRowIndex = jTable.getSelectedRow();

		        if (selectedRowIndex != -1) {
		            // Eliminar la fila seleccionada del modelo
		            tableModel.removeRow(selectedRowIndex);
		        }
			}
		});
		
		btnDownPanel.add(deleteBtn);
	}

	//method extra
	
	
	private JScrollPane getScrollPane() {
	    Component[] components = upPanel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JScrollPane) {
	            return (JScrollPane) component;
	        }
	    }
	    return null;
	}

	@Override
	protected void addActionDoneBtn(JButton btn) {
		btn.addActionListener( new ActionListener() {
			public String getNamesColums(String nameTable) {
				ArrayList<String> arrayNameColumns = 
						databaseManager.getNameColumns(nameTable);
			
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
			
			return nameColumns;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<String>();
				
				JScrollPane scrollPane = getScrollPane();
		        JTable jTable = getJTable(scrollPane);
		        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		        
		        //Add Receipt list
		        Object[] firstRowData = new Object[tableModel.getColumnCount()];
		        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
		            firstRowData[columnIndex] = tableModel.getValueAt(0, columnIndex);
		        }
		        
				data.add(addTextBox.get("Description list").getText());
				data.add("1");
				data.add((String) firstRowData[0]);
				data.add((String) firstRowData[1]);
				data.add((String) firstRowData[2]);
				data.add("30000"); //sumar todos los elementos de precio de la tabla
				
				databaseManager.setAllInfoTable(
						data.toArray(new String[0]), 
						databaseManager.createQuery(
								"INSERT INTO", 
								"?, ?, ?, ?, ?, ?", 
								"List_Receipt", 
								"Description_List, ID_Employee, "
								+"Date_Receipt, Type_Receipt, Customer, Final_Price"));
				
				//add receipt
				String[] auxID = {"ID"};
				ArrayList<String> lastIDTable = databaseManager.getAllInfoTable(
						auxID, 
						"SELECT MAX(List_Receipt.ID) AS ID FROM List_Receipt").get(0);
				
				for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
					
					for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
			            firstRowData[columnIndex] = tableModel.getValueAt(rowIndex, columnIndex);
			        }
					
					data.clear();
					
					data.add((String) firstRowData[3]);
					data.add(lastIDTable.toArray(new String[0])[0]);
					
					databaseManager.setAllInfoTable(
							data.toArray(new String[0]), 
							databaseManager.createQuery(
									"INSERT INTO", 
									"?, ?", 
									"Receipt", 
									"Description_List, ID_List_Receipt"));
					
					//conectar con productos (cambiar para que obtenga un id válido)}
					lastIDTable.clear() ;
					lastIDTable = databaseManager.getAllInfoTable(
							auxID, 
							"SELECT MAX(Receipt.ID) AS ID FROM Receipt").get(0);
					
					data.clear();
					
					data.add("1");
					data.add(lastIDTable.toArray(new String[0])[0]);
					databaseManager.setAllInfoTable(
							data.toArray(new String[0]), 
							databaseManager.createQuery(
									"INSERT INTO", 
									"?, ?", 
									"Product_x_Receipt", 
									"ID_Product, ID_Receipt"));
				}
				
				//Add Product x Receipt
				
				/*"Date",
	            "Type",
	            "Customer",
	            "Description",
	            "Name product",
	            "Final price"*/
			}
			
		});;
	}

	private JTable getJTable(JScrollPane scrollPane) {
	    if (scrollPane != null) {
	        return (JTable) scrollPane.getViewport().getView();
	    }
	    return null;
	}

	private void clearTextFields() {
		for ( Entry<String, JTextField> entry : addTextBox.entrySet() ) {
			entry.getValue().setText(entry.getKey());
		}
	}
	
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
