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
				ArrayList<String> fieldValues = new ArrayList<>();
				 
				for ( Entry<String, JTextField> entry : addTextBox.entrySet() ) {
					if ( !entry.getKey().equals("Date") &&
							!entry.getValue().getText().equals(entry.getKey())) {
						
						if (entry.getKey() == "Final price") {
							System.out.println(entry.getValue().getText());
						}
						fieldValues.add(entry.getValue().getText());
						System.out.println("hola");
					} else {
						LocalDateTime currentDateTime = LocalDateTime.now();

				        // Formatear la fecha y hora como una cadena
				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				        String formattedDateTime = currentDateTime.format(formatter);
				        
						fieldValues.add(formattedDateTime);
					}
				}

			    // Obtener el modelo de la JTable
			    JScrollPane scrollPane = getScrollPane();
			    JTable jTable = getJTable(scrollPane);
			    DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

			    // Añadir una nueva fila al modelo con la información recopilada
			    tableModel.addRow(fieldValues.toArray());

			    // Limpiar los JTextFields después de guardar la información
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
				
			}
			
		});
		
		btnDownPanel.add(deleteBtn);
	}

	//method extra
	// Método para obtener el JScrollPane desde el upPanel
	private JScrollPane getScrollPane() {
	    Component[] components = upPanel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JScrollPane) {
	            return (JScrollPane) component;
	        }
	    }
	    return null;
	}

	// Método para obtener la JTable desde el JScrollPane
	private JTable getJTable(JScrollPane scrollPane) {
	    if (scrollPane != null) {
	        return (JTable) scrollPane.getViewport().getView();
	    }
	    return null;
	}

	// Método para limpiar los JTextFields después de guardar la información
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
