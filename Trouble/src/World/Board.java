package World;
import Logic.Moves;
import java.util.Arrays;

public class Board {
    public final int greenSP = 0;
    public final int blueSP = 7;
    public final int redSP = 14;
    public final int yellowSP = 21;
    private int greenHome;
    private int blueHome;
    private int redHome;
    private int yellowHome;
    private Pieces[] board = new Pieces[28];
    private Pieces[] greenEnd = new Pieces[4];
    private Pieces[] blueEnd = new Pieces[4];
    private Pieces[] redEnd = new Pieces[4];
    private Pieces[] yellowEnd = new Pieces[4];


    public Board() {
        greenHome = 4;
        blueHome = 4;
        redHome = 4;
        yellowHome = 4;
        Arrays.fill(board, Pieces.BLACK);
        Arrays.fill(greenEnd, Pieces.BLACK);
        Arrays.fill(blueEnd, Pieces.BLACK);
        Arrays.fill(redEnd, Pieces.BLACK);
        Arrays.fill(yellowEnd, Pieces.BLACK);
    }

    public Board(Board b,Moves m){
        for(int x = 0; x < board.length; x++){
            board[x] = b.getBoard()[x];
        }
        greenHome = b.getGreenHome();
        blueHome = b.getBlueHome();
        redHome = b.getRedHome();
        yellowHome = b.getYellowHome();

        for(int x = 0; x < greenEnd.length; x++){
            greenEnd[x] = b.getGreenEnd()[x];
        }

        for(int x = 0; x < blueEnd.length; x++){
            blueEnd[x] = b.getGreenEnd()[x];
        }

        for(int x = 0; x < redEnd.length; x++){
            redEnd[x] = b.getGreenEnd()[x];
        }

        for(int x = 0; x < yellowEnd.length; x++){
            yellowEnd[x] = b.getGreenEnd()[x];
        }

        if(m.getWeight() == 1){

        }

        greenHome = b.getGreenHome();
        blueHome = b.getBlueHome();
        redHome = b.getRedHome();
        yellowHome = b.getYellowHome();
        Pieces temp = board[m.getStartPos()];
        board[m.getStartPos()] = Pieces.BLACK;
        board[m.getEndPos()] = temp;
    }


    public Pieces[] getBoard(){
        return board;
    }

    public int getGreenHome(){
        return greenHome;
    }

    public int getBlueHome(){
        return blueHome;
    }

    public int getYellowHome(){
        return yellowHome;
    }

    public int getRedHome(){
        return redHome;
    }

    public int getGreenSP(){
        return greenSP;
    }

    public int getBlueSP(){
        return blueSP;
    }

    public int getYellowSP(){
        return yellowSP;
    }

    public int getRedSP(){
        return redSP;
    }

    public Pieces[] getGreenEnd() {
        return greenEnd;
    }

    public Pieces[] getBlueEnd() {
        return blueEnd;
    }

    public Pieces[] getRedEnd() {
        return redEnd;
    }

    public Pieces[] getYellowEnd() {
        return yellowEnd;
    }
}
