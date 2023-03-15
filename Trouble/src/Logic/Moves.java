package Logic;

import World.*;

public class Moves {

    private int weight;
    private int roll;
    private Pieces[] boardstate;
    private Pieces colour;
    private Board board;
    
    

    public Moves(Board b, int r, Pieces c){
        this.board = b;
        this.boardstate = b.getBoard();
        this.roll = r;
        this.colour = c;
        
    }

    
    /**
     * finds the weight of a move for a given piece based on a dice roll & the current board 
     * @param roll from 1-6
     * @param b the board from it's current state
     * @param p the given piece
     * @return
     */
    public int findMoves(){

        if(roll != 6 && inStart())
            this.weight = -1;
        //assigns a negative weight to trying to leave the start when anything but a 6 is not rolled


        
        if(!(inStart())){
            switch (roll) {
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



    public static Moves[] findMoves(Board b, int roll){
        return new Moves[5];
    }

    public boolean inStart(){
        
        return(findMoves(board, roll) == board.getSP(colour) );
    }


}
