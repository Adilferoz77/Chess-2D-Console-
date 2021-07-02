package com.company;

import java.util.Scanner;

public class Game {
        //ALL WHITE PAWNS
    Piece WP1 = new Pawn('W',"♙");
    Piece WP2 = new Pawn('W',"♙");
    Piece WP3 = new Pawn('W',"♙");
    Piece WP4 = new Pawn('W',"♙");
    Piece WP5 = new Pawn('W',"♙");
    Piece WP6 = new Pawn('W',"♙");
    Piece WP7 = new Pawn('W',"♙");
    Piece WP8 = new Pawn('W',"♙");
        //White Rooks
    Piece WR1 = new Rook('W',"♖");
    Piece WR2 = new Rook('W',"♖");
        //White Knights
    Piece WX1 = new Knight('W',"♘");
    Piece WX2 = new Knight('W',"♘");
        //White Bishops
    Piece WB1 = new Bishop('W',"♗");
    Piece WB2 = new Bishop('W',"♗");
        //White Queen
    Piece WQ = new Queen('W',"♕");
        //White King
    Piece WK = new King('W',"♔");
        //ALL CYAN PAWNS
    Piece CP1 = new Pawn('C',"♙");
    Piece CP2 = new Pawn('C',"♙");
    Piece CP3 = new Pawn('C',"♙");
    Piece CP4 = new Pawn('C',"♙");
    Piece CP5 = new Pawn('C',"♙");
    Piece CP6 = new Pawn('C',"♙");
    Piece CP7 = new Pawn('C',"♙");
    Piece CP8 = new Pawn('C',"♙");
        //Cyan Rooks
    Piece CR1 = new Rook('C',"♖");
    Piece CR2 = new Rook('C',"♖");
        //Cyan Knights
    Piece CX1 = new Knight('C',"♘");
    Piece CX2 = new Knight('C',"♘");
        //Cyan Bishops
    Piece CB1 = new Bishop('C',"♗");
    Piece CB2 = new Bishop('C',"♗");
        //Cyan Queen
    Piece CQ = new Queen('C',"♕");
        //Cyan King
    Piece CK = new King('C',"♔");

        public void checkPieceMoves(Piece piece){
            System.out.print("\t\t\t\t\t\t\t\t\t\t\tAvailable Moves: ");
            piece.checkMoves();
            System.out.println();
        }

        void startGame() {

        Scanner scn = new Scanner(System.in);
        boolean game = true;
        boolean user1 = true;
        boolean user2 = false;

        while (game) {
            //Resetting Previous Moves
            Piece.resetAvailableMoves();
            if (user1) {
                System.out.println("\n");
                Board.displayBoard();
                String input;
                int x, y;
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\tPlayer1 Turn\n\t\t\t\t\t\t\t\t\t\t\tPick a Piece you want to move (Input Format: X,Y): ");
                input = scn.next();
                try{
                    x = Character.getNumericValue(input.charAt(0));
                    y = Character.getNumericValue(input.charAt(2));
                }
                catch (Exception e){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tIncorrect Place!\n\t\t\t\t\t\t\t\t\t\t\tTryAgain");
                    continue;
                }
                x = Character.getNumericValue(input.charAt(0));
                y = Character.getNumericValue(input.charAt(2));
                if(input.equals("QUIT")){
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\tEnding Game");
                    game = false;
                    home();
                }
                try {
                    if (Board.squares[x][y].getColor() == 'C'){
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\tYou can't pick your opponents Piece\n\t\t\t\t\t\t\t\t\t\t\tTry Again");
                        continue;
                    }
                }catch (Exception e){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tInvalid Input OR No Piece is placed on this place");
                    continue;
                }
                checkPieceMoves(Board.squares[x][y]);
                String moveTo;
                System.out.print("\t\t\t\t\t\t\t\t\t\t\tEnter a Place to move: ");
                moveTo = scn.next();

                if(Board.squares[x][y].Move(moveTo) == false){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tInvalid move!");
                    continue;
                }
                user1 = false;
                user2 = true;
            }
            Piece.resetAvailableMoves();
            if (user2) {
                System.out.println("\n");
                Board.displayBoard();
                String input;
                int x, y;
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\tPlayer2 Turn\n\t\t\t\t\t\t\t\t\t\t\tPick a Piece you want to move (Input Format: X,Y): ");
                input = scn.next();
                try{
                    x = Character.getNumericValue(input.charAt(0));
                    y = Character.getNumericValue(input.charAt(2));
                }
                catch (Exception e){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tIncorrect Place!\n\t\t\t\t\t\t\t\t\t\t\tTryAgain");
                    continue;
                }
                x = Character.getNumericValue(input.charAt(0));
                y = Character.getNumericValue(input.charAt(2));
                if(input.equals("QUIT")){
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\tEnding Game");
                    game = false;
                    home();
                }
                try {
                    if (Board.squares[x][y].getColor() == 'W'){
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\tYou can't pick your opponents Piece\n\t\t\t\t\t\t\t\t\t\t\tTry Again");
                        continue;
                    }
                }catch (Exception e){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tInvalid Input OR No Piece is placed on this place");
                    continue;
                }
                checkPieceMoves(Board.squares[x][y]);
                String moveTo;
                System.out.print("\t\t\t\t\t\t\t\t\t\t\tEnter a Place to move: ");
                moveTo = scn.next();
                if(Board.squares[x][y].Move(moveTo) == false){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\tInvalid move!");
                    continue;
                }
                user1 = true;
                user2 = false;
            }
        }
    }

    public void setGame(){
        Piece [] allPieces = {CR1,CX1,CB1,CQ,CK,CB2,CX2,CR2,CP1,CP2,CP3,CP4,CP5,CP6,CP7,CP8,WP1,WP2,WP3,WP4,WP5,WP6,WP7,WP8,WR1,WX1,WB1,WQ,WK,WB2,WX2,WR2};
        //Clearing Last Games Pieces
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Board.squares[i][j] = null;
            }
        }
        //Setting All Pieces for Starting
        int pieceIndex = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if((i>=0 && i<2) || (i>=6 && i<=7)){
                    Board.setPieces(i,j,allPieces[pieceIndex]);
                    allPieces[i].setCurrentPos(i+","+j);
                    pieceIndex++;
                }
            }
        }
    }
    public void instructions(){
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\tA Basic Guide on How to Play this version of Chess");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\tLets Begin");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\tHOW TO PLAY: ");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     The prerequisite of playing this is you must know the basic of playing Chess ");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     HOW TO PICK A PIECE:  ");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     When your turn comes, simply select the piece by Entering the Current Position of Piece");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t     For example: To move starting Pawn for white you can Select a piece by entering \"0,6\" ");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     HOW TO MOVE YOUR PIECE:");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     After picking up the Piece, you can see Available Moves in the next line, you can move to one of\n\t\t\t\t\t\t\t\t\t\t\t\t\t     these positions by entering them");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     Now wait for your opponent to Move and Try to Win the Game!");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     HOW TO END:");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t     Since this game has no Check and Checkmate System, You can end the game by typing QUIT, Please play FAIR!");
        home();
    }
    public void home(){
        System.out.println("\n\n");
        Scanner scn = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t1) Start a new Game");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t2) Instructions / How to Play");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t3) Exit");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\tEnter Your Choice: ");
        int choice = scn.nextInt();
        if(choice == 1){
            System.out.println("\t\t\t\t\t\t\t\t\tGame Started! To End the Game, Type QUIT to Switch to HomeScreen");
            setGame();
            startGame();
        }
        else if(choice == 2){
            instructions();
        }
        else if(choice == 3){
            System.out.println("Game Exited");
            System.exit(0);
        }else{
            System.out.println("Invalid Choice");
            home();
        }
    }

    public void logo(){
        System.out.println("\n\n");
        System.out.println("\t\t\t\t\t\t\t\t\t       ------        -----    -----       -----------        -------        -------   ");
        System.out.println("\t\t\t\t\t\t\t\t\t    ------------     -----    -----       -----------      -----  ----     -----  ----");
        System.out.println("\t\t\t\t\t\t\t\t\t    ----      --     -----    -----       ----             ---     ---     ---     ---");
        System.out.println("\t\t\t\t\t\t\t\t\t    ----             --------------       ----------       -----           -----      ");
        System.out.println("\t\t\t\t\t\t\t\t\t    ----             --------------       ----------         ------          ------   ");
        System.out.println("\t\t\t\t\t\t\t\t\t    ----      --     -----    -----       ----                   -----           -----");
        System.out.println("\t\t\t\t\t\t\t\t\t    ------------     -----    -----       ----------       -----  ----     -----  ----");
        System.out.println("\t\t\t\t\t\t\t\t\t       ------        -----    -----       ----------         -------         -------  ");
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.logo();
        game.home();
    }
}
