package Logic;

import World.Board;
import World.Pieces;

public class Tree {

    private MoveNode rootNode;

    /**
     * Initializes a decision tree. Set up for green to start the game.
     *
     * @param layers Number of layers in the tree. Each layer is made up of a row of dice nodes and move nodes
     *               for one player. Needs a minimum value of 1.
     */
    public Tree(int layers) {

        rootNode = new MoveNode(new Board());
        rootNode.setInitialBoardMove();

        for(int i = 0; i <= layers; i++){
            this.traverseAndAddLayer(rootNode);
        }

        this.traverseAndUpdateWeights(rootNode);
    }

    /**
     * Adds a layer of dice Nodes and move Nodes below node.
     *
     * @param node Node that layers are added beneath, Node must be a leaf node
     */
    private void addNodes(MoveNode node) {
        node.makeChildren(); // Adds dice Nodes
        for(int i = 0; i < 6; i++){ //Adds move Nodes below the Dice Nodes
            node.getChild(i).makeChildren();
        }
    }

    /**
     * Adds a layer of nodes to all leaf nodes beneath the input node.
     *
     * @param node Node described as above. Should input the root node unless doing something crazy
     */
    private void traverseAndAddLayer(MoveNode node) {

        MoveNode current = node;

        if (current.getChild(0) != null) {
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                    traverseAndAddLayer(current.getChild(i).getChild(j));
                }
            }
        } else {
            addNodes(current);
        }

    }

    /**
     * Updates the weights of all nodes beneath the input Node. Designed to work with rootNode, will set up the
     * weights for the next player's turn.
     *
     * @param node Please put root node in here.
     */
    private void traverseAndUpdateWeights(MoveNode node) {

        Pieces color = DiceNode.nextColor(rootNode.getColor());
        MoveNode current = node;

        if (current.getChild(0) != null) { //Recursively travel down the tree

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                    traverseAndUpdateWeights(current.getChild(i).getChild(j));
                }

                // If dice node is your color add weight, else subtract
                current.getChild(i).updateWeight(color == current.getChild(i).getColor());
            }

            current.updateWeight( color == current.getColor() );//Update the move Node
        }

//        System.out.print(current.getWeight());
    }


    /**
     * AI will choose the move with the highest weight, given a die roll. Sets the chosen MoveNode to the root
     * of the tree, adds a layer of nodes to the tree, updates the weights for the next player.
     *
     * @param diceRoll dice roll for the turn
     */
    public void aiChooseMove(int diceRoll){
        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            System.out.println("Option " + i + ", weight: " + rootNode.getChild(diceRoll - 1).getChild(i).getWeight()
                    + ", starting position: " + rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() );

            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() >
                    rootNode.getChild(diceRoll - 1).getChild(moveChoice).getWeight()){
                moveChoice = i;
            }
        }


        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        System.out.println("Chose " + moveChoice + ", Move Weight: " + rootNode.getMoves().getWeight());
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode);
    }


    /**
     * Player chooses a move given a die roll and starting position of the move. Sets the chosen MoveNode to the root
     * of the tree, adds a layer of nodes to the tree, updates the weights for the next player.
     *
     * @param diceRoll dice roll for the turn
     * @param startPos starting position of piece that will be moved
     */
    public void playerChooseMove(int diceRoll, int startPos, Pieces c) {
        int moveChoice = -1;
        int fix = rootNode.getBoard().getEnd(c);

        for (int i = 0; i < 4; i++) {
            if (rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos) {
                moveChoice = i;
                System.out.println(moveChoice);
            }
        }

        if(moveChoice == -1){
            System.out.println("YOU IDIOT YOU COULDN'T MOVE THAT PIECE");
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        System.out.println("Indiv. move weight: " + rootNode.getMoves().getWeight());
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode);
    }

    /**
     * @return rootNode of the tree
     */
    public MoveNode getRootNode(){
        return rootNode;
    }

}