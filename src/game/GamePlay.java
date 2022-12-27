package game;

import java.util.ArrayList;



public class GamePlay {
    public static void main(String[] args) {
        Ship.ShipType[] preferOfShip = {Ship.ShipType.BattleShip, Ship.ShipType.AircraftCarrier, Ship.ShipType.Destroyer, Ship.ShipType.Submarine, Ship.ShipType.PatrolBoat };
        //Create player 1
        ArrayList<Ship> player1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            player1.add(new BattleShip(preferOfShip,"bat1-" + i));
        }
        for (int i = 1; i <=5 ; i++) {
            player1.add(new AircraftCarrier(preferOfShip,"car1-"+i,(int)(Math.random()*6+20)));
        }
        for (int i = 1; i <=5 ; i++) {
            player1.add(new Destroyer(preferOfShip,"des1-"+i));
        }
        for (int i = 1; i <=5 ; i++) {
            player1.add(new Submarine(preferOfShip,"sub1-"+i));
        }
        for (int i = 1; i <=20 ; i++) {
            player1.add(new PatrolBoat(preferOfShip,"par1-"+i));
        }
        //Create player 2
        ArrayList<Ship> player2 = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            player2.add(new BattleShip(preferOfShip,"bat2-" + i));
        }
        for (int i = 1; i <=5 ; i++) {
            player2.add(new AircraftCarrier(preferOfShip,"car2-"+i,(int)(Math.random()*6+20)));
        }
        for (int i = 1; i <=5 ; i++) {
            player2.add(new Destroyer(preferOfShip,"des2-"+i));
        }
        for (int i = 1; i <=5 ; i++) {
            player2.add(new Submarine(preferOfShip,"sub2-"+i));
        }
        for (int i = 1; i <=20 ; i++) {
            player2.add(new PatrolBoat(preferOfShip,"par2-"+i));
        }
        int counter =1;
        while (true) {
            //Start battle
            battleRound(player1,player2);
            // Reset the armour rate and repair rate
            resetArmourOrRepair(player1,player2);
            //Counter to track round
            counter++;
            System.out.println("Finish round "+ counter +"!!!");
            //Check if game isOver or not
            if (isPlayerLost(player1)||isPlayerLost(player2)) {
                break;
            }
        }

        String mvp = "";
        if (isPlayerLost(player2)){
            System.out.println("Player 1 win!!");
            mvp = findMvp(player2);
//            for (Ship ship : player2) {
//                System.out.print(ship.destroyedBy + " * ");
//            }
        }
        else if (isPlayerLost(player1)){
            System.out.println("Player 2 win!!");
            mvp = findMvp(player1);
//            for (Ship ship : player1) {
//                System.out.print(ship.destroyedBy + " * ");
//            }
        }
//        System.out.println();
        System.out.println("Total round: " + counter);
        System.out.println("MVP is: " + mvp );


    }

    /**
     * Function Check if player is lost after round
     * @param p (ArrayList<Ship>
     * @return boolean
     */
   public static boolean isPlayerLost(ArrayList<Ship> p){
        boolean isLost = false;
        //check all the hitPoint of ship is 0 or not
       for (int i = 0; i < p.size(); i++) {
           if (p.get(i).nHitPoints <= 0) {
               isLost = true;
           }
           else isLost = false;
       }
       return isLost;
   }


    /**
     * Function battleRound : each ship of player 1 attack player and vice versa
     * @param p1 ArrayList<Ship>
     * @param p2 ArrayList<Ship>
     */
   public static void battleRound(ArrayList<Ship> p1,ArrayList<Ship> p2){
       if (!isPlayerLost(p1)){
           for (int i = 0; i < p1.size(); i++) {
               //Check is this ship was destroyed before attacking
               if (!p1.get(i).isDestroyed()) {
                   int attackPos;
                   //Generate opponent ship and check if this ship was destroyed .If yes, generate the new ship
                   while (true){
                       attackPos = pickTargetFromOpponent(p1.get(i));
                       if (isPlayerLost(p1)||isPlayerLost(p2)) break;
                       if (!p2.get(attackPos).isDestroyed()) break;
                   }
                   //Display before attacking
                   System.out.println(p1.get(i) + " is attacking " + p2.get(attackPos));
                   p2.get(attackPos).defendAttack(p1.get(i));
                   //Display after attacking
                   System.out.println(p1.get(i) + " attacked " + p2.get(attackPos));
                   System.out.println("-------------------------------------------------------------------------------------");
               }
           }
       }
       if (!isPlayerLost(p2)){
           //Repeat for player 2 to attack back
           for (int i = 0; i < p2.size(); i++) {

               if (!p2.get(i).isDestroyed()) {
                   int attackPos;

                   while (true){
                       attackPos = pickTargetFromOpponent(p2.get(i));
                       if (isPlayerLost(p1)||isPlayerLost(p2)) break;
                       if (!p1.get(attackPos).isDestroyed()) break;
                   }

                   System.out.println(p2.get(i) + " is attacking " + p1.get(attackPos));
                   p1.get(attackPos).defendAttack(p2.get(i));
                   System.out.println(p2.get(i) + " attacked " + p1.get(attackPos));
                   System.out.println("-------------------------------------------------------------------------------------");

               }
           }
       }

   }

    /**
     * Function pickTargetFromOpponent: Pick random ship from opponent
     * @param s Ship
     * @return position int
     */
   public static int pickTargetFromOpponent(Ship s){
        int position =-1;
        //Create referType with probability with function pickTarget in subclass
       switch (s.pickTarget()){
           case BattleShip:
               //Chose BattleShip position of opponent form 0 to 9
               position = (int) (Math.random()*10);
               break;
           case AircraftCarrier:
               //Chose AircraftCarrier position of opponent form 10 to 14
               position = (int) (Math.random()* 5 +10);
               break;
           case Destroyer:
               //Chose Destroyer position of opponent form 15 to 19
               position = (int) (Math.random() * 5 + 15);
               break;
           case Submarine:
               //Chose Submarine position of opponent form 20 to 24
               position = (int) (Math.random() *5 +20);
               break;
           case PatrolBoat:
               //Chose PatrolBoat position of opponent form 25 to 44
               position = (int) (Math.random() *20 +25);
           default:
               break;
       }
       return position;
   }

    /**
     * Function resetArmourOrRepair: reset Armour each round and check if ship is not attacked in round do repair
     * @param p1 ArrayList<Ship>
     * @param p2 ArrayList<Ship>
     */
    public static void resetArmourOrRepair(ArrayList<Ship> p1,ArrayList<Ship> p2){
        //Loop all ships in player 1
        for (Ship ship : p1){
            //Check type of ship
            if (ship instanceof BattleShip){
                //reset Armour if is attacked
                if(ship.nArmour!=100) ship.nArmour = 100;
                //Plus repairRate if is not attacked
                else {
                    //Plus repairRate
                    ship.nHitPoints += ship.nRepairRate;
                    //Make sure hitPoint is not exceed max value
                    if (ship.nHitPoints >= 300) ship.nHitPoints =300;
                }
            }
            //Repeated with 4 types of other ship
            else if (ship instanceof AircraftCarrier){
                if(ship.nArmour!=50) ship.nArmour = 50;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 500) ship.nHitPoints =500;
                }
            }
            else if (ship instanceof Destroyer){
                if(ship.nArmour!=25) ship.nArmour = 25;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 100) ship.nHitPoints =100;
                }
            }
            else if (ship instanceof Submarine){
                if(ship.nArmour!=15) ship.nArmour = 15;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 100) ship.nHitPoints =100;
                }
            }
            else{
                if(ship.nArmour!=5) ship.nArmour = 5;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 50) ship.nHitPoints =50;
                }
            }
        }
        //Do similar for player2
        for (Ship ship : p2){
            if (ship instanceof BattleShip){
                if(ship.nArmour!=100) ship.nArmour = 100;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 300) ship.nHitPoints =300;
                }
            }
            else if (ship instanceof AircraftCarrier){
                if(ship.nArmour!=50) ship.nArmour = 50;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 500) ship.nHitPoints =500;
                }
            }
            else if (ship instanceof Destroyer){
                if(ship.nArmour!=25) ship.nArmour = 25;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 100) ship.nHitPoints =100;
                }
            }
            else if (ship instanceof Submarine){
                if(ship.nArmour!=15) ship.nArmour = 15;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 100) ship.nHitPoints =100;
                }
            }
            else{
                if(ship.nArmour!=5) ship.nArmour = 5;
                else {
                    ship.nHitPoints += ship.nRepairRate;
                    if (ship.nHitPoints >= 50) ship.nHitPoints =50;
                }
            }
        }
    }

    /**
     * Function findMvp 
     * @param s ArrayList<Ship>
     * @return mvp String
     */
    public static String findMvp(ArrayList<Ship> s) {
        int count = 1 , tempCount;
        String mvp = s.get(0).destroyedBy;
//        String temp = " ";
//        for (int i = 0; i < s.size() -1 ; i++) {
//            temp = s.get(i).destroyedBy;
//            tempCount = 0;
//            for (int j = 1; j < s.size() ; j++) {
//              if (temp.equals(s.get(j).destroyedBy)) tempCount++;
//              if (tempCount>count) {
//                  mvp = temp;
//                  count = tempCount;
//              }
//            }
//        }
        return mvp;
    }
}
