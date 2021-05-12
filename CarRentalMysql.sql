DROP DATABASE IF EXISTS carrental;
CREATE DATABASE carrental; 

USE carrental;

CREATE TABLE client
(
id_client INT NOT NULL AUTO_INCREMENT,
name_client VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
phone VARCHAR(10) NOT NULL,
PRIMARY KEY(id_client)
);

CREATE TABLE vehicle
(
id_vehicle INT NOT NULL AUTO_INCREMENT,
model VARCHAR(255) NOT NULL,
brand VARCHAR(255) NOT NULL,
transmission VARCHAR(1) NOT NULL,
typevehicle VARCHAR(255) NOT NULL,
price INT NOT NULL,
PRIMARY KEY(id_vehicle)
);

CREATE TABLE reservation
(
id_reservation INT NOT NULL AUTO_INCREMENT,
id_vehicle INT NOT NULL,
id_client INT NOT NULL,
typevehicle VARCHAR(1) NOT NULL,
deliver_date DATE NOT NULL,
return_date DATE NOT NULL,
total_price INT NULL,
PRIMARY KEY(id_reservation)
);

CREATE TABLE users 
(
id_user INT NOT NULL AUTO_INCREMENT,
username VARCHAR(255) NULL,
password VARCHAR(255) NULL,
PRIMARY KEY (id_user)
);

CREATE TABLE rol
(
id_rol INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
id_user INT,
PRIMARY KEY(id_rol) 
);

ALTER TABLE rol
ADD CONSTRAINT fkrolUsers
FOREIGN KEY(id_user) REFERENCES users(id_user);

ALTER TABLE reservation
ADD CONSTRAINT fkReservationVehicle
FOREIGN KEY(id_vehicle) REFERENCES vehicle(id_vehicle);

ALTER TABLE reservation
ADD CONSTRAINT fkReservationClient
FOREIGN KEY(id_client) REFERENCES client(id_client);

ALTER TABLE reservation
ADD CONSTRAINT ckDeliver
CHECK (deliver_date < return_date);

DELIMITER //
CREATE PROCEDURE SP_SubscribeClient
(
name_client VARCHAR(255),
last_name VARCHAR(255),
phone VARCHAR(10)
)
BEGIN
	DECLARE id_cliente INT DEFAULT 0;
    SET id_cliente = (SELECT MAX(id_client) FROM carrental.client);
	IF id_cliente IS NULL THEN 
		SET id_cliente = 1;
    ELSE
		SET id_cliente = id_cliente + 1;
    END IF;    
	INSERT INTO client VALUES(id_cliente,name_client,last_name,phone);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SP_SubscribeReservation
(
id_vehicle NUMERIC(3,0),
id_client INT,
typevehicle VARCHAR(1),
deliver_date DATE,
return_date DATE
)
BEGIN
	DECLARE id_reservacion INT DEFAULT 0;
    DECLARE dia INT DEFAULT 0;
    DECLARE total_price NUMERIC(6,0) DEFAULT 0;
    
	SET id_reservacion = (SELECT MAX(id_reservation) FROM carrental.reservation);
	IF id_reservacion IS null THEN 
		SET id_reservacion = 1;
    ELSE
		SET id_reservacion = id_reservacion + 1;
    END IF;
    SET dia = DATEDIFF(return_date,deliver_date);
    IF typevehicle = 'c' THEN
		SET total_price = 1000 * dia;
    ELSEIF typevehicle = 'v' THEN
		SET total_price = 1300 * dia;
	ELSE SET total_price = 1500 * dia;
    END IF;
    
	INSERT INTO reservation VALUES(id_reservacion,id_vehicle,id_client,typevehicle,deliver_date,return_date,total_price);
END //
DELIMITER ;


CALL SP_SubscribeClient('Carlos','Guerrero','5548423117');
CALL SP_SubscribeClient('Juan','Sanchez','5502135478');
CALL SP_SubscribeClient('Jessica','Aranza','5536984502');
CALL SP_SubscribeClient('Eskarlet','Gutierrez','5595347825');
CALL SP_SubscribeClient('Eduardo','Parada','5545003698');
CALL SP_SubscribeClient('Fernando','Pacheco','5500265482');
CALL SP_SubscribeClient('Reyna','Lopez','5501020304');
CALL SP_SubscribeClient('Carla','Quiñones','5569024571');
CALL SP_SubscribeClient('Erick','Lopez','5598979695');
CALL SP_SubscribeClient('Mariel','Gopar','5536148502');
CALL SP_SubscribeClient('Miguel','Fajardo','5536002464');
CALL SP_SubscribeClient('Alejandra','Espinosa','5522558800');

        
INSERT INTO vehicle
VALUES	(001,'march','nissan','a','c',1000),
		(002,'altima','nissan','s','c',1000),
		(003,'gt_r','nissan','a','s',1500),
		(004,'gt_r','nissan','s','s',1500),
		(005,'maxima','nissan','a','v',1300),
		(006,'polo','volkswagen','s','c',1000),
		(007,'vento','volkswagen','a','c',1000),
		(008,'jetta','volkswagen','s','c',1000),
		(009,'jetta_gli','volkswagen','s','v',1500),
		(010,'t_cross','volkswagen','a','v',1300),
		(011,'hilux','toyota','a','v',1300),
		(012,'rav4','toyota','s','v',1300),
		(013,'yaris','toyota','s','c',1000),
		(014,'corolla','toyota','a','c',1000),
		(015,'camry','toyota','s','s',1500),
		(016,'sportage','kia','s','v',1300),
		(017,'sorento','kia','s','v',1300),
		(018,'rio','kia','a','c',1000),
		(019,'seltos','kia','a','v',1300),
		(020,'stinger','kia','s','s',1500),
		(021,'silverado','chevrolet','s','v',1300),
		(022,'camaro','chevrolet','a','s',1500),
		(023,'aveo','chevrolet','s','c',1000),
		(024,'tahoe','chevrolet','s','v',1300),
		(025,'spark','chevrolet','a','c',1000),
		(026,'civic','honda','s','c',1000),
		(027,'cr_v','honda','a','v',1300),
		(028,'fit','honda','a','c',1000),
		(029,'accord','honda','a','c',1000),
		(030,'nsx','honda','s','s',1500),
		(031,'cx_5','mazda','a','v',1300),
		(032,'mx_5','mazda','s','s',1500),
		(033,'cx_3','mazda','a','v',1300),
		(034,'cx_9','mazda','a','v',1300),
		(035,'i8','bmw','s','s',1500),
		(036,'i3','bmw','s','s',1500),
		(037,'z4','bmw','s','s',1500),
		(038,'coupe','bmw','a','c',1000);

INSERT INTO users(username,password)
VALUES 	('employee','123'),
		('guest','321');
        
INSERT INTO rol(name,id_user)
VALUES 	('ROLE_ADMIN',1),
		('ROLE_USER',1),
        ('ROLE_USER',2);

CALL SP_SubscribeReservation(6,1,'c','2021-04-02','2021-04-04');
CALL SP_SubscribeReservation(9,2,'v','2021-04-10','2021-04-15');
CALL SP_SubscribeReservation(10,3,'s','2021-04-01','2021-05-01');
CALL SP_SubscribeReservation(25,4,'c','2021-04-26','2021-05-09');
CALL SP_SubscribeReservation(38,5,'v','2021-04-28','2021-04-30');
CALL SP_SubscribeReservation(2,6,'s','2021-04-15','2021-04-18');
CALL SP_SubscribeReservation(16,7,'c','2021-04-20','2021-04-28');
CALL SP_SubscribeReservation(30,8,'v','2021-04-21','2021-04-24');
CALL SP_SubscribeReservation(12,9,'s','2021-04-14','2021-04-19');
CALL SP_SubscribeReservation(29,10,'c','2021-04-05','2021-04-15');

SELECT * FROM CLIENT;
SELECT * FROM reservation;
SELECT * FROM vehicle;
SELECT * FROM users;
SELECT * FROM rol;