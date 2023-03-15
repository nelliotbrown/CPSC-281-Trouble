package Logic;

import World.Board;

public class MoveNode {

    private final Board board; // Node's copy of the board
    private final Moves moves;
    private int weight; // Score from Move object

    private DiceNode[] children;
    private DiceNode parent;



    /**
     * Used for intializing tree
     *
     * @param board initial position board
     */
    public MoveNode(Board board) {
        this.board = board;
        this.moves = null;
        this.weight = 0;
        children = new DiceNode[6];
    }



    /**
     * @param move Moves object (returned from findMoves)
     * @param board Board object of grandparent, acted on by move.
     */
    public MoveNode(Moves move, Board board, DiceNode parent) {
        this.moves = move;
        this.board = board.moveBoard(move);
        this.weight = move.getWeight();
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


    /**
     * Updates weight of Node. Sets weight to the average of Dice Node weights since
     * it is probabilistic.
     *
     */
    public void updateWeight(){

        int newWeight = 0;

        for (DiceNode child : children) {
            newWeight += child.getWeight();
        }

        weight = newWeight/children.length;
    }


    // ~~~~~ Setters and Getters ~~~~~

    public int getWeight(){
        return weight;
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

    public int getMoveStartingPos(){
        return this.moves.getStartPos();
    }


}
