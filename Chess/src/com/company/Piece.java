package com.company;


public abstract class Piece {
    private String symbol;
    private String currentPos;
    private char color;
    private static String [] availableMoves=new String[30];

    abstract void checkMoves();

    Piece(char color, String symbol){
        this.color = color;
        this.symbol = symbol;
    }

    public static void resetAvailableMoves(){
        for(int i=0;i<30;i++){
            availableMoves[i] = null;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(String currentPos) {
        this.currentPos = currentPos;
    }

    protected char getColor(){
        return color;
    }


    protected boolean Move(String place){
        boolean isPosAvailable = false;
        for (String availableMove : availableMoves) {
            if (place.equals(availableMove)) {
                isPosAvailable = true;
                break;
            }
        }
        if(isPosAvailable){

            int x = Character.getNumericValue(currentPos.charAt(0));
            int y = Character.getNumericValue(currentPos.charAt(2));
            int NewX = Character.getNumericValue(place.charAt(0));
            int NewY = Character.getNumericValue(place.charAt(2));
            //Castling Move
            if (place.equals("0,2") || place.equals("0,6") || place.equals("7,2") || place.equals("7,6")){
                if(NewY < 4){
                    Board.squares[x][4-2] = Board.squares[x][4];
                    Board.squares[x][(4-2)+1] = Board.squares[x][0];
                    Board.squares[x][4] = null;
                    Board.squares[x][0] = null;
                }
                if(NewY > 4){
                    Board.squares[x][4+2] = Board.squares[x][4];
                    Board.squares[x][(4+2)-1] = Board.squares[x][7];
                    Board.squares[x][4] = null;
                    Board.squares[x][7] = null;
                }
                return true;
            }

            //for Kill Move
            if(Board.squares[NewX][NewY]!=null){
                Board.squares[NewX][NewY] = Board.squares[x][y];
                Board.squares[x][y] = null;
            }else {
                Board.squares[NewX][NewY] = Board.squares[x][y];
                Board.squares[x][y] = null;
            }
            return true;
        }
        return false;
    }

    public void setAvailableMoves(String availableMove) {
        for(int i=0;i<30;i++){
            if(availableMoves[i]== null){
                System.out.print(availableMove + " || ");
                availableMoves[i] = availableMove;
                break;
            }
        }
    }
}

class Pawn extends Piece{

    Pawn(char color,String symbol){
        super(color,symbol);
    }
    @Override
    public void checkMoves(){
        //For Initial Start 1 or 2 spaces
        if(getColor()=='C'){
            int x = Character.getNumericValue(getCurrentPos().charAt(0));
            int y = Character.getNumericValue(getCurrentPos().charAt(2));
                if(x==1 && Board.squares[x+1][y] == null && Board.squares[x+2][y] == null){
                    int temp = x+1;
                    int temp2 = x+2;
                    setAvailableMoves(temp2+ "," + y);
                    setAvailableMoves(temp + "," + y);

                } else if(Board.squares[1+x][y] == null) {
                    int temp = 1 + x;
                    setAvailableMoves(temp + "," + y);
                }

                if(y<7 && Board.squares[x+1][y+1]!=null && Board.squares[x+1][y+1].getColor()=='W'){
                    int tempX = x+1;
                    int tempY = y+1;
                    setAvailableMoves(tempX + "," + tempY);
                }
                if(y>0 && Board.squares[x+1][y-1]!=null && Board.squares[x+1][y-1].getColor()=='W'){
                    int tempX = x+1;
                    int tempY = y-1;
                    setAvailableMoves(tempX + "," + tempY);
                }
        }
        else if(getColor()=='W'){
            //For initial Start 1 or 2 spaces
            int x = Character.getNumericValue(getCurrentPos().charAt(0));
            int y = Character.getNumericValue(getCurrentPos().charAt(2));
                if(x==6 && Board.squares[x-1][y] == null && Board.squares[x-2][y] == null){
                    int temp = x-1;
                    int temp2 = x-2;
                    setAvailableMoves(temp2+ "," + y);
                    setAvailableMoves(temp + "," + y);

                }else if(Board.squares[x-1][y] == null){
                    int temp = x-1;
                    setAvailableMoves(temp + "," + y);
                }

            if(y<7 && Board.squares[x-1][y+1]!=null && Board.squares[x-1][y+1].getColor()=='C'){
                int tempX = x-1;
                int tempY = y+1;
                setAvailableMoves(tempX + "," + tempY);
            }
            if(y>0 && Board.squares[x-1][y-1]!=null && Board.squares[x-1][y-1].getColor()=='C'){
                int tempX = x-1;
                int tempY = y-1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
    }
}
class Rook extends Piece{

    Rook(char color,String symbol){

        super(color,symbol);
    }
    @Override
    public void checkMoves() {
        int x = Character.getNumericValue(getCurrentPos().charAt(0));
        int y = Character.getNumericValue(getCurrentPos().charAt(2));
        int tempX = x, tempY = y;
        for (int i = 0; i < 8; i++) {
            tempX++;
            if (tempX < 8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempY++;
            if (tempY <8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempX--;
            if (tempX >= 0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempY--;
            if (tempY >= 0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
    }
}
class Knight extends Piece{

    Knight(char color,String symbol){
        super(color,symbol);
    }
    @Override
    public void checkMoves(){
        int x = Character.getNumericValue(getCurrentPos().charAt(0));
        int y = Character.getNumericValue(getCurrentPos().charAt(2));
        if ((x+1 <8 && y+2 <8)){
            if(Board.squares[x+1][y+2]==null || Board.squares[x+1][y+2].getColor()!=this.getColor()){
                int tempX = x+1;
                int tempY = y+2;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x+2 <8 && y+1 <8)){
            if(Board.squares[x+2][y+1]==null || Board.squares[x+2][y+1].getColor()!=this.getColor()){
                int tempX = x+2;
                int tempY = y+1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x-1 >=0 && y-2 >=0)){
            if(Board.squares[x-1][y-2]==null || Board.squares[x-1][y-2].getColor()!=this.getColor()){
                int tempX = x-1;
                int tempY = y-2;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x-2 >=0 && y-1 >=0)){
            if(Board.squares[x-2][y-1]==null || Board.squares[x-2][y-1].getColor()!=this.getColor()){
                int tempX = x-2;
                int tempY = y-1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x+2 <8 && y-1 >=0)){
            if(Board.squares[x+2][y-1]==null || Board.squares[x+2][y-1].getColor()!=this.getColor()){
                int tempX = x+2;
                int tempY = y-1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x-1 >=0 && y+2 <8)){
            if(Board.squares[x-1][y+2]==null || Board.squares[x-1][y+2].getColor()!=this.getColor()){
                int tempX = x-1;
                int tempY = y+2;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x+1 <8 && y-2 >=0)){
            if(Board.squares[x+1][y-2]==null || Board.squares[x+1][y-2].getColor()!=this.getColor()){
                int tempX = x+1;
                int tempY = y-2;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if ((x-2 >=0 && y+1 <8)){
            if(Board.squares[x-2][y+1]==null || Board.squares[x-2][y+1].getColor()!=this.getColor()){
                int tempX = x-2;
                int tempY = y+1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }

    }
}
class Bishop extends Piece{

    Bishop(char color,String symbol){
        super(color,symbol);
    }
    @Override
    public void checkMoves(){
        int x = Character.getNumericValue(getCurrentPos().charAt(0));
        int y = Character.getNumericValue(getCurrentPos().charAt(2));
        int tempX=x,tempY=y;
        for(int i=0;i<8;i++){
            tempX++;tempY++;
            if (tempX<8 && tempY<8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX++;tempY--;
            if (tempX<8 && tempY>=0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX--;tempY++;
            if (tempX>=0 && tempY<8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX--;tempY--;
            if (tempX>=0 && tempY>=0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
    }
}
class Queen extends Piece{

    Queen(char color,String symbol){
        super(color,symbol);
    }
    @Override
    public void checkMoves(){
        int x = Character.getNumericValue(getCurrentPos().charAt(0));
        int y = Character.getNumericValue(getCurrentPos().charAt(2));
        int tempX = x, tempY = y;
        for (int i = 0; i < 8; i++) {
            tempX++;
            if (tempX < 8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempY++;
            if (tempY <8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempX--;
            if (tempX >= 0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x;
        tempY = y;
        for (int i = 0; i < 8; i++) {
            tempY--;
            if (tempY >= 0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor())
                        setAvailableMoves(tempX + "," + tempY);
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX = x; tempY = y;
        for(int i=0;i<8;i++){
            tempX++;tempY++;
            if (tempX<8 && tempY<8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX++;tempY--;
            if (tempX<8 && tempY>=0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX--;tempY++;
            if (tempX>=0 && tempY<8) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        tempX=x;tempY=y;
        for(int i=0;i<8;i++){
            tempX--;tempY--;
            if (tempX>=0 && tempY>=0) {
                if (Board.squares[tempX][tempY] != null) {
                    if(Board.squares[tempX][tempY].getColor() != this.getColor()) {
                        setAvailableMoves(tempX + "," + tempY);
                    }
                    break;
                }
                setAvailableMoves(tempX + "," + tempY);
            }
        }
    }
}
class King extends Piece{

    private boolean isEligibleForCastling = false;
    private boolean isMoved = false;

    King(char color,String symbol){
        super(color,symbol);
    }
    @Override
    public void checkMoves(){
        int x = Character.getNumericValue(getCurrentPos().charAt(0));
        int y = Character.getNumericValue(getCurrentPos().charAt(2));
        int tempX = x+1, tempY= y;
        if(x<7){
            if(Board.squares[x+1][y]==null || Board.squares[x+1][y].getColor()!=this.getColor()) {
                setAvailableMoves(tempX + "," + tempY);
            }
            if(y<7){
                if (Board.squares[x+1][y+1]==null || Board.squares[x+1][y+1].getColor()!=this.getColor() ){
                    tempY= y+1;
                    setAvailableMoves(tempX + "," + tempY);
                }
            }
            if(y>0){
                if (Board.squares[x+1][y-1]==null || Board.squares[x+1][y-1].getColor()!=this.getColor() ){
                    tempY= y-1;
                    setAvailableMoves(tempX + "," + tempY);
                }
            }
        }
        if(x>0) {
            tempX = x - 1;
            if (Board.squares[x - 1][y] == null || Board.squares[x - 1][y].getColor() != this.getColor() ) {
                tempY = y;
                setAvailableMoves(tempX + "," + tempY);
            }
            if(y<7){
                if (Board.squares[tempX][y+1]==null || Board.squares[tempX][y+1].getColor()!=this.getColor() ){
                    tempY= y+1;
                    setAvailableMoves(tempX + "," + tempY);
                }
            }
            if(y>0){
                if (Board.squares[tempX][y-1]==null || Board.squares[tempX][y-1].getColor()!=this.getColor() ){
                    tempY= y-1;
                    setAvailableMoves(tempX + "," + tempY);
                }
            }

        }
        tempX=x;
        if(y<7){
            if(Board.squares[x][y+1] == null || Board.squares[x][y+1].getColor()!=this.getColor() ){
                tempY= y+1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }
        if(y>0){
            if(Board.squares[x][y-1] == null || Board.squares[x][y-1].getColor()!=this.getColor() ){
                tempY= y-1;
                setAvailableMoves(tempX + "," + tempY);
            }
        }

        //Checking Castling Requirements for White
        if(this.getColor() == 'W'){
            if(this.getCurrentPos().charAt(0) == '7' && this.getCurrentPos().charAt(2) == '4' && this.isMoved == false){
                this.isEligibleForCastling = true;
            }else{
                this.isMoved = true;
                this.isEligibleForCastling = false;
            }
        }
        //Checking castling Requirements for Cyan
        if(this.getColor() == 'C'){
            if(this.getCurrentPos().charAt(0) == '0' && this.getCurrentPos().charAt(2) == '4' && this.isMoved == false){
                this.isEligibleForCastling = true;
            }else{
                this.isMoved = true;
                this.isEligibleForCastling = false;
            }
        }
        tempX=x;
        tempY=y;

        if(this.isEligibleForCastling){
                for(int i=1;i<4;i++){
                    if(Board.squares[tempX][i]!=null){
                        isEligibleForCastling = false;
                        break;
                    }
                }
                if (isEligibleForCastling){
                    setAvailableMoves(tempX + "," + (tempY-2));
                }
                isEligibleForCastling = true;
                for(int i=5;i<7;i++){
                    if(Board.squares[tempX][i]!=null){
                        isEligibleForCastling = false;
                        break;
                    }
                }
                if (isEligibleForCastling){
                    setAvailableMoves(tempX + "," + (tempY+2));
                }
        }
    }
}
