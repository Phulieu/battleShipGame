package game;

/**
 * Class: Ship
 * Author: Parker Lieu
 */
public abstract class Ship {
    /**
     * State
     */
    protected int nAttack;
    protected int nHitPoints;
    protected int nArmour;
    protected int nRepairRate;
    protected String destroyedBy;
    public enum ShipType {BattleShip,AircraftCarrier,Destroyer,Submarine,PatrolBoat};
    public ShipType[] sPreferredTarget;

    /**
     * Constructor
     * @param nAttack int
     * @param nHitPoints int
     * @param nArmour int
     * @param nRepairRate int
     * @param sPreferredTarget ShipType[]
     */
    public Ship(int nAttack, int nHitPoints, int nArmour, int nRepairRate, ShipType[] sPreferredTarget) {
        this.nAttack = nAttack;
        this.nHitPoints = nHitPoints;
        this.nArmour = nArmour;
        this.nRepairRate = nRepairRate;
        this.sPreferredTarget = sPreferredTarget;
    }

    /**
     * Constructor
     * @param sPreferredTarget ShipType[]
     */
    public Ship(ShipType[] sPreferredTarget) {
        this.sPreferredTarget = sPreferredTarget;
    }

    /**
     * Function getAttack
     * @return nAttack int
     */
    public int getAttack() {
        return nAttack;
    }
    /**
     * Function getArmour
     * @return nArmour int
     */
    public int getArmour() {
        return nArmour;
    }

    /**
     * Function setDestroyedBy
     * @param destroyedBy
     */
    public void setDestroyedBy(String destroyedBy) {
        this.destroyedBy = destroyedBy;
    }

    /**
     * Function isDestroyed : check if this ship is destroyed yet
     * @return boolean
     */
    public boolean isDestroyed() {
        if (nHitPoints <= 0) return true;
        else return false;
    }

    /**
     * Function wasDestroyedBy
     * @param shipName
     */
    public void wasDestroyedBy(String shipName) {
        destroyedBy = shipName;
    }

    /**
     * Function repair
     */
    public void repair() {
        nHitPoints = nHitPoints + nRepairRate;
    }
    @Override
    /**
     * Function toString : override to Object method toString
     */
    public String toString() {
        return nAttack + " | " + nHitPoints + " | " + nArmour + " | " + nRepairRate + " | " + destroyedBy;
    }

    /**
     * Abstract function
     * @param obAttacker defendAttack
     */
    public abstract void defendAttack(Ship obAttacker);

    //helper method

    /**
     * Helper function isMissed : random 70% probability if hit or miss
     * @return
     */
    public boolean isMissed(){
        int randomPro = (int) (Math.random()*101+1);
        if (randomPro >= 70) return true;
        else return false;
    }

    /**
     *Abstract function pickTarget to random referType with probability table
     */
    public abstract ShipType pickTarget();

    /**
     * Abstract function getName of each ship
     */
    public abstract String getsName();
}
