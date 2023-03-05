package Logic;

import World.Board;

public class MoveNode {

    private Board board; // Node's copy of the board
    private int score; // Score from Move object

    private DiceNode[] children;
    private DiceNode parent;



    /**
     * Used for intializing tree
     *
     * @param board initial position board
     */
    public MoveNode(Board board) {
        this.board = board;
        this.score = 0;
        children = new DiceNode[6];
    }


    /**
     * @param move Moves object (returned from findMoves)
     * @param board Board object of grandparent, acted on by move.
     */
    public MoveNode(Moves move, Board board, DiceNode parent) {
        this.board = board.moveBoard(move);
        this.score = move.getScore();
        this.parent = parent;
        children = new DiceNode[6];
    }

    /**
     * Fills out children with dice nodes with values 1-6.
     */
    public void makeChildren() {

        for(int i = 0; i < 6; i++){
            children[i] = new DiceNode(i + 1, this);
        }

    }


    // ~~~~~ Setters and Getters ~~~~~

    public int getScore(){
        return score;
    }


    public Board getBoard() {
        return board;
    }

    public void setParent(DiceNode parent) {
        this.parent = parent;
    }

    /**
     * Returns child i
     *
     * @param i
     * @return
     */
    public DiceNode getChild(int i){
        return children[i];
    }


}
