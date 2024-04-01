DROP DATABASE quanlycuahangsach;
CREATE DATABASE quanlycuahangsach;
USE quanlycuahangsach;


--Tạo bảng
CREATE TABLE [DANHMUCCHUCNANG] (
    [MCN] VARCHAR(50) NOT NULL, --Mã chức năng
    [TEN] VARCHAR(255) NOT NULL, --Tên chức năng
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MCN) 
);

CREATE TABLE [CTQUYEN] (
    [MNQ] INT NOT NULL, --Mã nhóm quyền
    [MCN] VARCHAR(50) NOT NULL, --Mã chức năng
    [HANHDONG] VARCHAR(255) NOT NULL, --Hành động thực hiện
    PRIMARY KEY(MNQ, MCN, HANHDONG)
);

CREATE TABLE [NHOMQUYEN] (
    [MNQ] INT NOT NULL IDENTITY(1,1), --Mã nhóm quyền
    [TEN] VARCHAR(255) NOT NULL, --Tên nhóm quyền
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MNQ) 
);

CREATE TABLE [NHANVIEN] (
    [MNV] INT NOT NULL IDENTITY(1,1), --Mã nhân viên
    [HOTEN] VARCHAR(255) NOT NULL, --Họ và tên NV
    [GIOITINH] INT NOT NULL DEFAULT '', --Giới tính
    [NGAYSINH] DATE NOT NULL, --Ngày sinh
    [SDT] VARCHAR(11) NOT NULL, --Số điện thoại
    [EMAIL] VARCHAR(50) NOT NULL, --Email
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MNV)
);


CREATE TABLE [TAIKHOAN] (
    [MNV] INT NOT NULL, --Mã nhân viên
    [MK] VARCHAR(255) NOT NULL, --Mật khẩu
    [TDN] VARCHAR(255) NOT NULL, --Tên đăng nhập
    [MNQ] INT NOT NULL, --Mã nhóm quyền
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    [OTP] VARCHAR(50) DEFAULT NULL --Mã OTP 
    PRIMARY KEY(MNV, TDN)
);

CREATE TABLE [KHACHHANG] (
    [MKH] INT NOT NULL IDENTITY(1,1), --Mã khách hàng
    [HOTEN] VARCHAR(255) NOT NULL, --Họ và tên KH
    [NGAYTHAMGIA] DATE NOT NULL, --Ngày tạo dữ liệu
    [DIACHI] VARCHAR(255) NOT NULL, --Địa chỉ
    [SDT] VARCHAR(11) NOT NULL, --Số điện thoại
    [EMAIL] VARCHAR(50) DEFAULT '', --Email
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MKH)
);

CREATE TABLE [PHIEUXUAT] (
    [MPX] INT NOT NULL IDENTITY(1,1), --Mã phiếu xuất
    [MNV] INT NOT NULL, --Mã nhân viên
    [MKH] INT NOT NULL, --Mã khách hàng
    [TIEN] INT NOT NULL, --Tổng tiền
    [TG] DATETIME NOT NULL, --Thời gian tạo
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MPX)
);

CREATE TABLE [CTPHIEUXUAT] (
    [MPX] INT NOT NULL, --Mã phiếu xuất
    [MSP] INT NOT NULL, --Mã sản phẩm
    [SL] INT NOT NULL, --Số lượng
    PRIMARY KEY(MPX, MSP)
);

CREATE TABLE [NHACUNGCAP] (
    [MNCC] INT NOT NULL IDENTITY(1,1), --Mã nhà cung cấp
    [TEN] VARCHAR(255) NOT NULL, --Tên NCC
    [DIACHI] VARCHAR(255) NOT NULL, --Địa chỉ
    [SDT] VARCHAR(11) NOT NULL, --Số điện thoại
    [EMAIL] VARCHAR(50) NOT NULL, --Email
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MNCC)
);

CREATE TABLE [PHIEUNHAP] (
    [MPN] INT NOT NULL IDENTITY(1,1), --Mã phiếu nhập
    [MNV] INT NOT NULL, --Mã nhân viên
    [MNCC] INT NOT NULL, --Mã nhà cung cấp
    [TIEN] INT NOT NULL, --Tổng tiền
    [TG] DATETIME NOT NULL, --Thời gian tạo
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MPN)
);

CREATE TABLE [CTPHIEUNHAP] (
    [MPN] INT NOT NULL, --Mã phiếu nhập
    [MSP] INT NOT NULL, --Mã sản phẩm
    [SL] INT NOT NULL, --Số lượng
    [TIENNHAP] INT NOT NULL, --Tiền nhập
    [HINHTHUC] INT NOT NULL DEFAULT 0, --Tổng tiền
    PRIMARY KEY(MPN, MSP)
);

CREATE TABLE [PHIEUKIEMKE] (
    [MPKK] INT NOT NULL IDENTITY(1,1), --Mã phiếu kiểm kê
    [MNV] INT NOT NULL, --Mã nhân viên
    [TG] DATETIME NOT NULL, --Thời gian tạo
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MPKK)
);

CREATE TABLE [CTPHIEUKIEMKE] (
    [MPKK] INT NOT NULL, --Mã phiếu kiểm kê
    [MSP] INT NOT NULL, --Mã sản phẩm
    [SL] INT NOT NULL, --Số lượng
    [CHENHLECH] INT NOT NULL, --Chênh lệch
    [GHICHU] VARCHAR(255) NOT NULL, --Ghi chú
    PRIMARY KEY(MPKK, MSP)
);

CREATE TABLE [SANPHAM] (
    [MSP] INT NOT NULL IDENTITY(1,1), --Mã sản phẩm
    [TENSP] VARCHAR(255) NOT NULL, --Tên sản phẩm
    [HINHANH] VARCHAR(255) NOT NULL, --Hình sản phẩm
    [DANHMUC] VARCHAR(255) NOT NULL, --Danh mục
    [NAMXB] INT NOT NULL, --Năm xuất bản
    [MNXB] INT NOT NULL, --Mã nhà xuất bản
    [TENTG] VARCHAR(255) NOT NULL, --Tên tác giả
    [MKVK] INT NOT NULL, --Mã khu vực kho
    [TIENX] INT NOT NULL, --Tiền xuất
    [SL] INT DEFAULT 0, --Số lượng
    [ISBN] VARCHAR(255) NOT NULL, --Mã ISBN
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MSP)
);

CREATE TABLE [NHAXUATBAN] (
    [MNXB] INT NOT NULL IDENTITY(1,1), --Mã nhà xuất bản
    [TENNXB] VARCHAR(255) NOT NULL, --Tên nhà xuất bản
    [DIACHI] VARCHAR(255) NOT NULL, --Địa chỉ
    [SDT] VARCHAR(11) NOT NULL, --Số điện thoại
    [EMAIL] VARCHAR(50) NOT NULL, --Email
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MNXB)
);

CREATE TABLE [KHUVUCKHO] (
    [MKVK] INT NOT NULL IDENTITY(1,1), --Mã khu vực kho
    [TENKV] VARCHAR(255) NOT NULL, --Tên khu vực kho
    [GHICHU] VARCHAR(255) DEFAULT '', --Ghi chú
    [TT] INT NOT NULL DEFAULT 1, --Trạng thái
    PRIMARY KEY(MKVK)
);

--Thêm dữ liệu

INSERT INTO [DANHMUCCHUCNANG]([MCN], [TEN], [TT])
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

INSERT INTO [CTQUYEN] ([MNQ], [MCN], [HANHDONG])
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
        -- (4, 'donvitinh', 'view'),
        (4, 'khuvuckho', 'view'),
        (4, 'kiemke', 'view'),
        -- (4, 'loaisanpham', 'view'),
        (4, 'nhacungcap', 'view'),
        (5, 'khachhang', 'view'),
        (5, 'khuvuckho', 'view');   

INSERT INTO [NHOMQUYEN] ([TEN], [TT])
VALUES
        ('Quản lý kho', 1),
        ('Nhân viên nhập hàng', 1),
        ('Nhân viên xuất hàng', 1),
        ('Thủ kho', 1),
        ('Nhân viên kiểm kho', 1);


INSERT INTO [NHANVIEN] ([HOTEN], [GIOITINH], [NGAYSINH], [SDT], [EMAIL], [TT])
VALUES
        ('Lê Thế Minh', 1, '2077-01-01', '0505555505', 'Mingey0101@gmail.com', 1),
        ('Hoàng Gia Bảo', 1, '2023-04-11', '0355374322', 'musicanime2501@gmail.com', 1),
        ('Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 1),
        ('Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 1),
        ('Vũ Trung Hiếu', 1, '2023-05-06', '0123456789', 'hieu@gmail.com', 1),
        ('Trần Nhật Sinh', 1, '2003-12-20', '0387913347', 'transinh085@gmail.com', 1);

INSERT INTO [TAIKHOAN] ([MNV], [MK], [TDN], [MNQ], [TT], [OTP])
VALUES
        (1, '123456', 'admin', 1, 1, 'null');

INSERT INTO [KHACHHANG] ([HOTEN], [DIACHI], [SDT], [TT], [NGAYTHAMGIA])
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


INSERT INTO [PHIEUXUAT] ([MNV], [MKH], [TIEN], [TG], [TT])
VALUES
        (1, 1, 100000, '2023-04-01 17:34:12', 1),
        (1, 2, 200000, '2023-04-04 18:19:51', 1);

INSERT INTO [CTPHIEUXUAT] ([MPX], [MSP], [SL])
VALUES
        (1, 1, 2),
        (1, 2, 2),
        (2, 3, 2),
        (2, 4, 2);

INSERT INTO [NHACUNGCAP] ([TEN], [DIACHI], [SDT], [EMAIL], [TT])
VALUES
        ('MINH LONG BOOK', '33 Đỗ Thừa Tự, Tân Quý, Tân Phú, Thành phố Hồ Chí Minh', '02866751142', 'cskh@minhlongbook.vn', 1),
        ('ĐINH TỊ BOOK', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('NHÃ NAM', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1),
        ('KIM ĐỒNG', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('1980 Books', '42/35 Nguyễn Minh Hoàng-Phường 12, Quận Tân Bình, Thành Phố Hồ Chí Minh', '02839333216', 'info.1980books@gmail.com', 1),
        ('THIÊN LONG', 'Tầng 10, Sofic Tower, Số 10 Đường Mai Chí Thọ, Phường Thủ Thiêm, Thành Phố Thủ Đức, Thành Phố Hồ Chí Minh', '1900866819', 'salesonline@thienlongvn.com', 1);

INSERT INTO [PHIEUNHAP] ([MNV], [MNCC], [TIEN], [TG], [TT])
VALUES
        (1, 1, 100000, '2023-04-01 01:09:27', 1),
        (1, 1, 200000, '2023-04-02 01:09:27', 1);

INSERT INTO [CTPHIEUNHAP] ([MPN], [MSP], [SL], [TIENNHAP], [HINHTHUC])
VALUES
        (1, 1, 2, 20000, 0),
        (1, 2, 2, 40000, 0),
        (2, 3, 2, 40000, 0),
        (2, 4, 2, 80000, 0);

INSERT INTO [PHIEUKIEMKE] ([MNV], [TG], [TT])
VALUES
        (1, '2023-04-01 01:09:27', 1),
        (1, '2023-04-02 01:09:27', 1);

INSERT INTO [CTPHIEUKIEMKE] ([MPKK], [MSP], [SL], [CHENHLECH], [GHICHU])
VALUES
        (1, 1, 2, 20000, 'Cứu tôi'),
        (1, 2, 2, 40000, 'Minh gay'),
        (2, 3, 2, 40000, ''),
        (2, 4, 2, 80000, '');

CREATE TABLE [SANPHAM] (
    [MSP] INT(11) NOT NULL, --Mã sản phẩm
    [TENSP] VARCHAR(255) NOT NULL, --Tên sản phẩm
    [HINHANH] VARCHAR(255) NOT NULL, --Hình sản phẩm
    [THELOAI] VARCHAR(255) NOT NULL, --Thể loại
    [NAMXB] INT(11) NOT NULL, --Năm xuất bản
    [MNXB] INT(11) NOT NULL, --Mã nhà xuất bản
    [TENTG] VARCHAR(255) NOT NULL, --Tên tác giả
    [MKVK] INT(11) NOT NULL, --Mã khu vực kho
    [TIENN] INT(11) NOT NULL, --Tiền nhập
    [TIENX] INT(11) NOT NULL, --Tiền xuất
    [SL] INT(11) DEFAULT 0, --Số lượng
    [ISBN] VARCHAR(255) NOT NULL, --Mã ISBN
    [TT] INT(10) NOT NULL DEFAULT 1, --Trạng thái
)

INSERT INTO [NHAXUATBAN] ([TENNXB], [DIACHI], [SDT], [EMAIL], [TT])
VALUES
        ('Nhà xuất bản Kim Đồng', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('Nhà xuất bản Trẻ', '161B Lý Chính Thắng, phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh', '02839316289', 'hopthubandoc@nxbtre.com.vn', 1),
        ('Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh', '62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, TP. HCM', '02838256804', 'nstonhop@gmail.com', 1),
        ('Nhà xuất bản Hội Nhà văn', 'số 65 Nguyễn Du, Quận Hai Bà Trưng, Hà Nội', '02438222135', 'nhaxuatbanhnv@gmail.com', 1),
        ('Nhà xuất bản Chính trị quốc gia Sự thật ', '6/86 Duy tân, Cầu Giấy, Hà Nội', '02438221581', 'phathanh@nxbctqg.vn', 1),
        ('Nhà xuất bản Phụ nữ Việt Nam', '39 Hàng Chuối, Quận Hai Bà Trưng, Hà Nội', '02439710717', 'truyenthongvaprnxbpn@gmail.com', 1),
        ('Nhà xuất bản Lao Động', '175 Giảng Võ, Đống Đa, Hà Nội', '', 'nxblaodong@yahoo.com', 1),
        ('Nhà xuất bản Đinh Tị Book', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('Nhà xuất bản Nhã Nam', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1);

INSERT INTO [KHUVUCKHO] ([TENKV], [GHICHU], [TT])
VALUES
        ('Khu vực A', 'Thiếu nhi', 1),
        ('Khu vực B', 'Khoa học', 1),
        ('Khu vực C', 'Văn học', 1),
        ('Khu vực D', 'Tâm lý, tình cảm', 1),
        ('Khu vực E', 'Lịch sử', 1),
        ('Khu vực F', 'Kinh dị', 1);

--Tạo quan hệ

ALTER TABLE [CTQUYEN] ADD CONSTRAINT FK_MNQ_CTQUYEN FOREIGN KEY (MNQ) REFERENCES [NHOMQUYEN](MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [CTQUYEN] ADD CONSTRAINT FK_MCN_CTQUYEN FOREIGN KEY (MCN) REFERENCES [DANHMUCCHUCNANG](MCN) ON DELETE NO ACTION ON UPDATE NO ACTION;           

ALTER TABLE [TAIKHOAN] ADD CONSTRAINT FK_MNV_TAIKHOAN FOREIGN KEY (MVN) REFERENCES [NHANVIEN](MVN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [TAIKHOAN] ADD CONSTRAINT FK_MNQ_TAIKHOAN FOREIGN KEY (MNQ) REFERENCES [NHOMQUYEN](MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [PHIEUXUAT] ADD CONSTRAINT FK_MNV_PHIEUXUAT FOREIGN KEY (MNV) REFERENCES [NHANVIEN](MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [PHIEUXUAT] ADD CONSTRAINT FK_MKH_PHIEUXUAT FOREIGN KEY (MKH) REFERENCES [KHACHHANG](MKH) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [CTPHIEUXUAT] ADD CONSTRAINT FK_MPX_CTPHIEUXUAT FOREIGN KEY (MPX) REFERENCES [PHIEUXUAT](MPX) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [CTPHIEUXUAT] ADD CONSTRAINT FK_MSP_CTPHIEUXUAT FOREIGN KEY (MSP) REFERENCES [SANPHAM](MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [PHIEUNHAP] ADD CONSTRAINT FK_MNV_PHIEUNHAP FOREIGN KEY (MNV) REFERENCES [NHANVIEN](MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [PHIEUNHAP] ADD CONSTRAINT FK_MNCC_PHIEUNHAP FOREIGN KEY (MNCC) REFERENCES [NHACUNGCAP](MNCC) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [CTPHIEUNHAP] ADD CONSTRAINT FK_MPN_CTPHIEUNHAP FOREIGN KEY (MPN) REFERENCES [PHIEUNHAP](MPN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [CTPHIEUNHAP] ADD CONSTRAINT FK_MSP_CTPHIEUNHAP FOREIGN KEY (MSP) REFERENCES [SANPHAM](MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [PHIEUKIEMKE] ADD CONSTRAINT FK_MNV_PHIEUKIEMKE FOREIGN KEY (MNV) REFERENCES [NHANVIEN](MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [CTPHIEUKIEMKE] ADD CONSTRAINT FK_MPKK_CTPHIEUKIEMKE FOREIGN KEY (MPKK) REFERENCES [PHIEUKIEMKE](MPKK) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [CTPHIEUKIEMKE] ADD CONSTRAINT FK_MSP_CTPHIEUKIEMKE FOREIGN KEY (MSP) REFERENCES [SANPHAM](MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE [SANPHAM] ADD CONSTRAINT FK_MNXB_SANPHAM FOREIGN KEY (MNXB) REFERENCES [NHAXUATBAN](MNXB) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE [SANPHAM] ADD CONSTRAINT FK_MKVK_SANPHAM FOREIGN KEY (MKVK) REFERENCES [KHUVUCKHO](MKVK) ON DELETE NO ACTION ON UPDATE NO ACTION;

--Xóa bảng

DROP TABLE [DANHMUCCHUCNANG];

DROP TABLE [CTQUYEN];

DROP TABLE [NHOMQUYEN];

DROP TABLE [NHANVIEN];

DROP TABLE [TAIKHOAN];

DROP TABLE [KHACHHANG];

DROP TABLE [PHIEUXUAT];

DROP TABLE [CTPHIEUXUAT];

DROP TABLE [NHACUNGCAP];

DROP TABLE [PHIEUNHAP];

DROP TABLE [CTPHIEUNHAP];

DROP TABLE [PHIEUKIEMKE];

DROP TABLE [CTPHIEUKIEMKE];

DROP TABLE [SANPHAM];

DROP TABLE [NHAXUATBAN];

DROP TABLE [KHUVUCKHO];
