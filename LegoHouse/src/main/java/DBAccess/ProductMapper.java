package DBAccess;

import FunctionLayer.LegoException;
import entity.Legoblock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductMapper {

    public static void main(String[] args) throws LegoException {
        ProductMapper pm = new ProductMapper();
        System.out.println(pm.getLegoblockbyName("2x4").getLength());
        System.out.println(pm.getLegoblockbyName("2x4").getName());
    }

    public Legoblock getLegoblockbyName(String legoblockName) throws LegoException {
        Legoblock legoblock = null;
        try {

            Connection con = Connector.connection();
            String SQL = "SELECT legoblockName, length FROM legoblock where legoblockName=?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, legoblockName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                legoblock = new Legoblock(legoblockName, rs.getInt("length"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegoException(ex.getMessage());

        } finally {
            return legoblock;
        }
    }

//     public ArrayList<Legoblock> getLegoblockbyName() throws ProductException {
//        Connection con;
//         try {
//             con = Connector.connection();
//         
//         
//        ResultSet rs = null;
//        PreparedStatement stmt = null;
//        String SQLString
//                = "select * from legoblock";
//
//        Legoblock legoblock = null;
//        ArrayList<Legoblock> legoblocks = new ArrayList<Legoblock>();
//
//     
//            stmt = con.prepareStatement(SQLString);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//            legoblock = new Legoblock(rs.getString("legoblockName"), rs.getInt("length"));
//                legoblocks.add(legoblock);
//            }
//         
//        return legoblocks;
//          } catch ( SQLException | ClassNotFoundException ex ) {
//            throw new ProductException( ex.getMessage() );
//        }
//    }
}
