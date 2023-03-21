package Logic;

import World.*;

public class Moves {

    private int weight;
    private final int roll;
    private Pieces[] boardstate;
    private Pieces colour;
    private Board board;
    private int startPos;
    private int endPos;



    public Moves(Board b, int r, Pieces c){
        this.board = b;
        this.boardstate = b.getBoard();
        this.roll = r;
        this.colour = c;

    }


    /**
     * updates the weight of a move for a given piece based on a dice roll & the current board
     * still missing cases where the weight is 1, 2, 3
     * @return
     */
    public void updateWeight(){

        if(roll != 6 && inStart())
            this.weight = -1;
        //assigns a negative weight to trying to leave the start when anything but a 6 is not rolled

        if(!(inStart())){
            switch (roll) {
                case 1:
                    if(isOccupied(this.startPos + 1)){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;

                case 2:
                    if(isOccupied(this.startPos + 2) ){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;

                case 3:
                    if(isOccupied(this.startPos + 3) ){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;

                case 4:
                    if(isOccupied(this.startPos + 4) ){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;

                case 5:
                    if(isOccupied(this.startPos + 5) ){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;

                case 6:
                    if(isOccupied(this.startPos + 6) ){
                        this.weight = 4;
                    }
                    this.weight = 5;
                    break;
            }
        }else{
            this.weight = 3;

        }

    }
    /*
    WEIGHT VALUES BASED ON POSSIBLE MOVES
    1. GETTING HOME ?????
    2. CLEARING START TILE ?????
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
    public int getEndPos(){
        return endPos;
    }



    public static Moves[] findMoves(Board b, int roll){
        return new Moves[5];
    }

    public boolean inStart(){
        return(board.getSP(this.colour) == this.startPos);
    }

    public boolean isOccupied(int i){
        return(boardstate[i] != Pieces.BLACK);

    }



}