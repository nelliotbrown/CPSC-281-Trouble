package Test;
import java.util.Random;

public class Players {
    private Random rand = new Random();
    private boolean isHuman;

    public Players(boolean isHuman){
        this.isHuman = isHuman;
    }

    public void makeMove(int d, int startingPos){
        if(isHuman){
            playerChooseMove(startingPos, d);
        } else {
            aiChooseMove(d);
        }
    }

    public int rollDice(){
        return rand.nextInt(1,7);
    }


}
