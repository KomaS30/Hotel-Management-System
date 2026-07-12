-- Create Database
CREATE DATABASE IF NOT EXISTS hoteldb;

USE hoteldb;

-- ===========================
-- CUSTOMER TABLE
-- ===========================
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10),
    phone VARCHAR(15),
    address VARCHAR(100),
    id_proof VARCHAR(30)
);

-- ===========================
-- ROOM TABLE
-- ===========================
CREATE TABLE room (
    room_no INT PRIMARY KEY,
    room_type VARCHAR(20) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'Available'
);

-- ===========================
-- BOOKING TABLE
-- ===========================
CREATE TABLE booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    room_no INT,
    check_in DATE,
    check_out DATE,
    total_amount DECIMAL(10,2),

    CONSTRAINT fk_customer
        FOREIGN KEY(customer_id)
        REFERENCES customer(customer_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_room
        FOREIGN KEY(room_no)
        REFERENCES room(room_no)
        ON DELETE CASCADE
);

-- ===========================
-- INSERT ROOM DATA
-- ===========================
INSERT INTO room(room_no, room_type, price, status) VALUES
(101,'Single',1000,'Available'),
(102,'Single',1000,'Available'),
(103,'Single',1000,'Available'),

(201,'Double',1800,'Available'),
(202,'Double',1800,'Available'),
(203,'Double',1800,'Available'),

(301,'Deluxe',2500,'Available'),
(302,'Deluxe',2500,'Available'),

(401,'Suite',4000,'Available'),
(402,'Suite',4000,'Available');

-- ===========================
-- SAMPLE CUSTOMERS
-- ===========================
INSERT INTO customer(name,gender,phone,address,id_proof) VALUES
('Komal Patil','Female','9876543210','Pune','Aadhar'),
('Rahul Sharma','Male','9876543211','Mumbai','PAN');

-- ===========================
-- SAMPLE BOOKINGS
-- ===========================
INSERT INTO booking(customer_id,room_no,check_in,check_out,total_amount)
VALUES
(1,101,'2026-07-15','2026-07-17',2000),
(2,201,'2026-07-18','2026-07-20',3600);

-- ===========================
-- UPDATE ROOM STATUS
-- ===========================

UPDATE room
SET status='Booked'
WHERE room_no IN (101,201);

USE hoteldb;

ALTER TABLE customer
ADD COLUMN email VARCHAR(100) UNIQUE,
ADD COLUMN password VARCHAR(50);

Desc customer;
SELECT DATABASE();
USE hoteldb;

CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
INSERT INTO admin(username, password)
VALUES ('admin', 'admin123');
INSERT INTO admin(username, password)
VALUES ('silver', '1234');
SELECT * FROM admin;

