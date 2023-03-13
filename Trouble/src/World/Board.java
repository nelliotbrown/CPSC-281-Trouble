package World;

import Logic.Moves;

public class Board {
    public final int greenSP = 0;
    public final int blueSP = 7;
    public final int redSP = 14;
    public final int yellowSP = 21;
    private int greenHome;
    private int blueHome;
    private int redHome;
    private int yellowHome;

    public Board(Moves bob) {
        if(bob == null){
            greenHome = 4;
            blueHome = 4;
            redHome = 4;
            yellowHome = 4;
        }

    }
    
}
