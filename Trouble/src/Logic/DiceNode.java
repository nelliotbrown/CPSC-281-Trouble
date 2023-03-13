package Logic;

public class DiceNode{

    private final int diceRoll;
    private int weight; // Total score for AI to decide move
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

    /**
     * Updates weight of dice node. Weight is set to max (or min) of the children Nodes
     *
     * @param isAdding if true adds weight to dice Node, else subtracts
     */
    public void updateWeight(boolean isAdding) {

        weight = children[0].getWeight();

        for (MoveNode child : children) {

            if (isAdding) {
                weight = Math.max(weight, child.getWeight());
            } else {
                weight = Math.min(weight, child.getWeight());
            }
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


    public int getWeight(){
        return weight;
    }


}
