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
    `EMAIL` VARCHAR(50) UNIQUE COMMENT 'Email',
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
    `DIACHI` VARCHAR(255) COMMENT 'Địa chỉ',
    `SDT` VARCHAR(12) COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) COMMENT 'Email',
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
    `TIENN` INT(11) NOT NULL COMMENT 'Tiền nhập',
    `SL` INT(11) DEFAULT 0 COMMENT 'Số lượng',
    `ISBN` VARCHAR(255) NOT NULL COMMENT 'Mã ISBN',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHAXUATBAN` (
    `MNXB` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhà xuất bản',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên nhà xuất bản',
    `DIACHI` VARCHAR(255) COMMENT 'Địa chỉ',
    `SDT` VARCHAR(12) COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) COMMENT 'Email',
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
        ('sanpham', 'Quản lý sản phẩm', 0),
        ('khachhang', 'Quản lý khách hàng', 0),
        ('nhacungcap', 'Quản lý nhà cung cấp', 0),
        ('nhaxuatban', 'Quản lý nhà xuất bản', 0),
        ('nhanvien', 'Quản lý nhân viên', 0),
        ('nhaphang', 'Quản lý nhập hàng', 0),
        ('xuathang', 'Quản lý xuất hàng', 0),
        ('khuvuckho', 'Quản lý khu vực kho', 0),
        ('nhomquyen', 'Quản lý nhóm quyền', 0),
        ('taikhoan', 'Quản lý tài khoản', 0),
        ('thongke', 'Quản lý thống kê', 0);

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
        (1, 'nhaxuatban', 'create'),
        (1, 'nhaxuatban', 'delete'),
        (1, 'nhaxuatban', 'update'),
        (1, 'nhaxuatban', 'view'),
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
        (3, 'khachhang', 'create'),
        (3, 'khachhang', 'update'),
        (3, 'khachhang', 'view'),
        (3, 'sanpham', 'update'),
        (3, 'sanpham', 'view'),
        (3, 'xuathang', 'create'),
        (3, 'xuathang', 'update'),
        (3, 'xuathang', 'view'),
        (4, 'khuvuckho', 'view'),
        (4, 'nhacungcap', 'view'),
        (5, 'khachhang', 'view'),
        (5, 'khuvuckho', 'view');   

INSERT INTO `NHOMQUYEN` (`TEN`, `TT`)
VALUES
        ('Quản lý tổng', 1),
        ('Nhân viên nhập hàng', 1),
        ('Nhân viên xuất hàng', 1),
        ('Thủ kho', 1),
        ('Nhân viên kiểm kho', 1);


INSERT INTO `NHANVIEN` (`HOTEN`, `GIOITINH`, `NGAYSINH`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('Lê Thế Minh', 1, '2077-01-01', '0505555505', 'remchan.com@gmail.com', 1),
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

INSERT INTO `SANPHAM` (`TEN`, `HINHANH`, `DANHMUC`, `NAMXB`, `MNXB`, `TENTG`, `MKVK`, `TIENX`, `SL`, `ISBN`, `TT`) 
VALUES
        ('Ám thị tâm lý', 'kogoiob1cgjlqhndkc0dcw1hzj1kqook.png', 'Sách dành cho giới trẻ', 2022, 21,'Patrick King - Huy Nguyễn', 1, 134000, 1, 9786046863748, 1),
        ('Không gì là không thể', 'xtx0d0apioa66abawbnpybv4v8wsb54p.png', 'Sách dành cho giới trẻ', 2020, 3,'George Matthew Adams', 1, 63500, 1, 8935086837665, 1),
        ('Tư duy ngược', '050d67e2d58f5e291cbf25da53f55ef7.jpg', 'Sách dành cho giới trẻ', 2020, 10,'Nguyễn Anh Dũng', 1, 125000, 1, 9786043440287, 1),
        ('Bạn đắt giá bao nhiêu?', 'txq47334nnwj1cw5f5ux7egme8erj2k7.jpg', 'Sách dành cho giới trẻ', 2021,9 ,'Vãn Tình', 1, 107000, 1, 8936186543500, 1),
        ('Hãy gọi tên tôi', '90adcd1cc9b81aa80a272a653c7785e5.png', 'Sách dành cho giới trẻ', 2020,11 ,'Chanel Miller', 1, 118000, 1, 9786046863748, 1),
        ('Đời ngắn đừng ngủ dài', 'q9rcr9y1ax8gb1u372kxb0eivlg2xegc.jpeg', 'Sách dành cho giới trẻ', 2020,2 ,'Robin Sharma', 1, 64000, 1, 8934974158691, 1),
        ('Tội ác sau những bức tranh', 'rl7k9yn47k492vlxrbbnx9jmdy3ps89x.jpeg', 'Văn học - Nghệ thuật', 2022,6 ,'Jason Rekulak', 1, 146000, 1, 8934974158692, 1),
        ('Người rỗng', '93lh9xopdaqdtimbzosmo3n3h3evhmcv.jpeg', 'Văn học - Nghệ thuật', 2020,22 ,'John Dickson Carr', 1, 70000, 1, 9786049847257, 1),
        ('Tạp văn Nguyễn Ngọc Tư', '4b1ea768cbbef544c4ca16fe1967634b.jpg', 'Văn học - Nghệ thuật', 2021,2 ,'Nguyễn Ngọc Tư', 1, 72000, 1, 8934974168607, 1),
        ('Bông sen vàng', 'ythnuw9ks3kk4l0lb1pa6fdtoo1tz32a.jpeg', 'Văn học - Nghệ thuật', 2022,5 ,'Sơn Tùng', 1, 144000, 1, 8934974168617, 1),
        ('Hoa tuylip đen', 'sbgtbeq0vz1dhnag0qf3jrp6enxjgw20.jpeg', 'Văn học - Nghệ thuật', 2017,22 ,'Alexandre Dumas cha', 1, 49000, 1, 8936067597097, 1),
        ('Truyện ngụ ngôn E Dốp', '883868zq1vtv4qqm7plgby2g8k0i2mkv.jpeg', 'Văn học - Nghệ thuật', 2019,22 ,'Aesop', 1, 59000, 1, 9786049633198, 1),
        ('Nhật ký trong tù', 'cd19aa6163295ef2dff24012f78b9aec.jpg', 'Văn học - Nghệ thuật', 2021,5 ,'Hồ Chí Minh', 1, 105000, 1, 9786043238112, 1),
        ('Phận liễu', 'detep2imksxnh97om03avji0q1p47clp.jpeg', 'Văn học - Nghệ thuật', 2020,12 ,'Chu Thanh Hương', 1, 162000, 1, 9786047244300, 1),
        ('Đồng tiền hạnh phúc', '6619719473f6f132a18182f019364abf.jpg', 'Văn học - Nghệ thuật', 2020,13 ,'Ken Honda', 1, 85500, 1, 8935095629664, 1),
        ('Lũ trẻ thủy tinh', 'rnwjyi3x3zzxzjxlq4ba0zaeqpu0lugk.jpeg', 'Văn học thiếu nhi', 2021,1 ,'Kristina Ohlsson', 1, 28000, 1, 9786042190862, 1),
        ('Lũ trẻ đường ray', '22znw9gr4514dul0lc5rgisvc47qxfw6.jpeg', 'Văn học thiếu nhi', 2020,22 ,'Edith Nesbit', 1, 63000, 1, 9786049693465, 1),
        ('Tớ sợ cái đồng hồ', 'fs2xgxjq2yswm6l33gfv50mwg6mgu4qm.jpeg', 'Văn học thiếu nhi', 2018,14 ,'Nguyễn Quỳnh Mai', 1, 52000, 1, 9786045653012, 1),
        ('Khu rừng trong thành phố', 'cmpq0o6lb0jqmna7f9n2k7y61z84if0u.jpeg', 'Văn học thiếu nhi', 2018,14 ,'Nguyễn Quỳnh Mai', 1, 58000, 1, 9786045653005, 1),
        ('Đảo ngàn sao', '5hghrc3synmydygcyzwsl7noz3pt7u31.jpeg', 'Văn học thiếu nhi', 2021,1 ,'Emma Karinsdotter', 1, 48000, 1, 9786042221603, 1),
        ('Cậu bé bạc', 'ivssbyx4axf57c2tsww8kqyt1a4xauhv.jpeg', 'Văn học thiếu nhi', 2020,1 ,'Kristina Ohlsson', 1, 30000, 1, 9786042186315, 1),
        ('Ngựa ô yêu dấu', 'wf05ukmavgsdar1a772qc24c1iawtcfg.jpeg', 'Văn học thiếu nhi', 2022,23 ,'Anna Sewell', 1, 109000, 1, 9786043565027, 1),
        ('Chuyện con mèo dạy hải âu bay', 'iuj2x3gbldratw5xzjg9s1aterb66k8t.jpeg', 'Văn học thiếu nhi', 2019,4 ,'Luis Sepúlveda', 1, 42000, 1, 8935235222113, 1),
        ('Chú bé mang Pyjama sọc', 'o53krzx7g5du11thdcme6xl2uqd2s8gp.jpeg', 'Văn học thiếu nhi', 2018,4 ,'John Boyne', 1, 58000, 1, 8935235217898, 1),
        ('Những lá thư thời chiến Việt Nam (Tuyển tập)', '25ac83e2311e9fcaf146c655f672d6eb.jpg', 'Sách Chính trị - Xã hội', 2023,5 ,'Đặng Vương Hưng', 1, 144000, 1, 8935279148646, 1),
        ('Kỷ yếu Hoàng Sa', 'kry3vs4zab398dch1jc23zycidl0jvaq.jpeg', 'Sách Chính trị - Xã hội', 2014,15 ,'UBND Huyện Hoàng Sa', 1, 153000, 1, 9786048002930, 1),
        ('Dấu ấn Việt Nam trên Biển Đông', '1odzbcuzqspuou03uwdnstegf6pe4jp6.jpeg', 'Sách Chính trị - Xã hội', 2012,15 ,'TS. Trần Công Trục', 1, 191000, 1, 9786048018740, 1),
        ('Chân dung Ngô Tất Tố', 'ytja13yxd96huojklmyy1cadwq4rykoi.jpeg', 'Sách Chính trị - Xã hội', 2014,15 ,'Cao Đắc Điểm - Ngô Thị Thanh Lịch', 1, 38000, 1, 9786048005214, 1),
        ('Chính sách đối ngoại đổi mới của Việt Nam (1986 - 2015)', 'eplg2rc40dd1zfp0wzp4zo5s0aok0khp.jpeg', 'Sách Chính trị - Xã hội', 2018,16 ,'PGS. TS. Phạm Quang Minh', 1, 56000, 1, 9786047744749, 1),
        ('Đặc trưng và vai trò của tầng lớp trung lưu ở Việt Nam', 'v4e1gkm2jgz2b7tdktk504lsgemrff59.jpeg', 'Sách Chính trị - Xã hội', 2022,17 ,'TS. Lê Kim Sa', 1, 81000, 1, 9786043089585, 1),
        ('Sức mạnh mềm văn hóa Trung Quốc thời Tập Cận Bình và ứng xử của Việt Nam', 'qkphoffa38djvveox22613t5qx00ha8c.jpeg', 'Sách Chính trị - Xã hội', 2022,17 ,'Ths.Chử Thị Bích Thu - TS.Trần Thị Thủy (Đồng chủ biên)', 1, 99000, 1, 97860430839493, 1),
        ('Đường tới Điện Biên Phủ', 'dbd44e00c80b8c79694bc2a87a36c20f.jpg', 'Sách Chính trị - Xã hội', 2018,15 ,'Đại tướng Võ Nguyên Giáp', 1, 47000, 1, 9786048030759, 1),
        ('Đường tới Truông Bồn huyền thoại', 'wofawcrdyttsuu37j3mh06i2m6ii0lq0.jpeg', 'Sách Chính trị - Xã hội', 2019,18 ,'Văn Hiền', 1, 150000, 1, 9786049642937, 1),
        ('Vương Dương Minh toàn thư', '8b90cf59071f3ed109d17770a0ec50ed.jpg', 'Sách Chính trị - Xã hội', 2023,15 ,'Túc Dịch Minh - Nguyễn Thanh Hải', 1, 443000, 1, 9786048083021, 1),
        ('Thoát khỏi địa ngục Khmer đỏ - Hồi ký của một người còn sống', '7lmy4xhmjhgiqap0p6b2mt8ft12ju5pu.png', 'Sách Chính trị - Xã hội', 2019,5 ,'Denise Affonco', 1, 74000, 1, 9786045751718, 1),
        ('Điện Biên Phủ - Điểm hẹn lịch sử', '240bb8a0096e82c9769587fdb0ccfe2a.jpg', 'Sách Chính trị - Xã hội', 2018,15 ,'Đại tướng Võ Nguyên Giáp', 1, 53000, 1, 9786048030742, 1),
        ('Sử liệu cổ nhạc Việt Nam', 'kzaj4gc27vz9pguxepwe3uoz630caym2.jpeg', 'Sách Chính trị - Xã hội', 2020,19 ,'Đặng Hoành Loan', 1, 405000, 1, 9786047029396, 1),
        ('Sự sinh thành Việt Nam', '7p1zz1z2gdgxk3f7p62nrd0xdn24sn0x.jpeg', 'Sách Chính trị - Xã hội', 2018,16 ,'GS. Hà Văn Tấn', 1, 96000, 1, 9786047730087, 1),
        ('Người Dao Tiền ở Việt Nam', 'lc205cd61ud39xfvfom6lqxbeu8rklw5.jpeg', 'Sách Chính trị - Xã hội', 2021,17 ,'Lý Hành Sơn', 1, 157000, 1, 9786043086072, 1),
        ('Tôn tử binh pháp', 'wufet92dnkp0jt7yzehtazcyvrpnhll3.jpeg', 'Sách Chính trị - Xã hội', 2019,20 ,'Tôn Tử', 1, 64000, 1, 8935235222564, 1),
        ('5 đường mòn Hồ Chí Minh', '48a0hx2ovces506vnslpveb0qjcy6gi9.jpeg', 'Sách Chính trị - Xã hội', 2020,15 ,'Đặng Phong', 1, 161000, 1, 9786048049669, 1),
        ('Việt Nam bản hùng ca giữ nước', '363454f37c5e79344b2a87e4d0155e7e.png', 'Sách Chính trị - Xã hội', 2021,15 ,'Đặng Văn Việt', 1, 256000, 1, 9786048052508, 1),
        ('Bất khuất Mường Lò', 'w1xynsdkve2rv9k58gdkn8eixbvulr5y.jpeg', 'Sách Chính trị - Xã hội', 2023,19 ,'Trần Cao Đàm', 1, 108000, 1, 9786047035649, 1),
        ('Nếm trải Điện Biên', 'hgnpj4w7mbutt0tg9pjbsa8eu15q9k3a.jpeg', 'Sách Chính trị - Xã hội', 2018,15 ,'Cao Tiến Lê', 1, 33000, 1, 9786048032661, 1),
        ('Đường Bác Hồ Đi Cứu Nước', 'oqzeqlleza3c8550w5jjg54kvloow7oy.jpeg', 'Sách Chính trị - Xã hội', 2021,5 ,'GS.TS. Trình Quang Phú', 1, 148000, 1, 9786045767559, 1),
        ('Ký ức chiến trận - Quảng Trị 1972 - 2022 (Bìa cứng) - Nguyễn Xuân Vượng', 'ds7l546w53f0otq26c67em4mle8xoszq.jpeg', 'Sách Chính trị - Xã hội', 2022, 10,'Nguyễn Xuân Vượng', 1, 160000, 1, 9786043566628, 1);


INSERT INTO `NHAXUATBAN` (`TEN`, `DIACHI`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('NXB Kim Đồng', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('NXB Trẻ', '161B Lý Chính Thắng, phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh', '02839316289', 'hopthubandoc@nxbtre.com.vn', 1),
        ('NXB Tổng hợp thành phố Hồ Chí Minh', '62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, TP. HCM', '02838256804', 'nstonhop@gmail.com', 1),
        ('NXB Hội Nhà văn', 'số 65 Nguyễn Du, Quận Hai Bà Trưng, Hà Nội', '02438222135', 'nhaxuatbanhnv@gmail.com', 1),
        ('NXB Chính trị Quốc gia Sự thật', '6/86 Duy tân, Cầu Giấy, Hà Nội', '02438221581', 'phathanh@nxbctqg.vn', 1),
        ('NXB Phụ nữ Việt Nam', '39 Hàng Chuối, Quận Hai Bà Trưng, Hà Nội', '02439710717', 'truyenthongvaprnxbpn@gmail.com', 1),
        ('NXB Lao Động', '175 Giảng Võ, Đống Đa, Hà Nội', '', 'nxblaodong@yahoo.com', 1),
        ('NXB Đinh Tị Book', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('NXB Nhã Nam', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1),
        ('NXB Nhà xuất bản Dân Trí', ' Số 9, ngõ 26, phố Hoàng Cầu, phường Ô Chợ Dừa, quận Đống Đa, Hà Nội', '02466860751', 'nxbdantri@gmail.com', 1),
        ('NXB Văn hóa - Văn nghệ TP. HCM', '88-90 Ký Con, P. Nguyễn Thái Bình, Q. 1, Tp. Hồ Chí Minh', '02838216009', ' nxbvhvn@nxbvanhoavannghe.org.vn', 1),
        ('NXB Công an nhân dân', '100 Yết Kiêu, Hai Bà Trưng, Hà Nội', '0692342969', '', 1),
        ('NXB Đại Học Kinh Tế Quốc Dân', '207 Đường Giải Phóng - Hà Nội', '02436282487', 'nxb@neu.edu.vn', 1),
        ('NXB Đông Tây', 'Số 9 Lê Văn Thiêm - Thanh Xuân - Hà Nội', '02422157878', 'lienhe@dongtay.vn', 1),
        ('NXB Thông tin & Truyền thông', 'Tầng 6, Tòa nhà Cục Tần số vô tuyến điện, 115 Trần Duy Hưng, Hà Nội', '02435772139', 'nxb.tttt@mic.gov.vn', 1),
        ('NXB Thế Giới', '7 Đ. Nguyễn Thị Minh Khai, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', '02838220102', '', 1),
        ('NXB Khoa học xã hội', '26 Lý Thường Kiệt, Phường Hàng Bài, Quận Hoàn Kiếm, Hà Nội', '', 'nxbkhxh@gmail.com', 1),
        ('NXB Nghệ An', 'Số 37B, Lê Hồng Phong, thành phố Vinh, Nghệ An', '02383844748', 'nhaxuatbannghean@gmail.com', 1),
        ('NXB Văn hóa dân tộc', ' Số 19 Nguyễn Bỉnh Khiêm,Quận Hai Bà Trưng,TP. Hà Nội', '02438263070', 'nxbvanhoadantoc@yahoo.com.vn', 1),
        ('NXB Hồng Đức', '65 P. Tràng Thi, Hàng Bông, Hoàn Kiếm, Hà Nội', '02439260024', '', 1),
        ('NXB Công thương', 'Tầng 4 - Tòa nhà Bộ Công thương, số 655 Phạm Văn Đồng - Bắc Từ Liêm - Hà Nội - Việt Nam', '02439341562', 'nxbct@moit.gov.vn', 1),
        ('NXB Văn học', '18 Nguyễn Trường Tộ - Ba Đình - Hà Nội', '02437163409', 'info@nxbvanhoc.com.vn', 1),
        ('NXB Dân Trí', 'Số 9, ngõ 26, phố Hoàng Cầu, phường Ô Chợ Dừa, quận Đống Đa, Hà Nội', '02466860751', 'nxbdantri@gmail.com', 1);
        
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

ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNV_TAIKHOAN FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNQ_TAIKHOAN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MNV_PHIEUXUAT FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MKH_PHIEUXUAT FOREIGN KEY (MKH) REFERENCES `KHACHHANG`(MKH) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MPX_CTPHIEUXUAT FOREIGN KEY (MPX) REFERENCES `PHIEUXUAT`(MPX) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MSP_CTPHIEUXUAT FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNV_PHIEUNHAP FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNCC_PHIEUNHAP FOREIGN KEY (MNCC) REFERENCES `NHACUNGCAP`(MNCC) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MPN_CTPHIEUNHAP FOREIGN KEY (MPN) REFERENCES `PHIEUNHAP`(MPN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MSP_CTPHIEUNHAP FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MNXB_SANPHAM FOREIGN KEY (MNXB) REFERENCES `NHAXUATBAN`(MNXB) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MKVK_SANPHAM FOREIGN KEY (MKVK) REFERENCES `KHUVUCKHO`(MKVK) ON DELETE NO ACTION ON UPDATE NO ACTION;

COMMIT;