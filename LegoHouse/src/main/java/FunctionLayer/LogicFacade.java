package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.ProductMapper;
import entity.User;
import DBAccess.UserMapper;
import entity.LegoHouse;
import entity.LegoWall;
import entity.Legoblock;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LegoException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LegoException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }
    public static ArrayList<LegoWall> createLegoHouse(int length, int width, int height) throws LegoException {
        LegohouseCalculation lhCalc = new LegohouseCalculation();
        return lhCalc.legoCalc(length, width, height);
    }
    public static int createOrder(LegoHouse legohouse) throws SQLException, ClassNotFoundException, LegoException {
        OrderMapper om = new OrderMapper();
        return om.addOrder(legohouse);
    }
}