package DAO;

import DTO.ChiTietPhieuDTO;
import DTO.PhieuXuatDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuXuatDAO implements DAOinterface<PhieuXuatDTO> {
    
    public static PhieuXuatDAO getInstance(){
        return new PhieuXuatDAO();
    }

    @Override
    public int insert(PhieuXuatDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `PHIEUXUAT` (`MNV`, `MKH`, `TIEN`, `TG`, `TT`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMNV());
            pst.setInt(2, t.getMKH());
            pst.setInt(3, (int) t.getTIEN());
            pst.setTimestamp(4, t.getTG());
            pst.setInt(5, t.getTT());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuXuatDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `PHIEUXUAT` SET `MNV`=?, `MKH`=?, `TIEN`=?, `TG`=?, `TT`=? WHERE `MPX`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMNV());
            pst.setInt(2, t.getMKH());
            pst.setInt(3, (int) t.getTIEN());
            pst.setTimestamp(4, t.getTG());
            pst.setInt(5, t.getTT());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE PHIEUXUAT SET TT = 0 WHERE MPX = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    @Override
    public ArrayList<PhieuXuatDTO> selectAll() {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUXUAT ORDER BY MPX DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MP = rs.getInt("MPX");
                Timestamp TG = rs.getTimestamp("TG");
                int MKH = rs.getInt("MKH");
                int MNV = rs.getInt("MNV");
                long TIEN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                PhieuXuatDTO PHIEUXUAT = new PhieuXuatDTO(MKH, MP, MNV, TG, TIEN, TT);
                result.add(PHIEUXUAT);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public PhieuXuatDTO selectById(String t) {
        PhieuXuatDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUXUAT WHERE MPX=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MP = rs.getInt("MPX");
                Timestamp TG = rs.getTimestamp("TG");
                int MKH = rs.getInt("MKH");
                int MNV = rs.getInt("MNV");
                long TIEN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                result = new PhieuXuatDTO(MKH, MP, MNV, TG, TIEN, TT);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    public PhieuXuatDTO cancel(int phieu) {
        PhieuXuatDTO result = null;
        try {
            
            ArrayList<ChiTietPhieuDTO> chitietphieu = ChiTietPhieuXuatDAO.getInstance().selectAll(phieu+"");
            ChiTietPhieuXuatDAO.getInstance().reset(chitietphieu);
            
            deletePhieu(phieu);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public int deletePhieu(int t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `PHIEUXUAT` WHERE MPX = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<PhieuXuatDTO> selectAllofKH(int MKH) {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUXUAT WHERE MKH=? ORDER BY MPX DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, MKH);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int MP = rs.getInt("MPX");
                Timestamp TG = rs.getTimestamp("TG");
                int kh = rs.getInt("MKH");
                int MNV = rs.getInt("MNV");
                long TIEN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                PhieuXuatDTO PHIEUXUAT = new PhieuXuatDTO(kh, MP, MNV, TG, TIEN, TT);
                result.add(PHIEUXUAT);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND TABLE_NAME   = 'PHIEUXUAT'";
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
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
