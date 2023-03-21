package Test;
import java.util.Scanner;

import Logic.MoveNode;
import Logic.Tree;
import World.Pieces;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean gameState = false;
        Tree tree = new Tree(4);

        Players green = new Players(false);
        Players blue = new Players(false);
        Players red = new Players(false);
        Players yellow = new Players(false);

        System.out.println("Hello, World!");
        String answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Green to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                green.setHuman(true);
            } else if (answer.equals("n")) {
                green.setHuman(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Blue to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                blue.setHuman(true);
            } else if (answer.equals("n")) {
                blue.setHuman(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")){
            System.out.println("Do you want Red to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                red.setHuman(true);

            } else if (answer.equals("n")) {
                red.setHuman(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";
        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Yellow to be Player or AI? 'y' for Player, 'n' for AI.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                yellow.setHuman(true);
            } else if (answer.equals("n")) {
                yellow.setHuman(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        gameState = true;

        while(gameState){
            if(gameState){
                green.makeMove(tree, green.rollDice());
                if(tree.getRootNode().getBoard().getEnd(Pieces.GREEN) == 4){
                    gameState = false;
                }
            }

            if(gameState){
                blue.makeMove(tree, blue.rollDice());
                if(tree.getRootNode().getBoard().getEnd(Pieces.BLUE) == 4){
                    gameState = false;
                }
            }

            if(gameState){
                red.makeMove(tree, red.rollDice());
                if(tree.getRootNode().getBoard().getEnd(Pieces.RED) == 4){
                    gameState = false;
                }
            }

            if(gameState){
                yellow.makeMove(tree, yellow.rollDice());
                if(tree.getRootNode().getBoard().getEnd(Pieces.YELLOW) == 4){
                    gameState = false;
                }
            }

        }

    }



}