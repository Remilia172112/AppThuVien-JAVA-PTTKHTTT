package DAO;

import config.JDBCUtil;
import helper.BCrypt;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO implements DAOinterface<TaiKhoanDTO>{
    
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `TAIKHOAN` (`MNV`, `TDN`, `MK`, `MNQ`, `TT`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMNV());
            pst.setString(2, t.getTDN());
            pst.setString(3, BCrypt.hashpw(t.getMK(), BCrypt.gensalt(12)));
            pst.setInt(4, t.getMNQ());
            pst.setInt(5, t.getTT());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(TaiKhoanDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `TAIKHOAN` SET `TDN` = ?, `TT` = ?, `MNQ` = ? WHERE `MNV` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTDN());
            pst.setInt(2, t.getTT());
            pst.setInt(3, t.getMNQ());
            pst.setInt(4, t.getMNV());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateTTCXL(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE TAIKHOAN TK JOIN NHANVIEN NV ON TK.MNV = NV.MNV SET TK.TT = 2 WHERE `EMAIL` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
}
    
    
    public void updatePass(String email, String password){
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE TAIKHOAN TK JOIN NHANVIEN NV ON TK.MNV = NV.MNV SET `MK` = ? WHERE `EMAIL` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(12)));
            pst.setString(2, email);
            pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TaiKhoanDTO selectByEmail(String t) {
        TaiKhoanDTO tk = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM TAIKHOAN TK JOIN NHANVIEN NV ON TK.MNV = NV.MNV WHERE NV.EMAIL = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int MNV = rs.getInt("MNV");
                String TDN = rs.getString("TDN");
                String MK = rs.getString("MK");
                int TT = rs.getInt("TT");
                int MNQ = rs.getInt("MNQ");
                tk = new TaiKhoanDTO(MNV, TDN, MK, MNQ, TT);
                return tk;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception           
        }
        return tk;
    }
    
    public void sendOpt(String email, String opt){
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE TAIKHOAN TK JOIN NHANVIEN NV ON TK.MNV = NV.MNV SET `OTP` = ? WHERE `EMAIL` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, opt);
            pst.setString(2, email);
            pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkOtp(String email, String otp){
        boolean check = false;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM TAIKHOAN TK JOIN NHANVIEN NV ON TK.MNV = NV.MNV WHERE NV.EMAIL = ? AND TK.OTP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, otp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                check = true;
                return check;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return check;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `TAIKHOAN` SET `TT`= '-1' WHERE MNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE TT = '0' OR TT = '1' OR TT = '2'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String TDN = rs.getString("TDN");
                String MK = rs.getString("MK");
                int MNQ = rs.getInt("MNQ");
                int TT = rs.getInt("TT");
                TaiKhoanDTO tk = new TaiKhoanDTO(MNV, TDN, MK, MNQ, TT);
                result.add(tk);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public TaiKhoanDTO selectById(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM TAIKHOAN WHERE MNV = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
                int MNV = rs.getInt("MNV");
                String TDN = rs.getString("TDN");
                String MK = rs.getString("MK");
                int TT = rs.getInt("TT");
                int MNQ = rs.getInt("MNQ");
                TaiKhoanDTO tk = new TaiKhoanDTO(MNV, TDN, MK, MNQ, TT);
                result = tk;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public TaiKhoanDTO selectByUser(String t) {
        TaiKhoanDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM TAIKHOAN WHERE TDN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MNV = rs.getInt("MNV");
                String TDN = rs.getString("TDN");
                String MK = rs.getString("MK");
                int TT = rs.getInt("TT");
                int MNQ = rs.getInt("MNQ");
                TaiKhoanDTO tk = new TaiKhoanDTO(MNV, TDN, MK, MNQ, TT);
                result = tk;
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND  TABLE_NAME = 'TAIKHOAN'";
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
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
