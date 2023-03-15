package Test;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean gameState = false;
        System.out.println("Hello, World!");
        String answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Green to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                Players green = new Players(true);
            } else if (answer.equals("n")) {
                Players green = new Players(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Blue to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                Players Blue = new Players(true);
            } else if (answer.equals("n")) {
                Players Blue = new Players(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";

        while(!answer.equals("y") && !answer.equals("n")){
            System.out.println("Do you want Red to be Player or AI? 'y' for Yes, 'n' for No.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                Players Red = new Players(true);

            } else if (answer.equals("n")) {
                Players Red = new Players(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        answer = "temp";
        while(!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Do you want Yellow to be Player or AI? 'y' for Player, 'n' for AI.");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                Players Yellow = new Players(true);
            } else if (answer.equals("n")) {
                Players Yellow = new Players(false);
            } else {
                System.out.println("not valid response.");
            }
        }

        gameState = true;

        while(gameState){

        }

    }



}
