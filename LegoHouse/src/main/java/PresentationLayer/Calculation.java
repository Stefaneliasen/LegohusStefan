package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LegoException;
import entity.LegoHouse;
import entity.LegoWall;
import entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Calculation extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoException {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        if(width < 4 || length <= 0 || height <= 0){
            return "calculation";
        }
        ArrayList<LegoWall> legowalls = LogicFacade.createLegoHouse(length, width, height);
        HttpSession session = request.getSession();
        session.setAttribute("length", length);
        session.setAttribute("width", width);
        session.setAttribute("height", height);
        
        User user = (User) session.getAttribute("user");

        LegoHouse legohouse = new LegoHouse(user.getId(), legowalls);
        session.setAttribute("legohouse", legohouse);

        try {
            LogicFacade.createOrder(legohouse);
//            LogicFacade.createOdetails(legohouse);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LegoException(ex.getMessage());
        }
        return "myOrders";
    
        }
}
