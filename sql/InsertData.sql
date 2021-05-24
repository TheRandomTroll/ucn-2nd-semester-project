USE dmaj0920_1086341;

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

INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock, Quantity) VALUES ('Bio Waffle', 12837542, 'A waffle made with all natural products', 3.99, 5, 500, 500);
INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock, Quantity) VALUES ('Apples', 12837524, 'Always gathered fresh from Panonska Nizina', 1.99, 5, 250, 250);
INSERT INTO Products (Name, Barcode, Description, Price, MinStock, MaxStock, Quantity) VALUES ('Grapes', 12837544, 'The best Istria has to offer', 7.99, 5, 300, 300);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Iced Tea - Lemon, 340ml', '03997203', '62.99', 20, 299, 299);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Chocolate - Semi Sweet, Calets', '24048788', '12.99', 15, 109, 109);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Tomato Puree', '49025055', '34.99', 13, 134, 134);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Energy - Boo - Koo', '12699161', '01.99', 13, 169, 169);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Apples - Sliced / Wedge', '37911618', '24.99', 12, 136, 136);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Nantucket Apple Juice', '64511851', '13.99', 19, 266, 266);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Corn Kernels - Frozen', '19225945', '14.99', 11, 251, 251);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Spinach - Packaged', '41224439', '98.99', 10, 243, 243);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Turnip - White, Organic', '01825463', '17.99', 17, 288, 288);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Coconut - Shredded, Sweet', '41718690', '81.99', 20, 184, 184);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Ecolab - Hand Soap Form Antibac', '00525834', '07.99', 16, 169, 169);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Onions - Red Pearl', '00864508', '25.99', 18, 202, 202);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Dried Apple', '93766442', '87.99', 11, 217, 217);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Sprouts - Bean', '29781289', '08.99', 10, 308, 308);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Oil - Grapeseed Oil', '92233105', '85.99', 13, 248, 248);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Cabbage - Nappa', '17000775', '03.99', 16, 418, 418);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Strawberries', '70021413', '81.99', 13, 472, 472);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Sauce - Soy Low Sodium - 3.87l', '86187890', '80.99', 10, 382, 382);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Eggplant - Regular', '78608369', '67.99', 15, 256, 256);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Pineapple - Canned, Rings', '24882183', '08.99', 19, 329, 329);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Lettuce - Romaine', '00179073', '19.99', 14, 184, 184);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Wakami Seaweed', '67111827', '85.99', 12, 487, 487);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Tuna - Salad Premix', '17453600', '86.99', 10, 142, 142);
INSERT INTO Products (Name, Barcode, Price, MinStock, MaxStock, Quantity) VALUES ('Puree - Strawberry', '92428973', '52.99', 20, 121, 121);

INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Rovinjska', '16', '5', 'Pula', '52100');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Prezida XLII', '38', NULL, 'Vir', '23234');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Hauk', '159', '9', 'Lubrza', '81519');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Chinook', '8', '7', 'Abengourou', '89493');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Veith', '6221', '4', 'Hekou', '97150');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Tomscot', '8', '4', 'San Felipe', '49563');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Waubesa', '23777', '7', 'Fiditi', '41397');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Superior', '19359', '3', 'Ntungamo', '42397');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Anniversary', '085', '5', 'Suchy Las', '74154');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Huxley', '568', '0', 'Cincinnati', '17359');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Fordem', '0', '0', 'Angus', '80353');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Crescent Oaks', '80516', '7', 'Posse', '00795');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Center', '3140', '0', 'West Kelowna', '62553');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Sherman', '292', '8', 'Karnobat', '61146');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Ridgeway', '7708', '0', 'Nepalgunj', '03645');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Elmside', '9317', '1', 'Soderhamn', '54392');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Hayes', '81393', '1', 'Shuanglong', '97702');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Anhalt', '4801', '7', 'Ke-Macina', '40271');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Novick', '2912', '3', 'Tangyu', '03453');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Forster', '9552', '8', 'Hancheng', '27875');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Oak Valley', '0', '1', 'Erjiegou', '65060');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Hoffman', '206', '9', 'Vaughan', '76245');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Sutteridge', '7', '0', 'Metsemotlhaba', '63349');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Mcguire', '6428', '1', 'Baumata', '49630');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Westend', '7', '5', 'Koronadal', '08222');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Scoville', '904', '8', 'Khotsimsk', '83032');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('School', '56761', '1', 'Wangying', '62685');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Kim', '65718', '6', 'Jinsheng', '19657');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Lakeland', '2766', '3', 'Zhujiaqiao', '58907');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Forest Run', '9', '2', 'Lushikeng', '99005');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('North', '6', '5', 'Sanghan', '79919');
INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES ('Mitchell', '61293', '9', 'Valenciennes', '16835');


INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('15L87210M', '2021-09-23', 0.2);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('15K12867N', '2021-11-11', 0.15);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('54S75999E', '2021-12-02 01:48:22', 0.72);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('85E62402J', '2022-01-01 20:58:55', 0.97);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('76M28431T', '2022-04-20 01:06:53', 0.02);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('12Q91377C', '2022-02-13 14:14:45', 0.17);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('18O17794A', '2022-04-04 23:31:52', 0.18);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('05K45759R', '2022-02-14 04:06:58', 0.71);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('59D88164Q', '2021-02-05 00:21:17', 0.47);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('17I54640T', '2021-09-30 19:36:49', 0.22);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('55T30689R', '2022-01-31 16:59:08', 0.79);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('13G61418H', '2021-10-22 06:41:10', 0.18);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('38A16242O', '2022-05-20 01:45:47', 0.28);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('85A30761I', '2022-01-03 01:09:09', 0.25);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('79B62831Y', '2021-09-03 03:05:17', 0.22);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('92M98047O', '2021-09-04 11:34:05', 0.49);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('57S05800I', '2021-08-10 07:35:26', 0.47);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('52Z49708Z', '2021-09-15 18:12:35', 0.3);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('31W50808G', '2021-02-13 03:28:27', 0.91);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('07U37780C', '2022-05-02 12:24:36', 0.45);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('46F51645L', '2021-12-27 02:50:14', 0.57);
INSERT INTO Vouchers (Code, ExpirationDate, Discount) VALUES ('96P38357F', '2021-09-04 08:03:15', 0.53);

INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Marko Kovacevic', '+385915153761', 'markokovacevic@gmail.com', 1);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Mihail Veljkovic', '+385919826498', 'mihailveljkovic@gmail.com', 1);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Antonio Gerginov', '+385912387619', 'antoniogerginov@gmail.com', 2);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Derward Sylvaine', '+385992854780', 'dsylvaine0@paginegialle.it', 27);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Lazar Raper', '+385997354666', 'lraper1@cdc.gov', 29);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Elane Whapple', '+385932387572', 'ewhapple2@google.de', 21);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Vite Matushevitz', '+385949355964', 'vmatushevitz3@seattletimes.com', 13);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Milton Fuidge', '+385965280467', 'mfuidge4@vimeo.com', 30);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Ashien Fairbank', '+385961043191', 'afairbank5@addthis.com', 30);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Elsy Loughton', '+385935182725', 'eloughton6@mozilla.com', 13);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Dania Deeson', '+385930828090', 'ddeeson7@noaa.gov', 18);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Lizzy Killbey', '+385924706536', 'lkillbey8@uol.com.br', 27);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Aguie Selley', '+385927121102', 'aselley9@tuttocitta.it', 9);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Sibbie Pasfield', '+385931076830', 'spasfielda@livejournal.com', 2);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Ofilia Giron', '+385929150002', 'ogironb@apache.org', 10);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Sher Waything', '+385940528400', 'swaythingc@yale.edu', 13);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Jarrod Fewster', '+385920158892', 'jfewsterd@joomla.org', 5);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Maurizio Grestye', '+385975191124', 'mgrestyee@fotki.com', 17);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Darrel Allbones', '+385935619925', 'dallbonesf@canalblog.com', 28);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Fons Mogford', '+385916422169', 'fmogfordg@unblog.fr', 9);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Quill Lewsy', '+385987017104', 'qlewsyh@twitpic.com', 21);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Osborne Ledbetter', '+385936756975', 'oledbetteri@istockphoto.com', 12);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Fancy Geikie', '+385930616982', 'fgeikiej@1688.com', 30);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Giffie Eskell', '+385962592485', 'geskellk@ebay.co.uk', 20);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Bevvy McKennan', '+385969466591', 'bmckennanl@gizmodo.com', 18);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Ginni Tickel', '+385914924318', 'gtickelm@desdev.cn', 9);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Odetta Moores', '+385929057358', 'omooresn@chronoengine.com', 8);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Forrester Redmond', '+385977089878', 'fredmondo@barnesandnoble.com', 7);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Harald Wilber', '+385955395219', 'hwilberp@mapy.cz', 21);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Dorene Licquorish', '+385980711668', 'dlicquorishq@shinystat.com', 5);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Whitman Buffey', '+385936602036', 'wbuffeyr@shop-pro.jp', 24);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Cookie Viant', '+385987089509', 'cviants@ning.com', 24);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Juliann Sarginson', '+385923419992', 'jsarginsont@blogspot.com', 14);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Blinnie Mathison', '+385953161899', 'bmathisonu@webeden.co.uk', 26);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Sarina Hissie', '+385948788941', 'shissiev@google.cn', 26);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Golda Sowrey', '+385971073698', 'gsowreyw@github.com', 13);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Constantino Perkin', '+385955667484', 'cperkinx@addthis.com', 12);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Nancy Piatkowski', '+385989471168', 'npiatkowskiy@samsung.com', 24);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Carlen Jakubovitch', '+385990728969', 'cjakubovitchz@hhs.gov', 6);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Ealasaid Worviell', '+385956226488', 'eworviell10@walmart.com', 14);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Laney Kliesl', '+385962514277', 'lkliesl11@ca.gov', 9);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Corenda Barwack', '+385969842680', 'cbarwack12@domainmarket.com', 6);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Evania Cristofano', '+385922745561', 'ecristofano13@chron.com', 6);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Cristi Kermit', '+385932608337', 'ckermit14@kickstarter.com', 15);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Alphonso Files', '+385939807226', 'afiles15@360.cn', 21);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Ruddy Hilliam', '+385931143051', 'rhilliam16@ft.com', 23);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Lelia Harding', '+385948393188', 'lharding17@usa.gov', 20);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('La verne Quodling', '+385944131740', 'lverne18@surveymonkey.com', 8);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Hansiain Ranfield', '+385913580863', 'hranfield19@patch.com', 30);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Giralda Fairhurst', '+385950447209', 'gfairhurst1a@tinyurl.com', 10);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Dallas Medgewick', '+385981850737', 'dmedgewick1b@baidu.com', 6);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Dusty Feldon', '+385938021323', 'dfeldon1c@usatoday.com', 15);
INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES ('Eddi Capnor', '+385989396611', 'ecapnor1d@mit.edu', 18);


INSERT INTO Couriers(FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Antonio', 'Stanic', '+385987631981', 1);
INSERT INTO Couriers(FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Teo', 'Mihajlovic', '+385997755543', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Bale', 'Coultar', '+385945471800', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Filbert', 'Killingsworth', '+385950607332', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Wilmar', 'Holdforth', '+385981775030', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Cacilia', 'Gavin', '+385978971671', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Bobbe', 'Hamments', '+385945392194', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Smitty', 'Harbar', '+385901323331', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Ysabel', 'Bewlay', '+385942893488', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Linet', 'Rimour', '+385987261151', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Harrietta', 'Petroulis', '+385944227320', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Glenda', 'Lantaff', '+385983334320', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Quintus', 'Narramore', '+385940958755', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Cynthia', 'Scurrey', '+385986454706', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Greta', 'Brunesco', '+385908279767', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Dag', 'Gullen', '+385995147353', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Lucretia', 'Satterlee', '+385963791791', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Phoebe', 'Gierth', '+385963867837', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Igor', 'Kubica', '+385948700436', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Christalle', 'Thewless', '+385990319615', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Rosie', 'Caunt', '+385915010916', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Iona', 'Ridgedell', '+385975083878', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Cinderella', 'Renowden', '+385913781380', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Padget', 'Euesden', '+385960395347', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Riley', 'Belsher', '+385972569647', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Trudie', 'Franseco', '+385911913868', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Alastair', 'Boorne', '+385996879889', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Ophelie', 'McKinlay', '+385962736744', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Russell', 'Ravenshear', '+385911045956', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Curtis', 'Simm', '+385910776221', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Putnam', 'Beininck', '+385965466736', 1);
INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES ('Maisie', 'Southwick', '+385972738601', 1);