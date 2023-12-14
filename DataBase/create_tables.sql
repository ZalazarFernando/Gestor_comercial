USE gestorcomercial;

CREATE TABLE Employee(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_Employee VARCHAR(20) NOT NULL,
    Lastname_Employee VARCHAR(20) NOT NULL,
    Email_Address VARCHAR(50) NOT NULL,
    Number_Phone VARCHAR(10) NOT NULL,
    Salary INT NOT NULL,
    ID_Supervised INT,
    Rol VARCHAR(50),
    Deleted_At DATETIME
);

ALTER TABLE Employee
ADD CONSTRAINT fk_employee_supervised
FOREIGN KEY (ID_Supervised) REFERENCES Employee(ID);

SELECT *
FROM Employee;

CREATE TABLE Working_hours(
    ID INT AUTO_INCREMENT ,
    ID_Employee INT,
    PRIMARY KEY(ID, ID_Employee),
    start_hour TIME NOT NULL,
    finish_hour TIME NOT NULL,
    FOREIGN KEY (ID_Employee) REFERENCES Employee(ID)
);

CREATE TABLE Supplier(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_Supplier VARCHAR(20) NOT NULL,
    Lastname_Supplier VARCHAR(20) NOT NULL,
    Email_Address VARCHAR(50) NOT NULL,
    Number_Phone VARCHAR(10) NOT NULL,
    Type_Products VARCHAR(40) NOT NULL
);

CREATE TABLE Brand(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_Brand VARCHAR(20) NOT NULL,
    Type_Products VARCHAR(40) NOT NULL,
    Description_Brand VARCHAR(100)
);

CREATE TABLE Supplier_x_Brand(
    ID_Supplier INT NOT NULL,
    ID_Brand INT NOT NULL,
    PRIMARY KEY(ID_Supplier, ID_Brand),
    FOREIGN KEY (ID_Supplier) REFERENCES Supplier(ID),
    FOREIGN KEY (ID_Brand) REFERENCES Brand(ID)
);

CREATE TABLE Product(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name_Product VARCHAR(50) NOT NULL,
    List_Price INT NOT NULL,
    Final_Price INT NOT NULL,
    ID_Supplier INT NOT NULL,
    FOREIGN KEY (ID_Supplier) REFERENCES Supplier(ID)
)

CREATE TABLE Product_x_Brand(
    ID_Product INT NOT NULL,
    ID_Brand INT NOT NULL,
    PRIMARY KEY(ID_Product, ID_Brand),
    FOREIGN KEY (ID_Product) REFERENCES Product(ID),
    FOREIGN KEY (ID_Brand) REFERENCES Brand(ID)
);

CREATE TABLE List_Receipt(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Description_List VARCHAR(100) NOT NULL,
    ID_Employee INT NOT NULL,
    FOREIGN KEY (ID_Employee) REFERENCES Employee(ID)
)

ALTER TABLE List_Receipt
ADD Customer VARCHAR(20);

CREATE TABLE Receipt(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Description_List VARCHAR(100) NOT NULL,
    ID_List_Receipt INT NOT NULL,
    FOREIGN KEY (ID_List_Receipt) REFERENCES List_Receipt(ID)
)

CREATE TABLE Product_x_Receipt(
    ID_Product INT NOT NULL,
    ID_Receipt INT NOT NULL,
    PRIMARY KEY(ID_Product, ID_Receipt),
    FOREIGN KEY (ID_Product) REFERENCES Product(ID),
    FOREIGN KEY (ID_Receipt) REFERENCES Receipt(ID)
);