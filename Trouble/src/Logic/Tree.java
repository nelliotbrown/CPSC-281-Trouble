package Logic;

import World.Board;


public class Tree {

    //TODO liam says don't make him do any illegal moves, ie killing your own people

    //TODO if truncating the tree add a boolean to moveNode

    private MoveNode rootNode;
    private int layers;

    /**
     *
     * @param initialBoard A Board object set to the initial starting position,
     *                     all pieces in home(s).
     * @param layers Number of layers the tree will have.
     */
    public Tree( Board initialBoard, int layers) {

        rootNode = new MoveNode(initialBoard);
        this.layers = layers;

        for(int i = 0; i < layers; i++){
            this.traverseAndAddLayer(rootNode);
        }

    }

    /**
     * Adds a layer of dice Nodes and move Nodes below node,
     * for one player's turn
     *
     * @param node Node that layers are added to
     */
    public void addNodes(MoveNode node) {
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
     * MAY BREAK IF WE MAKE SOME CHILDREN NULL (SPECIFICAlLY 0) INDEX
     *
     * @param node
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
     *
     *
     * @param node
     * @param isAdding true to add score (your own turn), false to subtract
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
    }
}





}

class DiceNode {

}


class MoveNode {


}


}






