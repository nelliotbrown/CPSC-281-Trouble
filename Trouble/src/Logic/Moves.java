package Logic;

public class Moves {
    private int weight;


    
    
    /**
     * finds the weight of a move for a given piece based on a dice roll & the current board 
     * @param roll from 1-6
     * @param b the board from it's current state
     * @param p the given piece
     * @return
     */
    public int findMoves(Dice roll, Board b, Piece p){
        if(roll != 6 && p.inStart == false)
            this.weight = -1;


        

        switch (roll, b, p) {
            case value:
                
                break;
        
            default:
                break;
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


    
}
