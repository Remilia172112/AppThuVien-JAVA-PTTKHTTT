/*Chạy bằng MySQL*/
DROP DATABASE quanlycuahangsach;
CREATE DATABASE quanlycuahangsach;
USE quanlycuahangsach;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*Tạo bảng*/
CREATE TABLE `DANHMUCCHUCNANG` (
    `MCN` VARCHAR(50) NOT NULL COMMENT 'Mã chức năng',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên chức năng',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MCN) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTQUYEN` (
    `MNQ` INT(11) NOT NULL COMMENT 'Mã nhóm quyền',
    `MCN` VARCHAR(50) NOT NULL COMMENT 'Mã chức năng',
    `HANHDONG` VARCHAR(255) NOT NULL COMMENT 'Hành động thực hiện',
    PRIMARY KEY(MNQ, MCN, HANHDONG)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHOMQUYEN` (
    `MNQ` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhóm quyền',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên nhóm quyền',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNQ) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHANVIEN` (
    `MNV` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhân viên',
    `HOTEN` VARCHAR(255) NOT NULL COMMENT 'Họ và tên NV',
    `GIOITINH` INT(11) NOT NULL COMMENT 'Giới tính',
    `NGAYSINH` DATE NOT NULL COMMENT 'Ngày sinh',
    `SDT` VARCHAR(11) NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNV)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;


CREATE TABLE `TAIKHOAN` (
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MK` VARCHAR(255) NOT NULL COMMENT 'Mật khẩu',
    `TDN` VARCHAR(255) NOT NULL UNIQUE COMMENT 'Tên đăng nhập',
    `MNQ` INT(11) NOT NULL COMMENT 'Mã nhóm quyền',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    `OTP` VARCHAR(50) DEFAULT NULL COMMENT 'Mã OTP',
    PRIMARY KEY(MNV, TDN)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `KHACHHANG` (
    `MKH` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khách hàng',
    `HOTEN` VARCHAR(255) NOT NULL COMMENT 'Họ và tên KH',
    `NGAYTHAMGIA` DATE NOT NULL COMMENT 'Ngày tạo dữ liệu',
    `DIACHI` VARCHAR(255) NOT NULL COMMENT 'Địa chỉ',
    `SDT` VARCHAR(11) NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) DEFAULT '' UNIQUE COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKH)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `PHIEUXUAT` (
    `MPX` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu xuất',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MKH` INT(11) NOT NULL COMMENT 'Mã khách hàng',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME NOT NULL COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPX)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUXUAT` (
    `MPX` INT(11) NOT NULL COMMENT 'Mã phiếu xuất',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    PRIMARY KEY(MPX, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHACUNGCAP` (
    `MNCC` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhà cung cấp',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên NCC',
    `DIACHI` VARCHAR(255) NOT NULL COMMENT 'Địa chỉ',
    `SDT` VARCHAR(11) NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) NOT NULL COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNCC)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `PHIEUNHAP` (
    `MPN` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu nhập',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MNCC` INT(11) NOT NULL COMMENT 'Mã nhà cung cấp',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME NOT NULL COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPN)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUNHAP` (
    `MPN` INT(11) NOT NULL COMMENT 'Mã phiếu nhập',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `TIENNHAP` INT(11) NOT NULL COMMENT 'Tiền nhập',
    `HINHTHUC` INT(11) NOT NULL DEFAULT 0 COMMENT 'Tổng tiền',
    PRIMARY KEY(MPN, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `PHIEUKIEMKE` (
    `MPKK` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu kiểm kê',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `TG` DATETIME NOT NULL COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPKK)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUKIEMKE` (
    `MPKK` INT(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `CHENHLECH` INT(11) NOT NULL COMMENT 'Chênh lệch',
    `GHICHU` VARCHAR(255) NOT NULL COMMENT 'Ghi chú',
    PRIMARY KEY(MPKK, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `SANPHAM` (
    `MSP` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã sản phẩm',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên sản phẩm',
    `HINHANH` VARCHAR(255) NOT NULL COMMENT 'Hình sản phẩm',
    `DANHMUC` VARCHAR(255) NOT NULL COMMENT 'Danh mục',
    `NAMXB` INT(11) NOT NULL COMMENT 'Năm xuất bản',
    `MNXB` INT(11) NOT NULL COMMENT 'Mã nhà xuất bản',
    `TENTG` VARCHAR(255) NOT NULL COMMENT 'Tên tác giả',
    `MKVK` INT(11) NOT NULL COMMENT 'Mã khu vực kho',
    `TIENX` INT(11) NOT NULL COMMENT 'Tiền xuất',
    `SL` INT(11) DEFAULT 0 COMMENT 'Số lượng',
    `ISBN` VARCHAR(255) NOT NULL COMMENT 'Mã ISBN',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHAXUATBAN` (
    `MNXB` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhà xuất bản',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên nhà xuất bản',
    `DIACHI` VARCHAR(255) NOT NULL COMMENT 'Địa chỉ',
    `SDT` VARCHAR(11) NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) NOT NULL COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNXB)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `KHUVUCKHO` (
    `MKVK` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khu vực kho',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên khu vực kho',
    `GHICHU` VARCHAR(255) DEFAULT '' COMMENT 'Ghi chú',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKVK)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*Thêm dữ liệu*/

INSERT INTO `DANHMUCCHUCNANG`(`MCN`, `TEN`, `TT`)
VALUES 
        ('khachhang', 'Quản lý khách hàng', 0),
        ('khuvuckho', 'Quản lý khu vực kho', 0),
        ('nhacungcap', 'Quản lý nhà cung cấp', 0),
        ('nhanvien', 'Quản lý nhân viên', 0),
        ('nhaphang', 'Quản lý nhập hàng', 0),
        ('nhomquyen', 'Quản lý nhóm quyền', 0),
        ('sanpham', 'Quản lý sản phẩm', 0),
        ('taikhoan', 'Quản lý tài khoản', 0),
        ('thongke', 'Quản lý thống kê', 0),
        ('thuoctinh', 'Quản lý thuộc tính', 0),
        ('xuathang', 'Quản lý xuất hàng', 0);

INSERT INTO `CTQUYEN` (`MNQ`, `MCN`, `HANHDONG`)
VALUES
        (1, 'khachhang', 'create'),
        (1, 'khachhang', 'delete'),
        (1, 'khachhang', 'update'),
        (1, 'khachhang', 'view'),
        (1, 'khuvuckho', 'create'),
        (1, 'khuvuckho', 'delete'),
        (1, 'khuvuckho', 'update'),
        (1, 'khuvuckho', 'view'),
        (1, 'nhacungcap', 'create'),
        (1, 'nhacungcap', 'delete'),
        (1, 'nhacungcap', 'update'),
        (1, 'nhacungcap', 'view'),
        (1, 'nhanvien', 'create'),
        (1, 'nhanvien', 'delete'),
        (1, 'nhanvien', 'update'),
        (1, 'nhanvien', 'view'),
        (1, 'nhaphang', 'create'),
        (1, 'nhaphang', 'delete'),
        (1, 'nhaphang', 'update'),
        (1, 'nhaphang', 'view'),
        (1, 'nhomquyen', 'create'),
        (1, 'nhomquyen', 'delete'),
        (1, 'nhomquyen', 'update'),
        (1, 'nhomquyen', 'view'),
        (1, 'sanpham', 'create'),
        (1, 'sanpham', 'delete'),
        (1, 'sanpham', 'update'),
        (1, 'sanpham', 'view'),
        (1, 'taikhoan', 'create'),
        (1, 'taikhoan', 'delete'),
        (1, 'taikhoan', 'update'),
        (1, 'taikhoan', 'view'),
        (1, 'thongke', 'create'),
        (1, 'thongke', 'delete'),
        (1, 'thongke', 'update'),
        (1, 'thongke', 'view'),
        (1, 'thuoctinh', 'create'),
        (1, 'thuoctinh', 'delete'),
        (1, 'thuoctinh', 'update'),
        (1, 'thuoctinh', 'view'),
        (1, 'xuathang', 'create'),
        (1, 'xuathang', 'delete'),
        (1, 'xuathang', 'update'),
        (1, 'xuathang', 'view'),
        (2, 'khuvuckho', 'create'),
        (2, 'khuvuckho', 'update'),
        (2, 'khuvuckho', 'view'),
        (2, 'nhacungcap', 'create'),
        (2, 'nhacungcap', 'update'),
        (2, 'nhacungcap', 'view'),
        (2, 'nhaphang', 'create'),
        (2, 'nhaphang', 'update'),
        (2, 'nhaphang', 'view'),
        (2, 'sanpham', 'create'),
        (2, 'sanpham', 'update'),
        (2, 'sanpham', 'view'),
        (2, 'thuoctinh', 'create'),
        (2, 'thuoctinh', 'delete'),
        (2, 'thuoctinh', 'update'),
        (2, 'thuoctinh', 'view'),
        (3, 'khachhang', 'create'),
        (3, 'khachhang', 'update'),
        (3, 'khachhang', 'view'),
        (3, 'sanpham', 'update'),
        (3, 'sanpham', 'view'),
        (3, 'xuathang', 'create'),
        (3, 'xuathang', 'update'),
        (3, 'xuathang', 'view'),
        (4, 'khuvuckho', 'view'),
        (4, 'kiemke', 'view'),
        (4, 'nhacungcap', 'view'),
        (5, 'khachhang', 'view'),
        (5, 'khuvuckho', 'view');   

INSERT INTO `NHOMQUYEN` (`TEN`, `TT`)
VALUES
        ('Quản lý kho', 1),
        ('Nhân viên nhập hàng', 1),
        ('Nhân viên xuất hàng', 1),
        ('Thủ kho', 1),
        ('Nhân viên kiểm kho', 1);


INSERT INTO `NHANVIEN` (`HOTEN`, `GIOITINH`, `NGAYSINH`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('Lê Thế Minh', 1, '2077-01-01', '0505555505', 'Mingey0101@gmail.com', 1),
        ('Hoàng Gia Bảo', 1, '2023-04-11', '0355374322', 'musicanime2501@gmail.com', 1),
        ('Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 1),
        ('Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 1),
        ('Vũ Trung Hiếu', 1, '2023-05-06', '0123456789', 'hieu@gmail.com', 1),
        ('Trần Nhật Sinh', 1, '2003-12-20', '0387913347', 'transinh085@gmail.com', 1);

INSERT INTO `TAIKHOAN` (`MNV`, `TDN`, `MK`, `MNQ`, `TT`, `OTP`)
VALUES
        (1, 'admin', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 1, 'null');

INSERT INTO `KHACHHANG` (`HOTEN`, `DIACHI`, `SDT`, `TT`, `NGAYTHAMGIA`)
VALUES
        ('Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2023-04-19 09:52:29'),
        ('Trần Nhất Nhất', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2023-04-19 09:52:29'),
        ('Hoàng Gia Bo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2023-04-19 09:52:29'),
        ('Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2023-04-19 09:52:29'),
        ('Nguyễn Thị Minh Anh', '123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2023-04-30 17:59:57'),
        ('Trần Đức Minh', '789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2023-04-30 18:08:12'),
        ('Lê Hải Yến', '456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2023-04-30 18:08:47'),
        ('Phạm Thanh Hằng', '102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2023-04-30 18:12:59'),
        ('Hoàng Đức Anh', '321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2023-04-30 18:13:47'),
        ('Ngô Thanh Tùng', '987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2023-04-30 18:14:12'),
        ('Võ Thị Kim Ngân', '555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2023-04-30 18:15:11'),
        ('Đỗ Văn Tú', '777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2023-04-30 18:15:56'),
        ('Lý Thanh Trúc', '888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2023-04-30 18:16:22'),
        ('Bùi Văn Hoàng', '222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2023-04-30 18:16:53'),
        ('Lê Văn Thành', '23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2023-04-30 18:17:46'),
        ('Nguyễn Thị Lan Anh', '456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2023-04-30 18:18:10'),
        ('Phạm Thị Mai', '234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789012', 1, '2023-04-30 18:18:34'),
        ('Hoàng Văn Nam', ' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345678', 1, '2023-04-30 18:19:16');


INSERT INTO `PHIEUXUAT` (`MNV`, `MKH`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 1, 100000, '2023-04-01 17:34:12', 1),
        (1, 2, 200000, '2023-04-04 18:19:51', 1);

INSERT INTO `CTPHIEUXUAT` (`MPX`, `MSP`, `SL`)
VALUES
        (1, 1, 2),
        (1, 2, 2),
        (2, 3, 2),
        (2, 4, 2);

INSERT INTO `NHACUNGCAP` (`TEN`, `DIACHI`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('MINH LONG BOOK', '33 Đỗ Thừa Tự, Tân Quý, Tân Phú, Thành phố Hồ Chí Minh', '02866751142', 'cskh@minhlongbook.vn', 1),
        ('ĐINH TỊ BOOK', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('NHÃ NAM', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1),
        ('KIM ĐỒNG', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('1980 Books', '42/35 Nguyễn Minh Hoàng-Phường 12, Quận Tân Bình, Thành Phố Hồ Chí Minh', '02839333216', 'info.1980books@gmail.com', 1),
        ('THIÊN LONG', 'Tầng 10, Sofic Tower, Số 10 Đường Mai Chí Thọ, Phường Thủ Thiêm, Thành Phố Thủ Đức, Thành Phố Hồ Chí Minh', '1900866819', 'salesonline@thienlongvn.com', 1);

INSERT INTO `PHIEUNHAP` (`MNV`, `MNCC`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 1, 100000, '2023-04-01 01:09:27', 1),
        (1, 1, 200000, '2023-04-02 01:09:27', 1);

INSERT INTO `CTPHIEUNHAP` (`MPN`, `MSP`, `SL`, `TIENNHAP`, `HINHTHUC`)
VALUES
        (1, 1, 2, 20000, 0),
        (1, 2, 2, 40000, 0),
        (2, 3, 2, 40000, 0),
        (2, 4, 2, 80000, 0);

INSERT INTO `PHIEUKIEMKE` (`MNV`, `TG`, `TT`)
VALUES
        (1, '2023-04-01 01:09:27', 1),
        (1, '2023-04-02 01:09:27', 1);

INSERT INTO `CTPHIEUKIEMKE` (`MPKK`, `MSP`, `SL`, `CHENHLECH`, `GHICHU`)
VALUES
        (1, 1, 2, 20000, 'Cứu tôi'),
        (1, 2, 2, 40000, 'Minh gay'),
        (2, 3, 2, 40000, ''),
        (2, 4, 2, 80000, '');

INSERT INTO `SANPHAM` (`TEN`, `HINHANH`, `DANHMUC`, `NAMXB`, `MNXB`, `TENTG`, `MKVK`, `TIENX`, `SL`, `ISBN`, `TT`) 
VALUES
        ('Ám thị tâm lý', 'kogoiob1cgjlqhndkc0dcw1hzj1kqook.png', 'Sách dành cho giới trẻ', 2022,'NXB Công thương' ,'Patrick King - Huy Nguyễn', 0, 134000, 1, 9786046863748, 0 ),
        ('Không gì là không thể', 'xtx0d0apioa66abawbnpybv4v8wsb54p.png', 'Sách dành cho giới trẻ', 2020,'NXB Tổng hợp thành phố Hồ Chí Minh' ,'George Matthew Adams', 0, 63500, 1, 8935086837665, 0 ),
        ('Tư duy ngược', '050d67e2d58f5e291cbf25da53f55ef7.jpg', 'Sách dành cho giới trẻ', 2020,'NXB Nhà xuất bản Dân Trí' ,'Nguyễn Anh Dũng', 0, 125000, 1, 9786043440287, 0 ),
        ('Bạn đắt giá bao nhiêu?', 'txq47334nnwj1cw5f5ux7egme8erj2k7.jpg', 'Sách dành cho giới trẻ', 2021,'NXB Nhã Nam' ,'Vãn Tình', 0, 107000, 1, 8936186543500, 0 ),
        ('Hãy gọi tên tôi', '90adcd1cc9b81aa80a272a653c7785e5.png', 'Sách dành cho giới trẻ', 2020,'NXB Văn hóa - Văn nghệ TP. HCM' ,'Chanel Miller', 0, 118000, 1, 9786046863748, 0 ),
        ('Đời ngắn đừng ngủ dài', 'q9rcr9y1ax8gb1u372kxb0eivlg2xegc.jpeg', 'Sách dành cho giới trẻ', 2020,'NXB Trẻ' ,'Robin Sharma', 0, 64000, 1, 8934974158691, 0 ),
        ('Tội ác sau những bức tranh', 'rl7k9yn47k492vlxrbbnx9jmdy3ps89x.jpeg', 'Văn học - Nghệ thuật', 2022,'NXB Phụ nữ Việt Nam' ,'Jason Rekulak', 0, 146000, 1, 8934974158692, 0 ),
        ('Người rỗng', '93lh9xopdaqdtimbzosmo3n3h3evhmcv.jpeg', 'Văn học - Nghệ thuật', 2020,'NXB Văn học' ,'John Dickson Carr', 0, 70000, 1, 9786049847257, 0 ),
        ('Tạp văn Nguyễn Ngọc Tư', '4b1ea768cbbef544c4ca16fe1967634b.jpg', 'Văn học - Nghệ thuật', 2021,'NXB Trẻ' ,'Nguyễn Ngọc Tư', 0, 72000, 1, 8934974168607, 0 ),
        ('Bông sen vàng', 'ythnuw9ks3kk4l0lb1pa6fdtoo1tz32a.jpeg', 'Văn học - Nghệ thuật', 2022,'NXB Chính trị Quốc gia Sự thật' ,'Sơn Tùng', 0, 144000, 1, 8934974168617, 0 ),
        ('Hoa tuylip đen', 'sbgtbeq0vz1dhnag0qf3jrp6enxjgw20.jpeg', 'Văn học - Nghệ thuật', 2017,'NXB Văn học' ,'Alexandre Dumas cha', 0, 49000, 1, 8936067597097, 0 ),
        ('Truyện ngụ ngôn E Dốp', '883868zq1vtv4qqm7plgby2g8k0i2mkv.jpeg', 'Văn học - Nghệ thuật', 2019,'NXB Văn học' ,'Aesop', 0, 59000, 1, 9786049633198, 0 ),
        ('Nhật ký trong tù', 'cd19aa6163295ef2dff24012f78b9aec.jpg', 'Văn học - Nghệ thuật', 2021,'NXB Chính trị Quốc gia Sự thật' ,'Hồ Chí Minh', 0, 105000, 1, 9786043238112, 0 ),
        ('Phận liễu', 'detep2imksxnh97om03avji0q1p47clp.jpeg', 'Văn học - Nghệ thuật', 2020,'NXB Công an nhân dân' ,'Chu Thanh Hương', 0, 162000, 1, 9786047244300, 0 ),
        ('Phận liễu', '6619719473f6f132a18182f019364abf.jpg', 'Văn học - Nghệ thuật', 2020,'NXB Đại Học Kinh Tế Quốc Dân' ,'Ken Honda', 0, 85500, 1, 8935095629664, 0 ),
        ('Lũ trẻ thủy tinh', 'rnwjyi3x3zzxzjxlq4ba0zaeqpu0lugk.jpeg', 'Văn học thiếu nhi', 2021,'NXB Kim Đồng' ,'Kristina Ohlsson', 0, 28000, 1, 9786042190862, 0 ),
        ('Lũ trẻ đường ray', '22znw9gr4514dul0lc5rgisvc47qxfw6.jpeg', 'Văn học thiếu nhi', 2020,'NXB Văn học' ,'Edith Nesbit', 0, 63000, 1, 9786049693465, 0 ),
        ('Tớ sợ cái đồng hồ', 'fs2xgxjq2yswm6l33gfv50mwg6mgu4qm.jpeg', 'Văn học thiếu nhi', 2018,'NXB Đông Tây' ,'Nguyễn Quỳnh Mai', 0, 52000, 1, 9786045653012, 0 ),
        ('Khu rừng trong thành phố', 'cmpq0o6lb0jqmna7f9n2k7y61z84if0u.jpeg', 'Văn học thiếu nhi', 2018,'NXB Đông Tây' ,'Nguyễn Quỳnh Mai', 0, 58000, 1, 9786045653005, 0 ),
        ('Đảo ngàn sao', '5hghrc3synmydygcyzwsl7noz3pt7u31.jpeg', 'Văn học thiếu nhi', 2021,'NXB Kim Đồng' ,'Emma Karinsdotter', 0, 48000, 1, 9786042221603, 0 ),
        ('Cậu bé bạc', 'ivssbyx4axf57c2tsww8kqyt1a4xauhv.jpeg', 'Văn học thiếu nhi', 2020,'NXB Kim Đồng' ,'Kristina Ohlsson', 0, 30000, 1, 9786042186315, 0 ),
        ('Ngựa ô yêu dấu', 'wf05ukmavgsdar1a772qc24c1iawtcfg.jpeg', 'Văn học thiếu nhi', 2022,'NXB Dân Trí' ,'Anna Sewell', 0, 109000, 1, 9786043565027, 0 ),
        ('Chuyện con mèo dạy hải âu bay', 'iuj2x3gbldratw5xzjg9s1aterb66k8t.jpeg', 'Văn học thiếu nhi', 2019,'NXB Hội Nhà Văn' ,'Luis Sepúlveda', 0, 42000, 1, 8935235222113, 0 ),
        ('Chú bé mang Pyjama sọc', 'o53krzx7g5du11thdcme6xl2uqd2s8gp.jpeg', 'Văn học thiếu nhi', 2018,'NXB Hội Nhà Văn' ,'John Boyne', 0, 58000, 1, 8935235217898, 0 ),
        ('Những lá thư thời chiến Việt Nam (Tuyển tập)', '25ac83e2311e9fcaf146c655f672d6eb.jpg', 'Sách Chính trị - Xã hội', 2023,'NXB Chính trị Quốc gia Sự thật' ,'Đặng Vương Hưng', 0, 144000, 1, 8935279148646, 0 ),
        ('Kỷ yếu Hoàng Sa', 'kry3vs4zab398dch1jc23zycidl0jvaq.jpeg', 'Sách Chính trị - Xã hội', 2014,'NXB Thông tin & Truyền thông' ,'UBND Huyện Hoàng Sa', 0, 153000, 1, 9786048002930, 0 ),
        ('Dấu ấn Việt Nam trên Biển Đông', '1odzbcuzqspuou03uwdnstegf6pe4jp6.jpeg', 'Sách Chính trị - Xã hội', 2012,'NXB Thông tin & Truyền thông' ,'TS. Trần Công Trục', 0, 191000, 1, 9786048018740, 0 ),
        ('Chân dung Ngô Tất Tố', 'ytja13yxd96huojklmyy1cadwq4rykoi.jpeg', 'Sách Chính trị - Xã hội', 2014,'NXB Thông tin & Truyền thông' ,'Cao Đắc Điểm - Ngô Thị Thanh Lịch', 0, 38000, 1, 9786048005214, 0 ),
        ('Chính sách đối ngoại đổi mới của Việt Nam (1986 - 2015)', 'eplg2rc40dd1zfp0wzp4zo5s0aok0khp.jpeg', 'Sách Chính trị - Xã hội', 2018,'NXB Thế Giới' ,'PGS. TS. Phạm Quang Minh', 0, 56000, 1, 9786047744749, 0 ),
        ('Đặc trưng và vai trò của tầng lớp trung lưu ở Việt Nam', 'v4e1gkm2jgz2b7tdktk504lsgemrff59.jpeg', 'Sách Chính trị - Xã hội', 2022,'NXB Khoa học xã hội' ,'TS. Lê Kim Sa', 0, 81000, 1, 9786043089585, 0 ),
        ('Sức mạnh mềm văn hóa Trung Quốc thời Tập Cận Bình và ứng xử của Việt Nam', 'qkphoffa38djvveox22613t5qx00ha8c.jpeg', 'Sách Chính trị - Xã hội', 2022,'NXB Khoa học xã hội' ,'Ths.Chử Thị Bích Thu - TS.Trần Thị Thủy (Đồng chủ biên)', 0, 99000, 1, 97860430839493, 0 ),
        ('Đường tới Điện Biên Phủ', 'dbd44e00c80b8c79694bc2a87a36c20f.jpg', 'Sách Chính trị - Xã hội', 2018,'NXB Thông tin & Truyền thông' ,'Đại tướng Võ Nguyên Giáp', 0, 47000, 1, 9786048030759, 0 ),
        ('Đường tới Truông Bồn huyền thoại', 'wofawcrdyttsuu37j3mh06i2m6ii0lq0.jpeg', 'Sách Chính trị - Xã hội', 2019,'NXB Nghệ An' ,'Văn Hiền', 0, 150000, 1, 9786049642937, 0 ),
        ('Vương Dương Minh toàn thư', '8b90cf59071f3ed109d17770a0ec50ed.jpg', 'Sách Chính trị - Xã hội', 2023,'NXB Thông tin & Truyền thông' ,'Túc Dịch Minh - Nguyễn Thanh Hải', 0, 443000, 1, 9786048083021, 0 ),
        ('Thoát khỏi địa ngục Khmer đỏ - Hồi ký của một người còn sống', '7lmy4xhmjhgiqap0p6b2mt8ft12ju5pu.png', 'Sách Chính trị - Xã hội', 2019,'NXB Chính trị Quốc gia - Sự thật' ,'Denise Affonco', 0, 74000, 1, 9786045751718, 0 ),
        ('Điện Biên Phủ - Điểm hẹn lịch sử', '240bb8a0096e82c9769587fdb0ccfe2a.jpg', 'Sách Chính trị - Xã hội', 2018,'NXB Thông tin & Truyền thông' ,'Đại tướng Võ Nguyên Giáp', 0, 53000, 1, 9786048030742, 0 ),
        ('Sử liệu cổ nhạc Việt Nam', 'kzaj4gc27vz9pguxepwe3uoz630caym2.jpeg', 'Sách Chính trị - Xã hội', 2020,'NXB Văn hóa dân tộc' ,'Đặng Hoành Loan', 0, 405000, 1, 9786047029396, 0 ),
        ('Sự sinh thành Việt Nam', '7p1zz1z2gdgxk3f7p62nrd0xdn24sn0x.jpeg', 'Sách Chính trị - Xã hội', 2018,'NXB Thế Giới' ,'GS. Hà Văn Tấn', 0, 96000, 1, 9786047730087, 0 ),
        ('Người Dao Tiền ở Việt Nam', 'lc205cd61ud39xfvfom6lqxbeu8rklw5.jpeg', 'Sách Chính trị - Xã hội', 2021,'NXB Khoa học xã hội' ,'Lý Hành Sơn', 0, 157000, 1, 9786043086072, 0 ),
        ('Tôn tử binh pháp', 'wufet92dnkp0jt7yzehtazcyvrpnhll3.jpeg', 'Sách Chính trị - Xã hội', 2019,'NXB Hồng Đức' ,'Tôn Tử', 0, 64000, 1, 8935235222564, 0 ),
        ('5 đường mòn Hồ Chí Minh', '48a0hx2ovces506vnslpveb0qjcy6gi9.jpeg', 'Sách Chính trị - Xã hội', 2020,'NXB Thông tin & Truyền thông' ,'Đặng Phong', 0, 161000, 1, 9786048049669, 0 ),
        ('Việt Nam bản hùng ca giữ nước', '363454f37c5e79344b2a87e4d0155e7e.png', 'Sách Chính trị - Xã hội', 2021,'NXB Thông tin & Truyền thông' ,'Đặng Văn Việt', 0, 256000, 1, 9786048052508, 0 ),
        ('Bất khuất Mường Lò', 'w1xynsdkve2rv9k58gdkn8eixbvulr5y.jpeg', 'Sách Chính trị - Xã hội', 2023,'NXB Văn hóa dân tộc' ,'Trần Cao Đàm', 0, 108000, 1, 9786047035649, 0 ),
        ('Nếm trải Điện Biên', 'hgnpj4w7mbutt0tg9pjbsa8eu15q9k3a.jpeg', 'Sách Chính trị - Xã hội', 2018,'NXB Thông tin & Truyền thông' ,'Cao Tiến Lê', 0, 33000, 1, 9786048032661, 0 ),
        ('Đường Bác Hồ Đi Cứu Nước', 'oqzeqlleza3c8550w5jjg54kvloow7oy.jpeg', 'Sách Chính trị - Xã hội', 2021,'NXB Chính trị Quốc gia Sự thật' ,'GS.TS. Trình Quang Phú', 0, 148000, 1, 9786045767559, 0 ),
        ('Ký ức chiến trận - Quảng Trị 1972 - 2022 (Bìa cứng) - Nguyễn Xuân Vượng', 'ds7l546w53f0otq26c67em4mle8xoszq.jpeg', 'Sách Chính trị - Xã hội', 2022,'NXB Nhà xuất bản Dân Trí' ,'Nguyễn Xuân Vượng', 0, 160000, 1, 9786043566628, 0 );


INSERT INTO `NHAXUATBAN` (`TENNXB`, `DIACHI`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('NXB Kim Đồng', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('NXB Trẻ', '161B Lý Chính Thắng, phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh', '02839316289', 'hopthubandoc@nxbtre.com.vn', 1),
        ('NXB Tổng hợp thành phố Hồ Chí Minh', '62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, TP. HCM', '02838256804', 'nstonhop@gmail.com', 1),
        ('NXB Hội Nhà văn', 'số 65 Nguyễn Du, Quận Hai Bà Trưng, Hà Nội', '02438222135', 'nhaxuatbanhnv@gmail.com', 1),
        ('NXB Chính trị quốc gia Sự thật ', '6/86 Duy tân, Cầu Giấy, Hà Nội', '02438221581', 'phathanh@nxbctqg.vn', 1),
        ('NXB Phụ nữ Việt Nam', '39 Hàng Chuối, Quận Hai Bà Trưng, Hà Nội', '02439710717', 'truyenthongvaprnxbpn@gmail.com', 1),
        ('NXB Lao Động', '175 Giảng Võ, Đống Đa, Hà Nội', '', 'nxblaodong@yahoo.com', 1),
        ('NXB Đinh Tị Book', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('NXB Nhã Nam', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1);
        /*
         NXB Nhà xuất bản Dân Trí
        NXB Văn hóa - Văn nghệ TP. HCM
        NXB Công an nhân dân
        NXB Đại Học Kinh Tế Quốc Dân
        NXB Đông Tây
        NXB Thông tin & Truyền thông
        NXB Thế Giới
        NXB Khoa học xã hội
        NXB Nghệ An
        NXB Văn hóa dân tộc
        NXB Hồng Đức
        */

INSERT INTO `KHUVUCKHO` (`TEN`, `GHICHU`, `TT`)
VALUES
        ('Khu vực A', 'Thiếu nhi', 1),
        ('Khu vực B', 'Khoa học', 1),
        ('Khu vực C', 'Văn học', 1),
        ('Khu vực D', 'Tâm lý, tình cảm', 1),
        ('Khu vực E', 'Lịch sử', 1),
        ('Khu vực F', 'Kinh dị', 1);

/*Tạo quan hệ*/

ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MNQ_CTQUYEN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MCN_CTQUYEN FOREIGN KEY (MCN) REFERENCES `DANHMUCCHUCNANG`(MCN) ON DELETE NO ACTION ON UPDATE NO ACTION;           

ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNV_TAIKHOAN FOREIGN KEY (MVN) REFERENCES `NHANVIEN`(MVN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNQ_TAIKHOAN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MNV_PHIEUXUAT FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MKH_PHIEUXUAT FOREIGN KEY (MKH) REFERENCES `KHACHHANG`(MKH) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MPX_CTPHIEUXUAT FOREIGN KEY (MPX) REFERENCES `PHIEUXUAT`(MPX) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MSP_CTPHIEUXUAT FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNV_PHIEUNHAP FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNCC_PHIEUNHAP FOREIGN KEY (MNCC) REFERENCES `NHACUNGCAP`(MNCC) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MPN_CTPHIEUNHAP FOREIGN KEY (MPN) REFERENCES `PHIEUNHAP`(MPN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MSP_CTPHIEUNHAP FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUKIEMKE` ADD CONSTRAINT FK_MNV_PHIEUKIEMKE FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MPKK_CTPHIEUKIEMKE FOREIGN KEY (MPKK) REFERENCES `PHIEUKIEMKE`(MPKK) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MSP_CTPHIEUKIEMKE FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MNXB_SANPHAM FOREIGN KEY (MNXB) REFERENCES `NHAXUATBAN`(MNXB) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MKVK_SANPHAM FOREIGN KEY (MKVK) REFERENCES `KHUVUCKHO`(MKVK) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*Xóa bảng*/

DROP TABLE `DANHMUCCHUCNANG`;

DROP TABLE `CTQUYEN`;

DROP TABLE `NHOMQUYEN`;

DROP TABLE `NHANVIEN`;

DROP TABLE `TAIKHOAN`;

DROP TABLE `KHACHHANG`;

DROP TABLE `PHIEUXUAT`;

DROP TABLE `CTPHIEUXUAT`;

DROP TABLE `NHACUNGCAP`;

DROP TABLE `PHIEUNHAP`;

DROP TABLE `CTPHIEUNHAP`;

DROP TABLE `PHIEUKIEMKE`;

DROP TABLE `CTPHIEUKIEMKE`;

DROP TABLE `SANPHAM`;

DROP TABLE `NHAXUATBAN`;

DROP TABLE `KHUVUCKHO`;
