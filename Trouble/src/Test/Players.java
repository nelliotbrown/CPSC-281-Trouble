package Test;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import Logic.DiceNode;
import Logic.Tree;
import World.Pieces;

public class Players {
    private final Random rand = new Random();
    private boolean isHuman;
    private static final Scanner scanner = new Scanner(System.in);

    public Players(boolean isHuman){
        this.isHuman = isHuman;
    }

    public void makeMove(Tree tree, int d){

        if(isHuman){
            Pieces color = DiceNode.nextColor(tree.getRootNode().getColor());
            System.out.println("\n" + color + " rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            System.out.println(Arrays.toString(tree.getRootNode().getBoard().legalPieceIndices(color)));

            String str;
            int num = 0;
            boolean goodValue;

            do {

                System.out.println("Where do you want to move from ? Type 'home' to move from your Home or the index of one of your pieces.");
                str = scanner.nextLine();

                if (Objects.equals(str, "home")) {
                    goodValue = true;
                    num = switch (color) {
                        case GREEN -> -1;
                        case BLUE -> -2;
                        case RED -> -3;
                        default -> -4;
                    };
                } else {

                    try {
                        num = Integer.parseInt(str);
                        goodValue = true;
                    } catch (NumberFormatException s) {
                        System.out.println("Error, please try again.");
                        goodValue = false;
                    }

                }
            } while( !goodValue );

            tree.playerChooseMove(d, num);
        } else {

            Pieces color = DiceNode.nextColor(tree.getRootNode().getColor());
            System.out.println("\n" + color + " rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            tree.aiChooseMove(d);

            try{
                Thread.sleep(1000);
            } catch ( Exception ignored ){}

        }
    }

    public int rollDice(){
        return rand.nextInt(1,7);
    }

    // ~~~~~ Setters and Getters ~~~~~

    public void setHuman(boolean s){
        isHuman = s;
    }
}

