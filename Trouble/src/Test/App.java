package Test;
import java.sql.SQLOutput;
import java.util.Scanner;
import Logic.Tree;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean gameState = false;

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
            green.makeMove();
            blue.makeMove();
            red.makeMove();
            yellow.makeMove();
        }

    }



}
