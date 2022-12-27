package game;
/**
 * Class: BattleShip is subclass of Ship
 * Author: Parker Lieu
 */
public class AircraftCarrier extends Ship{
    /**
     * State
     */
    private String sName;
    private int planes;

    /**
     * Constructor
     * @param sPreferredTarget
     * @param sName
     * @param planes
     */
    public AircraftCarrier(ShipType[] sPreferredTarget, String sName, int planes) {
        super(10*planes,500,50,25,sPreferredTarget);
        this.sName = sName;
        this.planes = planes;
    }
    @Override
    /**
     * Function getsName : override the super abstract method
     * @return sName String
     */
    public String getsName() {
        return sName;
    }

    @Override
    /**
     * Function toString : override to super method toString
     */
    public String toString(){
        return sName + " | "  + super.toString();
    }
    @Override
    /**
     * Function defendAttack: use to implement when attack and tracking battle
     * @param obAttacker ship
     */
    public void defendAttack(Ship obAttacker){
        //Check attack hit or miss with function isMiss in super class
        if (!super.isMissed()) {
            //Check if nAttack of opponent is larger than nArmour of this ship
            if(obAttacker.nAttack - this.nArmour > 0){
                //Reduce Armour then reduce hitPoint with the attackValue
                int attackValue = obAttacker.nAttack - this.nArmour;
                this.nArmour = 0;
                this.nHitPoints = this.nHitPoints - attackValue;
            }
            else{
                //If no Armour
                this.nArmour = this.nArmour - obAttacker.nAttack;

            }
            System.out.println("Attack  is successful !!!");
        }
        else {
            //Case miss in attacking
            System.out.println("you are Missed !!!");
        }

        // set destroyedBy when this ship is Destroy
        if (this.isDestroyed()) {
            super.setDestroyedBy(obAttacker.getsName());
            System.out.println("Destroyed by " + super.destroyedBy);
            this.nHitPoints =0;
        }



    }

    @Override
    /**
     * Function pickTarget: random probability for ship
     * @return ShipType
     */
    public ShipType pickTarget(){
        //random a number from 1 to 100
        int randomPro = (int) (Math.random()*101+1);
        //in range 10%
        if (randomPro <=10) return sPreferredTarget[0];
            //in range 10% next
        else if ( randomPro <=20 ) return sPreferredTarget[2];
            //in range 40% next
        else if ( randomPro <=60 ) return sPreferredTarget[3];
            //in other range left
        else return sPreferredTarget[4];
    }
}
