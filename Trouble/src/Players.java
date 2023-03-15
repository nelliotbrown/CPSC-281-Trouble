import java.util.Random;

public class Players {
    private Random rand = new Random();

    public Players(){
    }

    public int rollDice(){
        return rand.nextInt(1,7);
    }
}
