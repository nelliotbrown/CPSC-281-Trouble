package Logic;

import World.Pieces;

public class DiceNode{

    private final int diceRoll;
    private final Pieces color;
    private int weight; // Total score for AI to decide move

    private MoveNode[] children;
    public MoveNode parent;

    /**
     * Constructor for DiceNode.
     *
     * @param diceRoll die roll given for DiceNode
     * @param parent MoveNode parent
     */
    public DiceNode(int diceRoll, MoveNode parent){
        this.diceRoll = diceRoll;
        this.parent = parent;
        this.color = nextColor(parent.getColor());
        this.children = new MoveNode[4];
    }


    /**
     * Adds MoveNode children to a DiceNode.
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

    /**
     * @param color color whose successor color will be returned
     * @return The next color in the order of turns
     */
    public static Pieces nextColor(Pieces color){

        if (color == Pieces.GREEN){
            return Pieces.BLUE;
        } else if (color == Pieces.BLUE){
            return Pieces.RED;
        } else if (color == Pieces.RED){
            return Pieces.YELLOW;
        } else if (color == Pieces.YELLOW){
            return Pieces.GREEN;
        } else {
            //TODO delete this after bug fixing
            System.out.println("THAT COLOR DOESN'T EXIST");
            return Pieces.BLACK;
        }
    }


    // ~~~~~ Setters and Getters ~~~~~

    public MoveNode getChild(int i){
        return children[i];
    }

    public int getWeight(){
        return weight;
    }

    public Pieces getColor(){
        return color;
    }


}
