INSERT INTO employee (first_name, last_name, gender, date_of_birth, phone, address, department_name, remark)
VALUES ('John', 'Ct', 0, '1990-01-01', '1234567890', '123 Main St', 'IT', 'Lorem ipsum dolor sit amet');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, phone, address, department_name, remark)
VALUES ('Jane', 'Doe', 1, '1990-01-01', '1234567890', '123 Main St', 'IT', 'Lorem ipsum dolor sit amet');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, phone, address, department_name, remark)
VALUES ('Nam', 'Smith', 0, '1990-01-01', '1234567890', '123 Main St', 'IT', 'Lorem ipsum dolor sit amet');

INSERT INTO account (account, email, password, status, employee_id)
VALUES ('admin', 'admin@example.com', 'admin', 0, 1);

INSERT INTO account (account, email, password, status, employee_id)
VALUES ('user', 'user@example.com', 'user', 1, 2);

INSERT INTO employee (first_name, last_name, gender, date_of_birth, phone, address, department_name, remark)
VALUES ('Alice', 'Brown', 1, '1992-02-14', '9876543210', '456 Park Ave', 'HR', 'Random remark 1'),
       ('Bob', 'Green', 0, '1985-07-23', '9123456780', '789 Elm St', 'Finance', 'Random remark 2'),
       ('Charlie', 'White', 0, '1993-09-12', '9234567891', '1010 Birch Rd', 'IT', 'Random remark 3'),
       ('Diana', 'Black', 1, '1991-06-08', '9345678902', '1212 Oak Ln', 'Marketing', 'Random remark 4'),
       ('Eve', 'Silver', 1, '1988-11-29', '9456789013', '1414 Maple Dr', 'HR', 'Random remark 5'),
       ('Frank', 'Blue', 0, '1986-03-17', '9567890124', '1616 Pine St', 'Operations', 'Random remark 6'),
       ('Grace', 'Orange', 1, '1987-08-30', '9678901235', '1818 Cedar Ave', 'Finance', 'Random remark 7'),
       ('Hank', 'Purple', 0, '1994-12-25', '9789012346', '2020 Spruce Ct', 'IT', 'Random remark 8'),
       ('Ivy', 'Yellow', 1, '1995-05-19', '9890123457', '2222 Willow Way', 'HR', 'Random remark 9'),
       ('Jake', 'Pink', 0, '1990-09-01', '9901234568', '2424 Ash Cir', 'Marketing', 'Random remark 10'),
       ('Kim', 'Gray', 1, '1983-10-16', '9812345679', '2626 Fir Blvd', 'Operations', 'Random remark 11'),
       ('Leo', 'Red', 0, '1989-04-11', '9723456789', '2828 Cedar St', 'Finance', 'Random remark 12'),
       ('Mia', 'Gold', 1, '1992-03-03', '9634567890', '3030 Pine Ln', 'IT', 'Random remark 13'),
       ('Nate', 'Aqua', 0, '1991-01-10', '9545678901', '3232 Oak Dr', 'HR', 'Random remark 14'),
       ('Olivia', 'Lime', 1, '1984-07-27', '9456789012', '3434 Maple Cir', 'Finance', 'Random remark 15'),
       ('Paul', 'Teal', 0, '1986-05-05', '9367890123', '3636 Birch Blvd', 'Marketing', 'Random remark 16'),
       ('Quinn', 'Indigo', 1, '1987-02-23', '9278901234', '3838 Spruce Rd', 'Operations', 'Random remark 17'),
       ('Rita', 'Coral', 1, '1990-10-09', '9189012345', '4040 Fir Ct', 'IT', 'Random remark 18'),
       ('Sam', 'Magenta', 0, '1985-08-13', '9090123456', '4242 Oak St', 'HR', 'Random remark 19'),
       ('Tina', 'Violet', 1, '1993-06-26', '9001234567', '4444 Elm Ln', 'Finance', 'Random remark 20');


INSERT INTO account (account, email, password, status, employee_id)
VALUES ('alice.b', 'alice.brown@example.com', 'alice123', 1, 3),
       ('bob.g', 'bob.green@example.com', 'bob123', 1, 4),
       ('charlie.w', 'charlie.white@example.com', 'charlie123', 1, 5),
       ('diana.b', 'diana.black@example.com', 'diana123', 1, 6),
       ('eve.s', 'eve.silver@example.com', 'eve123', 1, 7),
       ('frank.b', 'frank.blue@example.com', 'frank123', 0, 8),
       ('grace.o', 'grace.orange@example.com', 'grace123', 1, 9),
       ('hank.p', 'hank.purple@example.com', 'hank123', 1, 10),
       ('ivy.y', 'ivy.yellow@example.com', 'ivy123', 0, 11),
       ('jake.p', 'jake.pink@example.com', 'jake123', 1, 12),
       ('kim.g', 'kim.gray@example.com', 'kim123', 1, 13),
       ('leo.r', 'leo.red@example.com', 'leo123', 0, 14),
       ('mia.g', 'mia.gold@example.com', 'mia123', 1, 15),
       ('nate.a', 'nate.aqua@example.com', 'nate123', 0, 16),
       ('olivia.l', 'olivia.lime@example.com', 'olivia123', 1, 17),
       ('paul.t', 'paul.teal@example.com', 'paul123', 0, 18),
       ('quinn.i', 'quinn.indigo@example.com', 'quinn123', 1, 19),
       ('rita.c', 'rita.coral@example.com', 'rita123', 1, 20),
       ('sam.m', 'sam.magenta@example.com', 'sam123', 0, 21),
       ('tina.v', 'tina.violet@example.com', 'tina123', 1, 22);


-- Thêm dữ liệu mẫu vào bảng khach_hang
INSERT INTO khach_hang (ma_khach_hang, ten_khach_hang, dia_chi, nhom_kh_ncc, ma_so_thue, dien_thoai, ngung_theo_doi)
VALUES
    ('KH001', 'CONG TY A', '123 Le Loi, Q1, TP.HCM', 'Cong ty', '0123456789', '0909123456', FALSE),
    ('KH002', 'BENH VIEN B', '456 Hai Ba Trung, Q3, TP.HCM', 'Benh vien', '9876543210', '0909345678', FALSE),
    ('KH003', 'NHA THUOC C', '789 Vo Van Kiet, Q5, TP.HCM', 'Nha thuoc', '1234567890', '0909456789', TRUE);

-- Thêm dữ liệu mẫu vào bảng san_pham
INSERT INTO san_pham (ma_san_pham, ten_san_pham, don_vi, don_gia)
VALUES
    ('SP001', 'Thuoc A', 'Hop', 50000),
    ('SP002', 'Thuoc B', 'Chai', 75000),
    ('SP003', 'Thuoc C', 'Vien', 2000);

-- Thêm dữ liệu mẫu vào bảng don_hang
INSERT INTO don_hang (ma_don_hang, ma_khach_hang, ngay_dat, ngay_thanh_toan, dieu_khoan_tt, tong_tien, tien_vat, tong_thanh_toan)
VALUES
    ('DH001', (SELECT id FROM khach_hang WHERE ma_khach_hang = 'KH001'), '2024-11-01', '2024-11-10', '30 ngay', 150000, 15000, 165000),
    ('DH002', (SELECT id FROM khach_hang WHERE ma_khach_hang = 'KH002'), '2024-11-05', '2024-11-15', '15 ngay', 225000, 22500, 247500),
    ('DH003', (SELECT id FROM khach_hang WHERE ma_khach_hang = 'KH003'), '2024-11-10', '2024-11-20', '7 ngay', 50000, 5000, 55000);

-- Thêm dữ liệu mẫu vào bảng chi_tiet_don_hang
INSERT INTO chi_tiet_don_hang (ma_don_hang, ma_san_pham, so_luong, thanh_tien)
VALUES
    ((SELECT id FROM don_hang WHERE ma_don_hang = 'DH001'), (SELECT id FROM san_pham WHERE ma_san_pham = 'SP001'), 3, 50000 * 3),
    ((SELECT id FROM don_hang WHERE ma_don_hang = 'DH002'), (SELECT id FROM san_pham WHERE ma_san_pham = 'SP002'), 2, 75000 * 2),
    ((SELECT id FROM don_hang WHERE ma_don_hang = 'DH003'), (SELECT id FROM san_pham WHERE ma_san_pham = 'SP003'), 25, 2000 * 25);
