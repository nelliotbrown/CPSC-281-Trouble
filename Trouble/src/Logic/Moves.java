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
     * @return
     */
    public void updateWeight(){

        //assigns a negative weight to trying to leave the start when anything but a 6 is not rolled

        if(!(inHome())){
            switch (roll) {
                case 1:
                    if(!isOccupied(this.board.getSP(this.colour) + 1)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 1)){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;

                case 2:
                    if(!isOccupied(this.board.getSP(this.colour) + 2)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 2) ){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;

                case 3:
                    if(!isOccupied(this.board.getSP(this.colour) + 3)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 3) ){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;

                case 4:
                    if(!isOccupied(this.board.getSP(this.colour) + 4) && this.startPos + 4 == this.board.getEnd(colour)){

                    }else if(!isOccupied(this.board.getSP(this.colour) + 4)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 4) ){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;

                case 5:
                    if(!isOccupied(this.board.getSP(this.colour) + 5)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 5) ){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;

                case 6:
                    if(!isOccupied(this.board.getSP(this.colour) + 6)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 6) ){
                        this.weight = 2;
                    }
                    this.weight = 1;
                    break;
            }
        }else{
            this.weight = 3;

        }

    }
    /*
    WEIGHT VALUES BASED ON POSSIBLE MOVES
    5. GETTING TO END
    4. CLEARING START TILE ?????
    3. MOVING FROM HOME TO START TILE
    2. KILLING AN OPPONENT
    1. MOVE
    Illegal moves will be assigned negative weights
    */

    public void updateIllegalWeight(){
        if(this.roll != 6 && inHome())
            this.weight = -1;


    }

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

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public void setStartPos(int startPos){
        this.startPos = startPos;
    }

    public static Moves[] findMoves(Board b, int roll, Pieces c) {
        Moves[] array = new Moves[4];
        Pieces[] board = b.getBoard();
        int y = 0;
        for (int x = 0; x < board.length; x++) {
            if (board[x] == c) {
                array[y] = new Moves(b, roll, c);
                array[y].setStartPos(x);
                array[y].setEndPos((x + roll) % 28);
                array[y].updateWeight();
                y++;
            }
        }
    return array;
    }

    public boolean inHome(){
        return(this.board.getSP(this.colour) == this.board.getHome(this.colour));
    }

    public boolean isOccupied(int i){
        return(boardstate[i] != Pieces.BLACK);
    }
}