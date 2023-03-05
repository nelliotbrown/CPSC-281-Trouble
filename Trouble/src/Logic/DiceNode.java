package Logic;

import World.Board;

public class DiceNode {

    private final int diceRoll;
    private int totalScore; // Total score for AI to decide move
    private MoveNode[] children;
    public MoveNode parent;

    /**
     *
     * @param diceRoll
     * @param parent
     */
    public DiceNode(int diceRoll, MoveNode parent){
        this.diceRoll = diceRoll;
        this.parent = parent;
    }


    /**
     * Makes moveNode children that have each piece moved f
     */
    public void makeChildren() {

        Moves[] moves = Moves.findMoves( parent.getBoard(), diceRoll );

        children = new MoveNode[4];

        for(int i = 0; i < 4; i++){
            children[i] = new MoveNode( moves[i], parent.getBoard(), this);
        }
    }


    public void updateTotalScore() {

        totalScore = 0;

        for(int i = 0; i < 6; i++){
            totalScore += children[i].getScore();
        }
    }


    // ~~~~~ Setters and Getters ~~~~~

    /**
     * Returns child i
     *
     * @param i
     * @return
     */
    public MoveNode getChild(int i){
        return children[i];
    }


}
