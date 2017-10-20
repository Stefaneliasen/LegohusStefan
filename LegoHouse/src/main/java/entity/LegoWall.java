package entity;

public class LegoWall {

    private Legoblock legoblock;
    private int legoblockAmount;

    public LegoWall(Legoblock legoblock, int legoblockAmount) {
        this.legoblock = legoblock;
        this.legoblockAmount = legoblockAmount;
    }

    public Legoblock getLegoblock() {
        return legoblock;
    }

    public void setLegoblock(Legoblock legoblock) {
        this.legoblock = legoblock;
    }

    public int getLegoblockAmount() {
        return legoblockAmount;
    }

    public void setLegoblockAmount(int legoblockAmount) {
        this.legoblockAmount = legoblockAmount;
    }

}
