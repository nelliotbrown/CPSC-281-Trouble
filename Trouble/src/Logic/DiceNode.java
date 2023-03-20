package Logic;

import World.Pieces;

public class DiceNode{

    private final int diceRoll;
    private final Pieces color;
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
        this.color = nextColor(parent.getColor());
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

    /**
     *
     */
    private static Pieces nextColor(Pieces color){

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
            System.out.println("THAT COLOR DOESN'T EXIST HOMIE");
        }

        return Pieces.BLACK;
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

    public Pieces getColor(){
        return color;
    }


}
