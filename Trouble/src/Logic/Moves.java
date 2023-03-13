package Logic;

import World.Board;

public class Moves {

    private int weight;
    private int startPos;
    private int endPos;

    
    /**
     * finds the weight of a move for a given piece based on a dice roll & the current board 
     * @param roll from 1-6
     * @param b the board from it's current state
     * @param p the given piece
     * @return
     */
    public int findMoves(Dice roll, Board b, Colour c, Home h){

        if(roll != 6 && p.inStart == true)
            this.weight = -1;


        
        if(p.inStart == false){
            switch (roll, b, p) {
                case roll == 1:
                    break;

                case roll == 2:
                    break;
                
                case roll == 3:
                    break;
                    
                case roll == 4:
                    break;
                
                case roll == 5:
                    break;
                
                case roll == 6:
                    break;    
            }
        }else{
            
        }


        return this.weight;
    } 
    /*
    PRIORITIES
    1. GETTING HOME
    2. CLEARING START TILE
    3. MOVING FROM HOME TO START TILE
    4. KILLING AN OPPONENT
    5. MOVE
    Illegal moves will be assigned negative weights
    */


    // ~~~~~~~~~~~~~~ Additions I made to get this to work ~~~~~~~~~~~~~

    public int getWeight(){
        return weight;
    }

    public int getStartPos(){
        return startPos;
    }

    public static Moves[] findMoves(Board b, int roll){
        return new Moves[5];
    }


}
