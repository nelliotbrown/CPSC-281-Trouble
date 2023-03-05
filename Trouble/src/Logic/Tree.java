package Logic;

import World.Board;


public class Tree {

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
    }

    /**
     * Adds a layer of dice Nodes and move Nodes below node,
     * for one players turn
     */
    public void addNodes(MoveNode node) {

        node.makeChildren();

        // Makes children for dice Nodes and updates scores
        for(int i = 0; i < 6; i++){
            node.getChild(i).makeChildren();
            node.getChild(i).updateTotalScore();
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
            if(rootNode.getChild(diceRoll - 1).getChild(i).getScore() > moveChoice){
                moveChoice = i;
            }
        }

        rootNode = rootNode.getChild(diceRoll - 1).getChild(moveChoice);
        rootNode.setParent(null);

        //TODO add another layer to bottom of tree after this

    }


    public void addBottomLayer(){

        MoveNode current = rootNode;

        for(int i = 0; i < 2*layers; i++){

            for(int j = 0; j < 6; j++){

            }

        }





    }



}






