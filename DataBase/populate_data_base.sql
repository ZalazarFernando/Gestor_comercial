USE gestorcomercial

-- Insertar empleados
-- INSERT INTO Employee (Name_Employee, Lastname_Employee, Email_Address, Number_Phone, Salary, ID_Supervised)
-- VALUES
-- ('John', 'Doe', 'john.doe@email.com', '1234567890', 50000, NULL),
-- ('Jane', 'Smith', 'jane.smith@email.com', '9876543210', 60000, 1),
-- ('Bob', 'Johnson', 'bob.johnson@email.com', '5551112233', 45000, 2)

-- UPDATE Employee
-- SET Rol = "Reponedor"
-- WHERE ID <> 1;

-- -- Insertar horas de trabajo
-- INSERT INTO Working_hours (ID_Employee, start_hour, finish_hour)
-- VALUES
-- (1, '08:00:00', '17:00:00'),
-- (2, '09:00:00', '18:00:00'),
-- (3, '10:00:00', '19:00:00')

-- -- Insertar proveedores
-- INSERT INTO Supplier (Name_Employee, Lastname_Employee, Email_Address, Number_Phone, Type_Products)
-- VALUES
-- ('Supplier1', 'Lastname1', 'supplier1@email.com', '1112223333', 'Electronics'),
-- ('Supplier2', 'Lastname2', 'supplier2@email.com', '4445556666', 'Clothing'),
-- ('Supplier3', 'Lastname3', 'supplier3@email.com', '7778889999', 'Furniture')

-- -- Insertar marcas
-- INSERT INTO Brand (Name_Employee, Type_Products, Description_Brand)
-- VALUES
-- ('Brand1', 'Electronics', 'Description1'),
-- ('Brand2', 'Clothing', 'Description2'),
-- ('Brand3', 'Furniture', 'Description3')

-- -- Insertar productos
-- INSERT INTO Product (Name_Product, List_Price, Final_Price, ID_Supplier)
-- VALUES
-- ('Product1', 100, 80, 1),
-- ('Product2', 50, 40, 2),
-- ('Product3', 200, 180, 3)

-- -- Insertar listas de recibos
-- INSERT INTO List_Receipt (Description_List, ID_Employee)
-- VALUES
-- ('List1', 1),
-- ('List2', 2),
-- ('List3', 3)

-- -- Insertar recibos
-- INSERT INTO Receipt (Description_List, ID_List_Receipt)
-- VALUES
-- ('Receipt1', 1),
-- ('Receipt2', 2),
-- ('Receipt3', 3)

-- -- Insertar productos en recibos
-- INSERT INTO Product_x_Receipt (ID_Product, ID_Receipt)
-- VALUES
-- (1, 1),
-- (2, 2),
-- (3, 3)
