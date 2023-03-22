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
            if(startPos < 0){
                this.weight = 3;
                return;
            }

            switch (roll) {
                case 1:
                if(!isOccupied(this.board.getSP(this.colour) + 1) && this.startPos + 1 == this.board.getEnd(colour)){
                    this.weight = 5;
                }else if(!isOccupied(this.board.getSP(this.colour) + 1)){
                    this.weight = 4;
                }else if(isOccupied(this.startPos + 1)){
                    this.weight = 2;
                }else{
                    this.weight = 1;
                }
                break;

                case 2:
                if(!isOccupied(this.board.getSP(this.colour) + 2) && this.startPos + 2 == this.board.getEnd(colour)){
                    this.weight = 5;
                }else if(!isOccupied(this.board.getSP(this.colour) + 2)){
                    this.weight = 4;
                }else if(isOccupied(this.startPos + 2)){
                    this.weight = 2;
                }else{
                    this.weight = 1;
                }
                break;

                case 3:
                if(!isOccupied(this.board.getSP(this.colour) + 3) && this.startPos + 3 == this.board.getEnd(colour)){
                    this.weight = 5;
                }else if(!isOccupied(this.board.getSP(this.colour) + 3)){
                    this.weight = 4;
                }else if(isOccupied(this.startPos + 3)){
                    this.weight = 2;
                }else{
                    this.weight = 1;
                }
                break;

                case 4:
                    if(!isOccupied(this.board.getSP(this.colour) + 4) && this.startPos + 4 == this.board.getEnd(colour)){
                        this.weight = 5;
                    }else if(!isOccupied(this.board.getSP(this.colour) + 4)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 4) ){
                        this.weight = 2;
                    }else{
                    this.weight = 1;
                    }
                    break;

                case 5:
                    if(!isOccupied(this.board.getSP(this.colour) + 5) && this.startPos + 5 == this.board.getEnd(colour)){
                        this.weight = 5;
                    }else if(!isOccupied(this.board.getSP(this.colour) + 5)){
                        this.weight = 4;
                    }else if(isOccupied(this.startPos + 5)){
                        this.weight = 2;
                    }else{
                    this.weight = 1;
                    }
                    break;

                case 6:
                if(!isOccupied(this.board.getSP(this.colour) + 6) && this.startPos + 6 == this.board.getEnd(colour)){
                    this.weight = 5;
                }else if(!isOccupied(this.board.getSP(this.colour) + 6)){
                    this.weight = 4;
                }else if(isOccupied(this.startPos + 6)){
                    this.weight = 2;
                }else{
                this.weight = 1;
                }
                break;
            }
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
        return this.startPos;
    }
    public int getEndPos(){
        return this.endPos;
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
        int piecesInStart = b.getHome(c);

        // Working with liams goofy convention (he sucks ngl)
        int z = -1;
        switch (c) {
            case GREEN:
                break;
            case BLUE:
                z = -2;
                break;
            case RED:
                z = -3;
                break;
            case YELLOW:
                z = -4;
                break;
        }

        for (int i = 0; i < piecesInStart; i++){
            array[y] = new Moves(b, roll, c);
            array[y].setStartPos(z);
            array[y].setEndPos(roll);
            array[y].updateWeight();
            y++;
        }

        for (int x = 0; x < board.length; x++) {
            if (board[x] == c) {
                array[y] = new Moves(b, roll, c);
                array[y].setStartPos(x);
                array[y].setEndPos(x + roll % 28);
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
