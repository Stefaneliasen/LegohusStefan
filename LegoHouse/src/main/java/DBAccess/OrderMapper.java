package DBAccess;

import FunctionLayer.LegoException;
import entity.LegoHouse;
import entity.LegoWall;
import entity.Legoblock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {

    public static void main(String[] args) throws SQLException, LegoException, ClassNotFoundException {
        OrderMapper om = new OrderMapper();
        Legoblock legoblock = new Legoblock("2x4", 4);
        LegoWall legowall = new LegoWall(legoblock, 4);
        ArrayList<LegoWall> legowalls = new ArrayList();
        legowalls.add(legowall);
        LegoHouse legohouse = new LegoHouse(4, legowalls);
        om.addOrder(legohouse);

    }

    public int addOrder(LegoHouse legohouse) throws SQLException, ClassNotFoundException, LegoException {
        Connection con = Connector.connection();
        String sql = "insert into orders (User_userId) values (?);";
        try (
                PreparedStatement statement = con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);) {
            //  con.setAutoCommit(false);
            statement.setInt(1, legohouse.getUserId());
            int affectedRows = statement.executeUpdate();
            //  con.setAutoCommit(true);
            if (affectedRows == 0) {
                throw new LegoException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    legohouse.setOrderId(generatedKeys.getInt(1));
                    addOdetails(legohouse);
                } else {
                    throw new LegoException("Creating order failed, no ID obtained.");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                throw new LegoException(ex.getMessage());
            }
        }
        return legohouse.getOrderId();
    }

    public void addOdetails(LegoHouse legohouse) throws ClassNotFoundException, SQLException, LegoException {
        Connection con = Connector.connection();
        PreparedStatement order1 = null;
        String SQLString = "insert into odetails (Order_orderId, quantity, legoblockName) values (?,?,?)";
        try {
            // con.setAutoCommit(false);
            order1 = con.prepareStatement(SQLString);
            order1.setInt(1, legohouse.getOrderId());
            for (int i = 0; i < legohouse.getLegoWalls().size(); i++) {
                order1.setInt(2, legohouse.getLegoWalls().get(i).getLegoblockAmount());
                order1.setString(3, legohouse.getLegoWalls().get(i).getLegoblock().getName());

                order1.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new LegoException(ex.getMessage());
        }
    }
}
