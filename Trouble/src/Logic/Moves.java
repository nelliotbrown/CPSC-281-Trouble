package Logic;

import World.*;

public class Moves implements Comparable<Moves> {

    private int weight;
    private final int roll;
    private Pieces[] boardstate;
    private Pieces colour;
    private Board board;
    private int startPos;
    private int endPos;

    public Moves(int weight){
        this.weight = 0;
        this.roll = 0;
    }

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
    //TODO killing someone from your own start doesnt put them back in home
    //TODO stop illegal moves from playesr
    public void updateWeight(){

        // If in home but do not have a 6
        if ( startPos < 0 && roll != 6 ){
            this.weight = -1;
            return;
        }

        //If in home and do have a 6
        if ( startPos < 0 ){
            this.weight = 3;
            return;
        }


        // Going to Finish
        if ( startPos < board.getSP(this.colour) &&
                endPos >= board.getSP(this.colour) ){

            this.weight = 5;
            return;
        }

        // If moving from start tile
        if( startPos == board.getSP(this.colour) ){
            this.weight = 4;
            return;
        }


        // If killing
        if ( isOccupied(endPos) ){
            this.weight = 2;
            return;
        }

        // Landing on yourself
        if ( boardstate[endPos] == this.colour ){
            this.weight = -1;
            return;
        }

        this.weight = 1;
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
            array[y].setEndPos(b.getSP(c));
            array[y].updateWeight();
            y++;
        }

        for (int x = 0; x < board.length; x++) {
            if (board[x] == c) {
                array[y] = new Moves(b, roll, c);
                array[y].setStartPos(x);
                array[y].setEndPos( (x + roll) % 28);
                array[y].updateWeight();
                y++;
            }
        }

        for (int j = y; j < 4; j++){
            array[y] = new Moves(b, roll, c);
            array[y].setStartPos(0);
            array[y].setEndPos(0);
            array[y].setWeight(-1);
            y++;
        }

        return array;
    }



    public boolean inHome(){
        return(this.board.getSP(this.colour) == this.board.getHome(this.colour));
    }

    /**
     * @param i board index
     * @return true if the board index does not contain your piece on it and does contain someone else's
     * piece
     */
    public boolean isOccupied(int i){
        return(boardstate[i % 28] != Pieces.BLACK && boardstate[i % 28] != this.colour);
    }




    // ~~~~~ Setters and Getters ~~~~~

    public int getWeight(){
        return weight;
    }

    public void setWeight(int c){
        this.weight = c;
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




    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Moves o) {
        return this.weight - o.weight;
    }

}
