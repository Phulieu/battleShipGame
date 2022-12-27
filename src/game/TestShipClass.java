package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestShipClass {
    Ship.ShipType[] preferOfShip = {Ship.ShipType.BattleShip, Ship.ShipType.AircraftCarrier, Ship.ShipType.Destroyer, Ship.ShipType.Submarine, Ship.ShipType.PatrolBoat };
    Ship ship1 = new BattleShip(preferOfShip,"bat-test" );
    String oppo = "Opponent";


    @DisplayName("Test that the getAttack method functions correctly given correct and incorrect input")
    @Test
    public void  testGetAttack(){
        assertEquals(100, ship1.getAttack());
    }
    //fail for random generate attack
    @DisplayName("Test that the getArmour method functions correctly given correct and incorrect input")
    @Test
    public void  testGetArmour(){
        assertEquals(100, ship1.getArmour());
    }
    @DisplayName("Test that the isDestroyed method functions correctly given correct and incorrect input")
    @Test
    public void testIsDestroyed(){
        assertFalse(ship1.isDestroyed());
    }
    @DisplayName("Test that the repair method functions correctly given correct and incorrect input")
    @Test
    public  void testRepair(){
        ship1.repair();
        assertEquals(325,ship1.nHitPoints);
    }


}
