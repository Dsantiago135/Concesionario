-- =========================
-- DEALERSHIPS (3)
-- =========================
INSERT INTO TBL_DEALERSHIP VALUES (1,'AutoMax Norte','Calle 1','3001111111','active');
INSERT INTO TBL_DEALERSHIP VALUES (2,'CarZone Centro','Calle 2','3002222222','active');
INSERT INTO TBL_DEALERSHIP VALUES (3,'Motores Sur','Calle 3','3003333333','active');

-- =========================
-- CUSTOMERS (10)
-- =========================
INSERT INTO TBL_CUSTOMER VALUES (1,'Juan Perez','3100000001','juan@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (2,'Ana Lopez','3100000002','ana@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (3,'Carlos Ruiz','3100000003','carlos@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (4,'Maria Gomez','3100000004','maria@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (5,'Luis Torres','3100000005','luis@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (6,'Sofia Diaz','3100000006','sofia@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (7,'Pedro Rojas','3100000007','pedro@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (8,'Laura Castro','3100000008','laura@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (9,'Andres Vega','3100000009','andres@mail.com','active');
INSERT INTO TBL_CUSTOMER VALUES (10,'Diana Mora','3100000010','diana@mail.com','active');

-- =========================
-- VEHICLES (10)
-- =========================
INSERT INTO TBL_VEHICLE VALUES (1,'Toyota','Corolla',2020,'sedan','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (2,'Mazda','CX5',2022,'suv','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (3,'Tesla','Model 3',2023,'sedan','electric','luxury','active');
INSERT INTO TBL_VEHICLE VALUES (4,'Ford','Ranger',2021,'pickup','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (5,'BMW','X5',2023,'suv','hybrid','luxury','active');
INSERT INTO TBL_VEHICLE VALUES (6,'Kia','Rio',2019,'sedan','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (7,'Chevrolet','Onix',2020,'sedan','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (8,'Audi','A4',2022,'sedan','gasoline','luxury','active');
INSERT INTO TBL_VEHICLE VALUES (9,'Hyundai','Tucson',2021,'suv','gasoline','standard','active');
INSERT INTO TBL_VEHICLE VALUES (10,'Renault','Duster',2020,'suv','gasoline','standard','active');

-- =========================
-- EMPLOYEES (10)
-- =========================
INSERT INTO TBL_EMPLOYEE VALUES (1,1,'Manager Norte','3001000001',5000000,SYSDATE,'manager','active');
INSERT INTO TBL_EMPLOYEE VALUES (2,1,'Seller Norte 1','3001000002',2000000,SYSDATE,'seller','active');
INSERT INTO TBL_EMPLOYEE VALUES (3,1,'Seller Norte 2','3001000003',2000000,SYSDATE,'seller','active');

INSERT INTO TBL_EMPLOYEE VALUES (4,2,'Manager Centro','3002000001',5000000,SYSDATE,'manager','active');
INSERT INTO TBL_EMPLOYEE VALUES (5,2,'Seller Centro 1','3002000002',2000000,SYSDATE,'seller','active');
INSERT INTO TBL_EMPLOYEE VALUES (6,2,'Seller Centro 2','3002000003',2000000,SYSDATE,'seller','active');

INSERT INTO TBL_EMPLOYEE VALUES (7,3,'Manager Sur','3003000001',5000000,SYSDATE,'manager','active');
INSERT INTO TBL_EMPLOYEE VALUES (8,3,'Seller Sur 1','3003000002',2000000,SYSDATE,'seller','active');
INSERT INTO TBL_EMPLOYEE VALUES (9,3,'Seller Sur 2','3003000003',2000000,SYSDATE,'seller','active');

INSERT INTO TBL_EMPLOYEE VALUES (10,1,'Seller Norte 3','3001000004',2000000,SYSDATE,'seller','active');

-- =========================
-- UNITS (10)
-- =========================
-- AVAILABLE (sin ventas)
INSERT INTO TBL_UNIT VALUES (1,1,1,'AAA111','red',10000,SYSDATE,'used','available');
INSERT INTO TBL_UNIT VALUES (2,1,2,'BBB222','blue',0,SYSDATE,'new','available');
INSERT INTO TBL_UNIT VALUES (3,2,3,'CCC333','white',5000,SYSDATE,'used','available');

-- RESERVED (ventas en progreso)
INSERT INTO TBL_UNIT VALUES (4,2,4,'DDD444','black',0,SYSDATE,'new','reserved');
INSERT INTO TBL_UNIT VALUES (5,2,5,'EEE555','gray',0,SYSDATE,'new','reserved');
INSERT INTO TBL_UNIT VALUES (6,3,6,'FFF666','red',15000,SYSDATE,'used','reserved');

-- SOLD (ventas finalizadas)
INSERT INTO TBL_UNIT VALUES (7,3,7,'GGG777','blue',20000,SYSDATE,'used','sold');
INSERT INTO TBL_UNIT VALUES (8,3,8,'HHH888','black',0,SYSDATE,'new','sold');
INSERT INTO TBL_UNIT VALUES (9,1,9,'III999','white',8000,SYSDATE,'used','sold');
INSERT INTO TBL_UNIT VALUES (10,1,10,'JJJ000','gray',12000,SYSDATE,'used','sold');

-- =========================
-- SALES (6)
-- =========================
-- RESERVED → sin fecha fin
INSERT INTO TBL_SALE VALUES (1,1,2,4,SYSDATE,40000,'inprogress',NULL);
INSERT INTO TBL_SALE VALUES (2,2,5,5,SYSDATE,55000,'inprogress',NULL);
INSERT INTO TBL_SALE VALUES (3,3,8,6,SYSDATE,20000,'inprogress',NULL);

-- SOLD → con fecha fin
INSERT INTO TBL_SALE VALUES (4,4,9,7,SYSDATE,22000,'confirmed',SYSDATE);
INSERT INTO TBL_SALE VALUES (5,5,8,8,SYSDATE,45000,'confirmed',SYSDATE);
INSERT INTO TBL_SALE VALUES (6,6,10,9,SYSDATE,27000,'confirmed',SYSDATE);

-- =========================
-- SALES GOALS (10)
-- =========================
INSERT INTO TBL_SALESGOAL VALUES (1,1,1,'monthly',100000,SYSDATE,SYSDATE+30,'active');
INSERT INTO TBL_SALESGOAL VALUES (2,2,1,'monthly',80000,SYSDATE,SYSDATE+30,'active');
INSERT INTO TBL_SALESGOAL VALUES (3,4,2,'monthly',120000,SYSDATE,SYSDATE+30,'active');
INSERT INTO TBL_SALESGOAL VALUES (4,5,2,'monthly',90000,SYSDATE,SYSDATE+30,'active');
INSERT INTO TBL_SALESGOAL VALUES (5,7,3,'monthly',110000,SYSDATE,SYSDATE+30,'active');
INSERT INTO TBL_SALESGOAL VALUES (6,8,3,'monthly',85000,SYSDATE,SYSDATE+30,'active');

INSERT INTO TBL_SALESGOAL VALUES (7,NULL,1,'yearly',1000000,SYSDATE,SYSDATE+365,'active');
INSERT INTO TBL_SALESGOAL VALUES (8,NULL,2,'yearly',1200000,SYSDATE,SYSDATE+365,'active');
INSERT INTO TBL_SALESGOAL VALUES (9,NULL,3,'yearly',900000,SYSDATE,SYSDATE+365,'active');
INSERT INTO TBL_SALESGOAL VALUES (10,3,1,'quarterly',200000,SYSDATE,SYSDATE+90,'active');