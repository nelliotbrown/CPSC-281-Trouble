package Test;
import java.util.*;

import Logic.DiceNode;
import Logic.Moves;
import Logic.Tree;
import World.Pieces;

public class Players {
    private final Random rand = new Random();
    private boolean isHuman;
    private static final Scanner scanner = new Scanner(System.in);

    public Players(boolean isHuman){
        this.isHuman = isHuman;
    }

    //TODO: stop letting humans attempt to move piece from home when there's other legal options
    public void makeMove(Tree tree, int d){

        if(isHuman) {
            Pieces color = DiceNode.nextColor(tree.getRootNode().getColor());
            System.out.println("\n" + color + " rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            System.out.println("Pieces on: " + Arrays.toString(tree.getRootNode().getBoard().legalPieceIndices(color)));

            // Find legal moves
            Moves[] possibleMoves = Moves.findMoves(tree.getRootNode().getBoard(), d, color);
            HashSet<Integer> legalMoveIndices = new HashSet<>();
            for (Moves moves : possibleMoves) {
                if (moves.getWeight() != -100) {
                    legalMoveIndices.add(moves.getStartPos());
                }
            }


            // User input section
            String str;
            int num = 0;
            boolean goodValue;
            if (legalMoveIndices.size() > 0) { // If there are legal moves
                do {
                    System.out.println("Legal moves: " + legalMoveIndices);
                    System.out.println("Where do you want to move from ? Type 'home' to move from your Home or the index of one of your pieces.");
                    str = scanner.nextLine();

                    if (str.equals("home")) {
                        num = switch (color) {
                            case GREEN -> -1;
                            case BLUE -> -2;
                            case RED -> -3;
                            default -> -4;
                        };

                        goodValue = legalMoveIndices.contains(num);

                    } else {
                        try {
                            num = Integer.parseInt(str);
                            goodValue = true;

                            // If a piece is moved from an illegal index
                            if (!legalMoveIndices.contains(num)) {
                                throw new NoSuchElementException();
                            }

                        } catch (NumberFormatException s) {
                            System.out.println("Error, please try again.");
                            goodValue = false;
                        } catch (NoSuchElementException e) {
                            System.out.println("You do not have a piece on that index, please try again.");
                            goodValue = false;
                        }
                    }
                } while (!goodValue);

            } else { // If there is no legal moves
                num = possibleMoves[0].getStartPos();
                System.out.println("No legal Moves !");
//                try{ Thread.sleep(1000); } catch ( Exception ignored ){}
            }

            tree.playerChooseMove(d, num, color);
        } else {

            Pieces color = DiceNode.nextColor(tree.getRootNode().getColor());
            System.out.println("\n" + color + " rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            tree.aiChooseMove(d);

//            try{ Thread.sleep(1000); } catch ( Exception ignored ){}

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