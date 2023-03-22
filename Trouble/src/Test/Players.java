package Test;
import java.util.Random;
import java.util.Scanner;
import Logic.Tree;

public class Players {
    private Random rand = new Random();
    private boolean isHuman;
    private static Scanner scanner = new Scanner(System.in);

    public Players(boolean isHuman){
        this.isHuman = isHuman;
    }

    public void makeMove(Tree tree, int d){
        if(isHuman){
            System.out.println("You rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            System.out.println("Where do you want to move from ? ");
            String str = scanner.nextLine();
            int num = Integer.parseInt(str);
            tree.playerChooseMove(d, num);
        } else {
            System.out.println("You rolled: " + d);
            System.out.println(tree.getRootNode().getBoard());
            tree.aiChooseMove(d);
        }
    }

    public int rollDice(){
        return rand.nextInt(1,7);
    }

    public void setHuman(boolean s){
        isHuman = s;
    }
}

