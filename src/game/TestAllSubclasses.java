package game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestAllSubclasses {
    Ship.ShipType[] preferOfShip = {Ship.ShipType.BattleShip, Ship.ShipType.AircraftCarrier, Ship.ShipType.Destroyer, Ship.ShipType.Submarine, Ship.ShipType.PatrolBoat };
    Ship shipAttack = new BattleShip(preferOfShip,"bat-attack" );
    Ship shipDefend = new Submarine(preferOfShip, "sub-def");
    @DisplayName("Test that the defendAttack method functions correctly given correct and incorrect input")
    @Test
    public void testDefendAttack(){
        shipAttack.nAttack = 100;
        shipDefend.defendAttack(shipAttack);
        assertEquals(0,shipDefend.nArmour);
        assertEquals(15,shipDefend.nHitPoints);
        //Test pass when attack successful
        //Test fail when attack failed
    }
}
