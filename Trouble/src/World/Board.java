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
                Pieces temp = board[greenSP];
                board[greenSP] = Pieces.GREEN;
                greenHome--;

                if(temp == Pieces.BLUE){
                    blueHome++;
                }
                else if(temp == Pieces.RED){
                    redHome++;
                }
                else if(temp == Pieces.YELLOW){
                    yellowHome++;
                }
            }

            else if(m.getStartPos() == -2){
                Pieces temp = board[blueSP];
                board[blueSP] = Pieces.BLUE;
                blueHome--;

                if(temp == Pieces.GREEN){
                    greenHome++;
                }
                else if(temp == Pieces.RED){
                    redHome++;
                }
                else if(temp == Pieces.YELLOW){
                    yellowHome++;
                }
            }

            else if(m.getStartPos() == -3){
                Pieces temp = board[redSP];
                board[redSP] = Pieces.RED;
                redHome--;

                if(temp == Pieces.BLUE){
                    blueHome++;
                }
                else if(temp == Pieces.GREEN){
                    greenHome++;
                }
                else if(temp == Pieces.YELLOW){
                    yellowHome++;
                }
            }

            else if(m.getStartPos() == -4){
                Pieces temp = board[yellowSP];
                board[yellowSP] = Pieces.YELLOW;
                yellowHome--;

                if(temp == Pieces.BLUE){
                    blueHome++;
                }
                else if(temp == Pieces.RED){
                    redHome++;
                }
                else if(temp == Pieces.GREEN){
                    greenHome++;
                }
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

    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();

        str.append(" ".repeat(14)).append("HOME").append(" ".repeat(21)).append("END\n");

        //Yellow Home
        str.append(" ".repeat(13));
        for(int i = 0; i < 4; i++){
            str.append("[ ").append( yellowHome > i ? toStringHelper(Pieces.YELLOW) : " " ).append(" ]");
        }
        str.append(" ".repeat(5));
        for(int i = 0; i < 4; i++){
            str.append("[ ").append( yellowEnd > i ? toStringHelper(Pieces.YELLOW) : " " ).append(" ]");
        }


        // Numbers
        str.append("\n\n").append(" ".repeat(20));
        for(int i = 24; i >= 18; i--){
            str.append(i).append("   ");
        }
        str.append("\n");


        // top row
        str.append("H [ ").append( greenHome > 0 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append(" ".repeat(11));
        for(int i = 24; i >= 18; i--){
            str.append("[ ").append(toStringHelper(board[i])).append(" ]");
        }
        str.append(" ".repeat(11)).append("[ ").append( redHome > 0 ? toStringHelper(Pieces.RED) : " " ).append(" ] H\n");


        // middle rows
        str.append("O [ ").append( greenHome > 1 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("   25 ").append("[ ").append(toStringHelper(board[25])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[17])).append(" ]").append(" 17   ").append("[ ").append( redHome > 1 ? toStringHelper(Pieces.RED) : " " ).append(" ] O\n");

        str.append("M [ ").append( greenHome > 2 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("   26 ").append("[ ").append(toStringHelper(board[26])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[16])).append(" ]").append(" 16   ").append("[ ").append( redHome > 2 ? toStringHelper(Pieces.RED) : " " ).append(" ] M\n");

        str.append("E [ ").append( greenHome > 3 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("   27 ").append("[ ").append(toStringHelper(board[27])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[15])).append(" ]").append(" 15   ").append("[ ").append( redHome > 3 ? toStringHelper(Pieces.RED) : " " ).append(" ] E\n");

        str.append("       ").append("    0 ").append("[ ").append(toStringHelper(board[0])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[14])).append(" ]").append(" 14   ").append("\n");

        str.append("E [ ").append( greenEnd > 0 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("    1 ").append("[ ").append(toStringHelper(board[1])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[13])).append(" ]").append(" 13   ").append("[ ").append( redEnd > 0 ? toStringHelper(Pieces.RED) : " " ).append(" ] E\n");

        str.append("N [ ").append( greenEnd > 1 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("    2 ").append("[ ").append(toStringHelper(board[2])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[12])).append(" ]").append(" 12   ").append("[ ").append( redEnd > 1 ? toStringHelper(Pieces.RED) : " " ).append(" ] N\n");

        str.append("D [ ").append( greenEnd > 2 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append("    3 ").append("[ ").append(toStringHelper(board[3])).append(" ]").append(" ".repeat(35));
        str.append("[ ").append(toStringHelper(board[11])).append(" ]").append(" 11   ").append("[ ").append( redEnd > 2 ? toStringHelper(Pieces.RED) : " " ).append(" ] D\n");


        // bottom row
        str.append("  [ ").append( greenEnd > 3 ? toStringHelper(Pieces.GREEN) : " " ).append(" ]").append(" ".repeat(11));
        for(int i = 4; i <= 10; i++){
            str.append("[ ").append(toStringHelper(board[i])).append(" ]");
        }
        str.append(" ".repeat(11)).append("[ ").append( redEnd > 3 ? toStringHelper(Pieces.RED) : " " ).append(" ]");


        str.append("\n").append(" ".repeat(20));
        for(int i = 4; i <= 9; i++){
            str.append(i).append("    ");
        }
        str.append("10\n\n");


        //Blue Home
        //Yellow Home
        str.append(" ".repeat(13));
        for(int i = 0; i < 4; i++){
            str.append("[ ").append( blueHome > i ? toStringHelper(Pieces.BLUE) : " " ).append(" ]");
        }
        str.append(" ".repeat(5));
        for(int i = 0; i < 4; i++){
            str.append("[ ").append( blueEnd > i ? toStringHelper(Pieces.BLUE) : " " ).append(" ]");
        }

        str.append("\n").append(" ".repeat(14)).append("HOME").append(" ".repeat(21)).append("END\n");

        return str.toString();

    }



    public String toStringHelper(Pieces c){
        if(c == Pieces.GREEN){
            return "\u001B[32m" + "G" + "\u001B[0m";
        } else if(c == Pieces.BLUE){
            return "\u001B[34m" + "B" + "\u001B[0m";
        } else if(c == Pieces.YELLOW){
            return "\u001B[33m" + "Y" + "\u001B[0m";
        } else if(c == Pieces.RED){
            return "\u001B[31m" + "R" + "\u001B[0m";
        } else {
            return " ";
        }
    }

    /**
     *
     * @param c piece color
     * @return an array of indices that c's pieces are on (with duplicates, with home). returns -2 for pieces in end, -1 for
     * pieces in home.
     */
    public int[] legalPieceIndices(Pieces c) {
        int[] indices = new int[4];
        int counter = 0;

        for (int i = getHome(c); i > 0; i--) {
            indices[counter++] = -1;
        }

        for (int i = 0; i < 28; i++) {
            if (board[i] == c) {
                indices[counter++] = i;
            }
        }

        for (int i = getEnd(c); i > 0; i--) {
            indices[counter++] = -2;
        }

        return indices;
    }

    public void pieceCount(){
        int total = greenEnd + blueEnd + redEnd + yellowEnd + greenHome + redHome + blueHome + yellowHome;
        for(int x = 0; x < board.length;  x++){
            if(board[x] != Pieces.BLACK){
                total++;
            }
        }
        if(total != 16){
            System.out.println("Sean is not correct it works");
        }
    }


}