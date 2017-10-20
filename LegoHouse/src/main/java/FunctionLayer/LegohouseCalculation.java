package FunctionLayer;

import DBAccess.ProductMapper;
import entity.LegoWall;
import entity.Legoblock;
import java.util.ArrayList;

public class LegohouseCalculation {

    public static void main(String[] args) throws LegoException {
        LegohouseCalculation legoCalc = new LegohouseCalculation();
        int length = 21;
        int width = 21;
        int height = 2;

        for (LegoWall legowalls : legoCalc.legoCalc(length, width, height)) {
            System.out.println(legoCalc.legoCalc(length, width, height));
        }
    }
    ProductMapper pm = new ProductMapper();

    public ArrayList<LegoWall> legoCalc(int length, int width, int height) throws LegoException {

        ArrayList<LegoWall> legoWalls = new ArrayList();
        Legoblock block2x1 = new Legoblock("2x1", pm.getLegoblockbyName("2x1").getLength());
        Legoblock block2x2 = new Legoblock("2x2", pm.getLegoblockbyName("2x2").getLength());
        Legoblock block2x4 = new Legoblock("2x4", pm.getLegoblockbyName("2x4").getLength());

        //Amount of 2x1 blocks used to cover the length of the legohouse
        LegoWall wallLength2x1 = new LegoWall(block2x1, (length % block2x4.getLength()) % block2x2.getLength() / block2x1.getLength());
        //Amount of 2x1 blocks used to cover the width of the legohouse
        LegoWall wallWidth2x1 = new LegoWall(block2x1, (width - 4) % block2x4.getLength() % block2x2.getLength() / block2x1.getLength());
        //Amount of 2x2 blocks used to cover the length of the legohouse
        LegoWall wallLength2x2 = new LegoWall(block2x2, (length - wallLength2x1.getLegoblockAmount()) % block2x4.getLength() / block2x2.getLength());
        //Amount of 2x2 blocks used to cover the width of the legohouse
        LegoWall wallWidth2x2 = new LegoWall(block2x2, ((width - 4) - wallWidth2x1.getLegoblockAmount()) % block2x4.getLength() / block2x2.getLength());
        //Amount of 2x4 blocks used to cover the length of the legohouse
        LegoWall wallLength2x4 = new LegoWall(block2x4, (length - wallLength2x1.getLegoblockAmount() - wallLength2x2.getLegoblockAmount() * 2) / block2x4.getLength());
        //Amount of 2x4 blocks used to cover the width of the legohouse
        LegoWall wallWidth2x4 = new LegoWall(block2x4, ((width - 4) - wallWidth2x1.getLegoblockAmount() - wallWidth2x2.getLegoblockAmount() * 2) / block2x4.getLength());

        //Ganges med 2 for, at få antalbrikker for begge sider og ganges med højde derefter.
        int amountOfBlocks2x1 = ((wallLength2x1.getLegoblockAmount() * 2) * height) + ((wallWidth2x1.getLegoblockAmount() * 2) * height);
        wallLength2x1.setLegoblockAmount(amountOfBlocks2x1);
        legoWalls.add(wallLength2x1);
        int amountOfBlocks2x2 = ((wallLength2x2.getLegoblockAmount() * 2) * height) + ((wallWidth2x2.getLegoblockAmount() * 2) * height);
        wallLength2x2.setLegoblockAmount(amountOfBlocks2x2);
        legoWalls.add(wallLength2x2);
        int amountOfBlocks2x4 = ((wallLength2x4.getLegoblockAmount() * 2) * height) + ((wallWidth2x4.getLegoblockAmount() * 2) * height);
        wallLength2x4.setLegoblockAmount(amountOfBlocks2x4);
        legoWalls.add(wallLength2x4);
        return legoWalls;
    }
}
