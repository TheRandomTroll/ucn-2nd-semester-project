USE dmaj0920_1086341;

DROP TABLE IF EXISTS PaymentTypes;
DROP TABLE IF EXISTS Vouchers;
DROP TABLE IF EXISTS ShoppingLists;
DROP TABLE IF EXISTS Couriers;
DROP TABLE IF EXISTS CourierStatuses;
DROP TABLE IF EXISTS CourierBatches;
DROP TABLE IF EXISTS OrderLines;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS OrderStatuses;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS Customers;

CREATE TABLE PaymentTypes(
	Id INT PRIMARY KEY IDENTITY,
	PaymentType VARCHAR(100)
);

CREATE TABLE CourierStatuses(
	Id INT PRIMARY KEY IDENTITY,
	CourierStatus VARCHAR(100)
);

CREATE TABLE OrderStatuses(
	Id INT PRIMARY KEY IDENTITY,
	OrderStatus VARCHAR(100)
);

CREATE TABLE Products(
	Id INT PRIMARY KEY IDENTITY,
	Name VARCHAR(100),
	Barcode INT,
	Description VARCHAR(255),
	Price FLOAT,
	MaxStock INT,
	MinStock INT CHECK(MinStock > 1),
	Quantity INT
);

CREATE TABLE Addresses(
	Id INT PRIMARY KEY IDENTITY,
	Street VARCHAR(100),
	StreetNumber VARCHAR(100),
	Floor VARCHAR(100) NULL,
	City VARCHAR(1000),
	PostalCode INT
);

CREATE TABLE Vouchers(
	Id INT PRIMARY KEY IDENTITY,
	Code VARCHAR(100),
	ExpirationDate DATE,
	Discount FLOAT CHECK(Discount > 0 AND Discount < 1)
);

CREATE TABLE Customers(
	Id INT PRIMARY KEY IDENTITY,
	Name VARCHAR(100),
	PhoneNumber VARCHAR(100),
	Email VARCHAR(100),
	AddressId INT,
	FOREIGN KEY (AddressId) REFERENCES Addresses(Id)
);

CREATE TABLE Orders(
	Id INT PRIMARY KEY IDENTITY,
	OrderNumber VARCHAR(100),
	TotalPrice FLOAT,
	AppliedVoucherId INT NULL,
	FOREIGN KEY (AppliedVoucherId) REFERENCES Vouchers(Id),
	CustomerId INT,
	FOREIGN KEY (CustomerId) REFERENCES Customers(Id) ON DELETE CASCADE,
	OrderStatusId INT,
	FOREIGN KEY (OrderStatusId) REFERENCES OrderStatuses(Id),
	InvoiceAddressId INT NULL,
	FOREIGN KEY (InvoiceAddressId) REFERENCES Addresses(Id),
	DeliveryAddressId INT NULL,
	FOREIGN KEY (DeliveryAddressId) REFERENCES Addresses(Id),
);

CREATE TABLE ShoppingLists(
	Id INT PRIMARY KEY IDENTITY,
	CreationDate DATE,
	OrderId INT,
	FOREIGN KEY (OrderId) REFERENCES Orders(Id),
	PaymentTypeId INT,
	FOREIGN KEY (PaymentTypeId) REFERENCES PaymentTypes(Id)
);

CREATE TABLE OrderLines(
	Id INT PRIMARY KEY IDENTITY,
	Quantity INT,
	ProductId INT,
	FOREIGN KEY (ProductId) REFERENCES Products(Id),
	OrderId INT,
	FOREIGN KEY (OrderId) REFERENCES Orders(Id) ON DELETE CASCADE
);

CREATE TABLE Couriers(
	Id INT PRIMARY KEY IDENTITY,
	FirstName VARCHAR(100),
	LastName VARCHAR(100),
	PhoneNumber VARCHAR(100),
	CourierStatusId INT,
	FOREIGN KEY (CourierStatusId) REFERENCES CourierStatuses(Id)
);

CREATE TABLE CourierBatches(
	Id INT PRIMARY KEY IDENTITY,
	CreationDate DATE,
	OrderId INT,
	FOREIGN KEY (OrderId) REFERENCES Orders(Id),
	CourierId INT,
	FOREIGN KEY (CourierId) REFERENCES Couriers(Id)
);