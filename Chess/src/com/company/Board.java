package com.company;
public class Board{
    static final String cyanText = "\u001B[36m";
    static final String redText = "\u001B[31m";
    static final String resetText = "\u001B[0m";
    public static Piece [][] squares = new Piece[8][8];

    public static void displayBoard(){
        System.out.println(redText + "\t\t\t\t\t\t\t\t\t\t\t\t\t   0|1| 2| 3| 4|5| 6| 7|" +resetText);
        for(int i=0;i<8;i++){
            System.out.print(redText +"\t\t\t\t\t\t\t\t\t\t\t\t\t" +i + resetText +"|");
            for(int j=0;j<8;j++){
                if(squares[i][j] == null)
                System.out.print("　|");
                else{
                    //Show Cyan Pieces
                    if(squares[i][j].getColor() == 'C') {
                        System.out.print(cyanText + squares[i][j].getSymbol() + resetText + "|");
                        squares[i][j].setCurrentPos(i + "," + j);
                    }else{
                        //Show White Pieces
                        System.out.print(squares[i][j].getSymbol() + "|");
                        squares[i][j].setCurrentPos(i+","+j);
                    }
                }
            }
            System.out.println();
        }
    }
    static void setPieces(int row, int column, Piece piece){
        squares[row][column] = piece;
    }

}
