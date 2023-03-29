package Logic;

import World.Board;
import World.Pieces;


public class MoveNode {

    private final Board board; // Node's copy of the board
    private final Pieces color;
    private int weight; // Score from Move object
    private  Moves moves;
    private final DiceNode[] children;
    private DiceNode parent;


    /**
     * Used for initializing tree
     *
     * @param board initial position board
     */
    public MoveNode(Board board) {
        this.board = board;
        this.moves = null;
        this.weight = 0;
        this.parent = null;
        this.color = Pieces.YELLOW;
        children = new DiceNode[6];
    }


    /**
     * Constructor for MoveNode
     *
     * @param move Moves object (returned from findMoves)
     * @param board Board object of grandparent, acted on by move.
     */
    public MoveNode(Moves move, Board board, DiceNode parent) {
        this.moves = move;
        this.board = new Board(board, move);
        this.weight = move.getWeight();
        this.parent = parent;
        this.color = parent.getColor();
        children = new DiceNode[6];
    }

    /**
     * Fills out MoveNode's children with DiceNodes (values 1-6).
     */
    public void makeChildren() {
        for(int i = 0; i < 6; i++){
            children[i] = new DiceNode(i + 1, this);
        }
    }


    /**
     * Updates weight of Node. Sets weight to the average of Dice Node weights since
     * it is probabilistic.
     */
    public void updateWeight( boolean isAdding ){
        int newWeight = getChild(0).getWeight();

        for (DiceNode child : children) {
            newWeight += child.getWeight();
        }

        if(isAdding){
            weight = newWeight + moves.getWeight();
        } else {
            weight = newWeight - moves.getWeight();
        }

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

    public DiceNode getChild(int i){
        return children[i];
    }

    public int getMoveStartingPos(){
        return this.moves.getStartPos();
    }

    public Moves getMoves(){
        return this.moves;
    }

    public Pieces getColor(){
        return this.color;
    }

    public DiceNode getParent() {
        return parent;
    }

    public void setInitialBoardMove(){
        this.moves = new Moves(0);
    }
}
