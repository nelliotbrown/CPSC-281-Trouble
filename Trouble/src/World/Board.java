package World;
import Logic.Moves;
import java.util.Arrays;

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

    public Board(Board b, Moves m){
        for(int x = 0; x < board.length; x++){
            board[x] = b.getBoard()[x];
        }
        greenHome = b.getHome(Pieces.GREEN);
        blueHome = b.getHome(Pieces.BLUE);
        redHome = b.getHome(Pieces.RED);
        yellowHome = b.getHome(Pieces.YELLOW);

        for(int x = 0; x < greenEnd.length; x++){
            greenEnd[x] = b.getEnd(Pieces.GREEN)[x];
        }

        for(int x = 0; x < blueEnd.length; x++){
            blueEnd[x] = b.getEnd(Pieces.BLUE)[x];
        }

        for(int x = 0; x < redEnd.length; x++){
            redEnd[x] = b.getEnd(Pieces.RED)[x];
        }

        for(int x = 0; x < yellowEnd.length; x++){
            yellowEnd[x] = b.getEnd(Pieces.YELLOW)[x];
        }

        if(m.getWeight() == 1){
                            
        }

        greenHome = b.getHome(Pieces.GREEN);
        blueHome = b.getHome(Pieces.BLUE);
        redHome = b.getHome(Pieces.RED);
        yellowHome = b.getHome(Pieces.YELLOW);
        Pieces temp = board[m.getStartPos()];
        board[m.getStartPos()] = Pieces.BLACK;
        board[m.getEndPos()] = temp;
    }


    public Pieces[] getBoard(){
        return board;
    }


    public int getHome(Pieces c){
        if(c == Pieces.YELLOW){
            return yellowHome;
        }else if(c == Pieces.GREEN){
            return greenHome;
        }else if(c == Pieces.BLUE){
            return blueHome;
        }else{
            return redHome;
        }
    }

    public int getSP(Pieces c){
        if(c == Pieces.YELLOW){
            return yellowSP;
        }else if(c == Pieces.GREEN){
            return greenSP;
        }else if(c == Pieces.BLUE){
            return blueSP;
        }else{
            return redSP;
        }
    }

    public Pieces[] getEnd(Pieces c) {
        if(c == Pieces.YELLOW){
            return yellowEnd;
        }else if(c == Pieces.GREEN){
            return greenEnd;
        }else if(c == Pieces.BLUE){
            return blueEnd;
        }else{
            return redEnd;
        }

        for(int x = 0; x < blueEnd.length; x++){
            blueEnd[x] = b.getEnd(Pieces.BLUE)[x];
        }

        for(int x = 0; x < redEnd.length; x++){
            redEnd[x] = b.getEnd(Pieces.RED)[x];
        }

        for(int x = 0; x < yellowEnd.length; x++){
            yellowEnd[x] = b.getEnd(Pieces.YELLOW)[x];
        }

        if(m.getWeight() == 1){

        }

        greenHome = b.getHome(Pieces.GREEN);
        blueHome = b.getHome(Pieces.BLUE);
        redHome = b.getHome(Pieces.RED);
        yellowHome = b.getHome(Pieces.YELLOW);
        Pieces temp = board[m.getStartPos()];
        board[m.getStartPos()] = Pieces.BLACK;
        board[m.getEndPos()] = temp;
    }


    public Pieces[] getBoard(){
        return board;
    }


    public int getHome(Pieces c){
        if(c == Pieces.YELLOW){
            return yellowHome;
        }else if(c == Pieces.GREEN){
            return greenHome;
        }else if(c == Pieces.BLUE){
            return blueHome;
        }else{
            return redHome;
        }
    }

    public int getSP(Pieces c){
        if(c == Pieces.YELLOW){
            return yellowSP;
        }else if(c == Pieces.GREEN){
            return greenSP;
        }else if(c == Pieces.BLUE){
            return blueSP;
        }else{
            return redSP;
        }
    }

    public Pieces[] getEnd(Pieces c) {
        if(c == Pieces.YELLOW){
            return yellowEnd;
        }else if(c == Pieces.GREEN){
            return greenEnd;
        }else if(c == Pieces.BLUE){
            return blueEnd;
        }else{
            return redEnd;
        }

    }
}
