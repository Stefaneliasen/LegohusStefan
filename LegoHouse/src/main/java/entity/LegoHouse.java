
package entity;

import java.util.ArrayList;
import java.util.List;

public class LegoHouse {
    private int userId;
    private int orderId;
    private List<LegoWall> legoWalls = new ArrayList();

    public LegoHouse(int userId, ArrayList<LegoWall> legoWalls) {
        this.userId = userId;
        this.legoWalls = legoWalls;
    }
    
    public LegoHouse(){
    
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<LegoWall> getLegoWalls() {
        return legoWalls;
    }

    public void setLegoWalls(List<LegoWall> legoWalls) {
        this.legoWalls = legoWalls;
    }

}
