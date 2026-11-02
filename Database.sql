CREATE DATABASE blood_bank;
USE blood_bank;

CREATE TABLE donors (
    donor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    blood_group VARCHAR(5) NOT NULL
);

CREATE TABLE hospitals (
    hospital_id INT PRIMARY KEY AUTO_INCREMENT,
    hospital_name VARCHAR(150) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    blood_group VARCHAR(5) NOT NULL,
    units INT NOT NULL DEFAULT 0,
    expiry_date DATE
);

CREATE TABLE blood_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    hospital_id INT NOT NULL,
    blood_group VARCHAR(5) NOT NULL,
    units_requested INT NOT NULL,
    priority VARCHAR(20) DEFAULT 'Normal',
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (hospital_id)
        REFERENCES hospitals(hospital_id)
);
CREATE TABLE blood_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    hospital_id INT NOT NULL,
    blood_group VARCHAR(5) NOT NULL,
    units_requested INT NOT NULL,
    priority VARCHAR(20) DEFAULT 'Normal',
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (hospital_id)
        REFERENCES hospitals(hospital_id)
);

CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    donor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (donor_id)
        REFERENCES donors(donor_id)
);

INSERT INTO inventory (blood_group, units, expiry_date)
VALUES
('A+', 10, '2026-12-01'),
('A-', 5, '2026-12-05'),
('B+', 12, '2026-12-10'),
('B-', 4, '2026-12-12'),
('O+', 15, '2026-12-15'),
('O-', 6, '2026-12-18'),
('AB+', 8, '2026-12-20'),
('AB-', 3, '2026-12-22');
