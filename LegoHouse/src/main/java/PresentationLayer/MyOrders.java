
package PresentationLayer;

import FunctionLayer.LegoException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyOrders extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoException {
                HttpSession session = request.getSession();
        session.getAttribute("legohouse");
        
         return "index";
}
}
