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

        for(int i = 0; i <= layers; i++){
            this.traverseAndAddLayer(rootNode);
        }
    }

    /**
     * Adds a layer of dice Nodes and move Nodes below node.
     *
     * @param node Node that layers are added beneath, Node must be a leaf node
     */
    private void addNodes(MoveNode node) {
        node.makeChildren(); // Adds dice Nodes

        node.makeChildren(); // Adds dice Nodes

        for(int i = 0; i < 6; i++){ //Adds move Nodes below the Dice Nodes
            node.getChild(i).makeChildren();
        }
    }

    /**
     * Adds layers to the botoom of the tra
     */
    private void traverseAndAddLayer(){
        traverseAndAddLayer(rootNode);
    }

    /**
     * Adds layers to the botoom of the tra
     */
    private void traverseAndAddLayer(){
        traverseAndAddLayer(rootNode);
    }


    /**
     * Adds "layers" to bottom of board
     *
     * @param node Node described as above. Should input the root node unless doing something crazy
     */
    private void traverseAndAddLayer(MoveNode node) {

        MoveNode current = node;

        if (current.getChild(0) != null) {
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                    current = current.getChild(i).getChild(j);
                    traverseAndAddLayer(current);
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
                    current = current.getChild(i).getChild(j);
                    traverseAndUpdateWeights(current, isAdding);
                }
                current.getChild(i).updateWeight(isAdding); //Update the dice Node
            }

            current.updateWeight(); //Update the move Node
        }
    }



    /**
     * the ai chooses the moveNode with the highest score,
     * sets rootNode to
     *
     * @param diceRoll
     */
    public void aiChooseMove(int diceRoll){

        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

    }


    public void playerChooseMove(int diceRoll, int startPos){

        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

        //TODO: Maybe replace isAdding with depth ???

        MoveNode current = node;

        if (current.getChild(0) != null) { //Recursively travel down the tree

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                    current = current.getChild(i).getChild(j);
                    traverseAndUpdateWeights(current);
                }
//                current.getChild(i).updateWeight( true); //Update the dice Node
                DiceNode child = current.getChild(i);
                child.updateWeight( (color == child.getColor()) ); // If dice node is your color add weight,
            }
            current.updateWeight(); //Update the move Node
        }
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
            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

    }


    public void playerChooseMove(int diceRoll, int startPos){

        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

        //TODO: Maybe replace isAdding with depth ???

        MoveNode current = node;

        if (current.getChild(0) != null) { //Recursively travel down the tree

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                    current = current.getChild(i).getChild(j);
                    traverseAndUpdateWeights(current, isAdding);
                }
                current.getChild(i).updateWeight(isAdding); //Update the dice Node
            }

            current.updateWeight(); //Update the move Node
        }
    }

    /**
     *
     *
     * @param node
     * @param isAdding true to add score, false to subtract
     */
    public void traverseAndUpdateWeights(MoveNode node, boolean isAdding) {

        //TODO: Maybe replace isAdding with depth ???

        MoveNode current = node;

        if (current.getChild(0) != null) { //Recursively travel down the tree

            for(int i = 0; i < 6; i++){

                for(int j = 0; j < 4; j++){
                    current = current.getChild(i).getChild(j);
                    traverseAndUpdateWeights(current, isAdding);
                }
                current.getChild(i).updateWeight(isAdding); //Update the dice Node
            }

            current.updateWeight(); //Update the move Node
        }
    }



    /**
     * the ai chooses the moveNode with the highest score,
     * sets rootNode to
     *
     * @param diceRoll
     */
    public void aiChooseMove(int diceRoll){
        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);
    }


    /**
     *
     *
     * @param diceRoll
     * @param startPos
     */
    public void playerChooseMove(int diceRoll, int startPos) {
        int moveChoice = 0;

        for (int i = 0; i < 4; i++) {
            if (rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos) {
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);


    /**
     * the ai chooses the moveNode with the highest score,
     * sets rootNode to
     *
     * @param diceRoll
     */
    public void aiChooseMove(int diceRoll){

        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

    }


    public void playerChooseMove(int diceRoll, int startPos){

        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        traverseAndAddLayer(rootNode);
        traverseAndUpdateWeights(rootNode, true);

        //TODO: Maybe replace isAdding with depth ???

        MoveNode current = node;

        if (current.getChild(0) != null) { //Recursively travel down the tree

            for(int i = 0; i < 6; i++){

                for(int j = 0; j < 4; j++){
                    current = current.getChild(i).getChild(j);
                    traverseAndUpdateWeights(current, isAdding);
                }
                current.getChild(i).updateWeight(isAdding); //Update the dice Node
            }

            current.updateWeight(); //Update the move Node
        }
    }



    /**
     * the ai chooses the moveNode with the highest score,
     * sets rootNode to
     *
     * @param diceRoll
     */
    public void aiChooseMove(int diceRoll){
        int moveChoice = 0;

        for(int i = 0; i < 4; i++){
            if(rootNode.getChild(diceRoll - 1).getChild(i).getWeight() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
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
    public void playerChooseMove(int diceRoll, int startPos) {
        int moveChoice = 0;

        for (int i = 0; i < 4; i++) {
            if (rootNode.getChild(diceRoll - 1).getChild(i).getMoveStartingPos() == startPos) {
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
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





}

class DiceNode {

}


class MoveNode {


}


}






