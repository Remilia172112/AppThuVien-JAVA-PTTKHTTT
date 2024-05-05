package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.NhanVienDTO;

public class NhanVienDAO implements DAOinterface<NhanVienDTO>{
    public static NhanVienDAO getInstance(){
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVienDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `NHANVIEN`(`HOTEN`, `GIOITINH`,`SDT`,`NGAYSINH`,`TT`,`EMAIL`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHOTEN());
            pst.setInt(2, t.getGIOITINH());
            pst.setString(3, t.getSDT());
            pst.setDate(4, (Date) (t.getNGAYSINH()));
            pst.setInt(5, t.getTT());
            pst.setString(6, t.getEMAIL());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhanVienDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `NHANVIEN` SET `HOTEN` = ?,`GIOITINH` = ?,`NGAYSINH` = ?,`SDT` = ?, `TT` = ?, `EMAIL` = ?  WHERE `MNV` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHOTEN());
            pst.setInt(2, t.getGIOITINH());
            pst.setDate(3, (Date) t.getNGAYSINH());
            pst.setString(4, t.getSDT());
            pst.setInt(5, t.getTT());
            pst.setString(6, t.getEMAIL());
            pst.setInt(7, t.getMNV());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE NHANVIEN SET `TT` = -1 WHERE MNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM NHANVIEN WHERE `TT` = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
                int MNV = rs.getInt("MNV");
                String HOTEN = rs.getString("HOTEN");
                int GIOITINH = rs.getInt("GIOITINH");
                Date NGAYSINH = rs.getDate("NGAYSINH");
                String SDT = rs.getString("SDT");
                int TT = rs.getInt("TT");
                String EMAIL = rs.getString("EMAIL");
                NhanVienDTO nv = new NhanVienDTO(MNV, HOTEN, GIOITINH, NGAYSINH, SDT, TT, EMAIL);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<NhanVienDTO> selectAlll() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM NHANVIEN";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String HOTEN = rs.getString("HOTEN");
                int GIOITINH = rs.getInt("GIOITINH");
                Date NGAYSINH = rs.getDate("NGAYSINH");
                String SDT = rs.getString("SDT");
                int TT = rs.getInt("TT");
                String EMAIL = rs.getString("EMAIL");
                NhanVienDTO nv = new NhanVienDTO(MNV,HOTEN,GIOITINH,NGAYSINH,SDT,TT,EMAIL);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<NhanVienDTO> selectAllNV() {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM NHANVIEN nv where nv.TT = 1 and not EXISTS(SELECT * FROM taikhoan tk WHERE nv.MNV=tk.MNV)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String HOTEN = rs.getString("HOTEN");
                int GIOITINH = rs.getInt("GIOITINH");
                Date NGAYSINH = rs.getDate("NGAYSINH");
                String SDT = rs.getString("SDT");
                int TT = rs.getInt("TT");
                String EMAIL = rs.getString("EMAIL");
                NhanVienDTO nv = new NhanVienDTO(MNV,HOTEN,GIOITINH,NGAYSINH,SDT,TT,EMAIL);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    

    @Override
    public NhanVienDTO selectById(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM NHANVIEN WHERE MNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String HOTEN = rs.getString("HOTEN");
                int GIOITINH = rs.getInt("GIOITINH");
                Date NGAYSINH = rs.getDate("NGAYSINH");
                String SDT = rs.getString("SDT");
                int TT = rs.getInt("TT");
                String EMAIL = rs.getString("EMAIL");
                result = new NhanVienDTO(MNV,HOTEN,GIOITINH,NGAYSINH,SDT,TT,EMAIL);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public NhanVienDTO selectByEmail(String t) {
        NhanVienDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM NHANVIEN WHERE EMAIL = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String HOTEN = rs.getString("HOTEN");
                int GIOITINH = rs.getInt("GIOITINH");
                Date NGAYSINH = rs.getDate("NGAYSINH");
                String SDT = rs.getString("SDT");
                int TT = rs.getInt("TT");
                String EMAIL = rs.getString("EMAIL");
                result = new NhanVienDTO(MNV,HOTEN,GIOITINH,NGAYSINH,SDT,TT,EMAIL);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND   TABLE_NAME   = 'NHANVIEN'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
