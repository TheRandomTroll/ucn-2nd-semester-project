INSERT INTO PaymentTypes (PaymentType) VALUES ('Cash');
INSERT INTO PaymentTypes (PaymentType) VALUES ('CashOnDelivery');
INSERT INTO PaymentTypes (PaymentType) VALUES ('Card');
INSERT INTO PaymentTypes (PaymentType) VALUES ('PayPal');

INSERT INTO CourierStatuses (CourierStatus) VALUES ('Available')
INSERT INTO CourierStatuses (CourierStatus) VALUES ('Delivering');
INSERT INTO CourierStatuses (CourierStatus) VALUES ('OnVacation');

INSERT INTO OrderStatuses (OrderStatus) VALUES ('Draft');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('Allocated');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('Packed');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('Shipped');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('Delivered');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('NotAccepted');
INSERT INTO OrderStatuses (OrderStatus) VALUES ('Cancelled');

INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock) VALUES ('Bio Waffle', 12837542, 'A waffle made with all natural products', 3.99, 5, 500);
INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock) VALUES ('Apples', 12837524, 'Always gathered fresh from Panonska Nizina', 1.99, 5, 250);
INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock) VALUES ('Grapes', 12837544, 'The best Istria has to offer', 7.99, 5, 300);

INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Rovinjska', '16', '5', 'Pula', '52100');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Prezida XLII', '38', NULL, 'Vir', '23234');

INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('15L87210M', '2021-09-23', 0.2);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('15K12867N', '2021-11-11', 0.15);

INSERT INTO Customers (Name, PhoneNumber, AddressId) VALUES ('Marko Kovacevic', '+385915153761', 1);
INSERT INTO Customers (Name, PhoneNumber, AddressId) VALUES ('Mihail Veljkovic', '+385919826498', 1);
INSERT INTO Customers (Name, PhoneNumber, AddressId) VALUES ('Antonio Gerginov', '+385912387619', 2);

INSERT INTO Couriers(FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Antonio', 'Stanic', '+385987631981', 1);
INSERT INTO Couriers(FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Teo', 'Mihajlovic', '+385997755543', 1);