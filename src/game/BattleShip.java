package game;
/**
 * Class: BattleShip is subclass of Ship
 * Author: Parker Lieu
 */
public class BattleShip extends Ship {
    /**
     * State
     */
    private String sName;

    /**
     * Constructor
     * @param sPreferredTarget
     * @param sName
     */
    public BattleShip(ShipType[] sPreferredTarget, String sName) {
        super((int) (Math.random()*50+100),300,100,25,sPreferredTarget);
        this.sName = sName;
    }

    /**
     * Function getsName : override the super abstract method
     * @return sName String
     */
    @Override
    public String getsName() {
        return sName;
    }
    @Override
    /**
     * Function toString : override to super method toString
     */
    public String toString(){
        return sName + " | " + super.toString();
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
            setDestroyedBy(obAttacker.getsName());
            System.out.println("Destroyed by " + this.destroyedBy);
            this.nHitPoints =0;
        }



    }


    /**
     * Function pickTarget: random probability for ship
     * @return ShipType
     */
    @Override
    public ShipType pickTarget(){
        //random a number from 1 to 100
        int randomPro = (int) (Math.random()*101+1);
        //in range 80%
        if (randomPro <=80) return sPreferredTarget[0];
        //in range 5% next
        else if (randomPro <=85) return sPreferredTarget[1];
        //in range 5% next
        else if (randomPro <=95) return sPreferredTarget[2];
        // other range left
        else return sPreferredTarget[3];
    }
}
