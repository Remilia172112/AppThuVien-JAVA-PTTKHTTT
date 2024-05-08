package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ChiTietGioHangDTO;
import config.JDBCUtil;

public class ChiTietGioHangDAO implements ChiTietInterface{

    public static ChiTietGioHangDAO getInstance(){
        return new ChiTietGioHangDAO();
    }


    @Override
    public int insert(ArrayList<ChiTietGioHangDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTGIOHANG` (`MKH`, `MSP`, `SL`,  `TIENGIO`) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMKH());
                pst.setInt(2, t.get(i).getMSP());
                int SL = -(t.get(i).getSL());
                pst.setInt(3, t.get(i).getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), SL);
                pst.setInt(4, t.get(i).getTIENGIO());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int update(ArrayList t, String pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList selectAll(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }
    
}
