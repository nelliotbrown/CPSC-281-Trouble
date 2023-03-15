package Logic;

import World.Board;


public class Tree {

    //TODO liam says don't make him do any illegal moves, ie killing your own people

    private MoveNode rootNode;
    int layers;

    /**
     *
     * @param initialBoard A Board object set to the initial starting position,
     *                     all pieces in home(s).
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
     * for one players turn
     */
    public void addNodes(MoveNode node) {
        node.makeChildren(); // Adds dice Nodes

<<<<<<< HEAD
        for(int i = 0; i < 6; i++){ //Adds move Nodes below the Dice Nodes
            node.getChild(i).makeChildren();
        }
=======
        node.makeChildren(); // Adds dice Nodes

        for(int i = 0; i < 6; i++){ //Adds move Nodes below the Dice Nodes
            node.getChild(i).makeChildren();
        }

>>>>>>> 7853f5f (added dice and move nodes and a bit to tree)
    }


    /**
     * Adds "layers" to bottom of board
     *
     * MAY BREAK IF WE MAKE SOME CHILDREN NULL (SPECIFICAlLY 0) INDEX
     *
     * @param node
     */
    public void traverseAndAddLayer(MoveNode node) {

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
<<<<<<< HEAD
=======

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
>>>>>>> 7853f5f (added dice and move nodes and a bit to tree)

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
