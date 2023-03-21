
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
    private int greenEnd;
    private int blueEnd;
    private int redEnd;
    private int yellowEnd;


    public Board() {
        greenHome = 4;
        blueHome = 4;
        redHome = 4;
        yellowHome = 4;
        Arrays.fill(board, Pieces.BLACK);
        greenEnd = 0;
        blueEnd = 0;
        redEnd = 0;
        yellowEnd = 0;
    }

    public Board(Board b, Moves m){
        for(int x = 0; x < board.length; x++){
            board[x] = b.getBoard()[x];
        }

        greenHome = b.getHome(Pieces.GREEN);
        blueHome = b.getHome(Pieces.BLUE);
        redHome = b.getHome(Pieces.RED);
        yellowHome = b.getHome(Pieces.YELLOW);

        greenEnd = b.getEnd(Pieces.GREEN);
        blueEnd = b.getEnd(Pieces.BLUE);
        redEnd = b.getEnd(Pieces.RED);
        yellowEnd = b.getEnd(Pieces.YELLOW);


        greenHome = b.getHome(Pieces.GREEN);
        blueHome = b.getHome(Pieces.BLUE);
        redHome = b.getHome(Pieces.RED);
        yellowHome = b.getHome(Pieces.YELLOW);

        if(m.getWeight() == 5){
            Pieces c = board[m.getStartPos()];
            board[m.getStartPos()] = Pieces.BLACK;
            if(c == Pieces.GREEN){
                greenEnd++;
            }

            else if(c == Pieces.BLUE){
                blueEnd++;
            }

            else if(c == Pieces.RED){
                redEnd++;
            }

            else if(c == Pieces.YELLOW){
                yellowEnd++;
            }

        }

        else if(m.getWeight() == 3){
            if(m.getStartPos() == -1){
                board[greenSP] = Pieces.GREEN;
                greenHome--;
            }

           else if(m.getStartPos() == -2){
                board[blueSP] = Pieces.BLUE;
                blueHome--;
            }

           else if(m.getStartPos() == -3){
                board[redSP] = Pieces.RED;
                redHome--;
            }

           else if(m.getStartPos() == -4){
                board[yellowSP] = Pieces.YELLOW;
                yellowHome--;
            }
        }

        else if(m.getWeight() == 2){
            Pieces temp = board[m.getEndPos()];
            board[m.getEndPos()] = board[m.getStartPos()];
            board[m.getStartPos()] = Pieces.BLACK;
            if(temp == Pieces.GREEN){
                greenHome++;
            }
            else if(temp == Pieces.BLUE){
                blueHome++;
            }
            else if(temp == Pieces.RED){
                redHome++;
            }
            else if(temp == Pieces.YELLOW){
                yellowHome++;
            }
        }

        else if(m.getWeight() == 4 || m.getWeight() == 1){
            board[m.getEndPos()] = board[m.getStartPos()];
            board[m.getStartPos()] = Pieces.BLACK;
       }


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

    public int getEnd(Pieces c) {
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

    public String toString(){
        return Arrays.toString(board);
    }

    public String toString(){
        return Arrays.toString(board);
    }
}
