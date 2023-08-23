package entity;

import java.util.Random;

public class Board {
    int size;
    int[][] mat;

    public Board(int size) {
        this.size = size;
        mat = new int[size][size];
        int r1 = new Random().nextInt(size);
        int c1 = new Random().nextInt(size);

        int r2 = new Random().nextInt(size);
        int c2 = new Random().nextInt(size);
        while(r1==r2 && c1==c2){
            r2 = new Random().nextInt(size);
            c2 = new Random().nextInt(size);
        }
        mat[r1][c1] = 2;
        mat[r2][c2] = 2;

        System.out.println("Printing initial board...");
        print();
    }
    public void rightControl()throws Exception{
        //move elements to right
        //combine elements
        //find a (r,c) to input 2
        //check if any possible move exists
        shiftRight();
        combineElementsRight();
        input2InNewCell();
        boolean nextMove = nextMoveExists();
        if(!nextMove){
            throw new Exception("Game Over");
        }
    }
    public void leftControl()throws Exception{
        shiftLeft();
        combineElementsLeft();
        input2InNewCell();
        boolean nextMove = nextMoveExists();
        if(!nextMove){
            throw new Exception("Game Over");
        }
    }
    public void downControl()throws Exception{
        shiftDown();
        combineElementsDown();
        input2InNewCell();
        boolean nextMove = nextMoveExists();
        if(!nextMove){
            throw new Exception("Game Over");
        }
    }
    public void upControl()throws Exception{
        shiftUp();
        combineElementsUp();
        input2InNewCell();
        boolean nextMove = nextMoveExists();
        if(!nextMove){
            throw new Exception("Game Over");
        }
    }
    public void shiftDown(){
        for(int c=0;c<size;c++){
            int emptyRow=size-1, nonEmpty=size-1;
            while(emptyRow>=0 && nonEmpty>=0){
                if(mat[emptyRow][c]!=0){
                    emptyRow--;
                }
                else{
                    nonEmpty = emptyRow;
                    while(nonEmpty>=0 && mat[nonEmpty][c]==0){
                        nonEmpty--;
                    }
                    if(nonEmpty>=0){
                        mat[emptyRow][c] = mat[nonEmpty][c];
                        mat[nonEmpty][c] = 0;
                    }
                }
            }
        }
        System.out.println("Printing board after shifting down...");
        print();
    }
    public void shiftUp(){
        for(int c=0;c<size;c++){
            int emptyRow=0, nonEmpty=0;
            while(emptyRow<size && nonEmpty<size){
                if(mat[emptyRow][c]!=0){
                    emptyRow++;
                }
                else{
                    nonEmpty = emptyRow;
                    while(nonEmpty<size && mat[nonEmpty][c]==0){
                        nonEmpty++;
                    }
                    if(nonEmpty<size){
                        mat[emptyRow][c] = mat[nonEmpty][c];
                        mat[nonEmpty][c] = 0;
                    }
                }
            }
        }
        System.out.println("Printing board after shifting up...");
        print();
    }
    public void combineElementsUp(){
        for(int c=0;c<size;c++){
            for(int r=0;r<size-1;r++){
                if(mat[r][c]==mat[r+1][c]){
                    mat[r][c] = 2*mat[r][c];
                    mat[r+1][c] = 0;
                }
            }
        }
        System.out.println("Printing board after combining up...");
        print();
        shiftUp();
    }
    public void combineElementsDown(){
        for(int c=0;c<size;c++){
            for(int r=size-1;r>0;r--){
                if(mat[r][c]==mat[r-1][c]){
                    mat[r][c] = 2*mat[r][c];
                    mat[r-1][c] = 0;
                }
            }
        }
        System.out.println("Printing board after combining down...");
        print();
        shiftDown();
    }
    public void shiftLeft(){
        for(int r=0;r<size;r++){
            int emptyCol=0, nonEmpty=0;
            while(emptyCol<size && nonEmpty<size){
                if(mat[r][emptyCol]!=0){
                    emptyCol++;
                }
                else{
                    nonEmpty = emptyCol;
                    while(nonEmpty<size && mat[r][nonEmpty]==0){
                        nonEmpty++;
                    }
                    if(nonEmpty<size){
                        mat[r][emptyCol] = mat[r][nonEmpty];
                        mat[r][nonEmpty] = 0;
                    }
                }
            }
        }
        System.out.println("Printing board after shifting left...");
        print();
    }
    public void shiftRight(){
        for(int r=0;r<size;r++){
            int emptyCol=size-1, nonEmpty = size-1;
            while(emptyCol>=0 && nonEmpty>=0){
                if(mat[r][emptyCol]!=0){
                    emptyCol--;
                }
                else{
                    nonEmpty = emptyCol;
                    while(nonEmpty>=0 && mat[r][nonEmpty]==0){
                        nonEmpty--;
                    }
                    if(nonEmpty>=0){
                        mat[r][emptyCol] = mat[r][nonEmpty];
                        mat[r][nonEmpty] = 0;
                    }
                }
            }
        }
        System.out.println("Printing board after shifting right...");
        print();
    }
    public void combineElementsRight(){
        for(int r=0;r<size;r++){
            for(int c=size-1;c>0;c--){
                if(mat[r][c]==mat[r][c-1]){
                    mat[r][c] = 2*mat[r][c];
                    mat[r][c-1] = 0;
                }
            }
        }
        System.out.println("Printing board after combining right...");
        print();
        shiftRight();
    }
    public void combineElementsLeft(){
        for(int r=0;r<size;r++){
            for(int c=0;c<size-1;c++){
                if(mat[r][c]==mat[r][c+1]){
                    mat[r][c] = 2*mat[r][c];
                    mat[r][c+1] = 0;
                }
            }
        }
        System.out.println("Printing board after combining left...");
        print();
        shiftLeft();
    }
    public void input2InNewCell(){
        int r=new Random().nextInt(size),c=new Random().nextInt(size);
        while(mat[r][c]!=0){
            r=new Random().nextInt(size);
            c=new Random().nextInt(size);
        }
        System.out.println("New input cell..."+r+", "+c);
        mat[r][c] = 2;
        print();
    }
    public boolean nextMoveExists(){
        //check if empty cell exists
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(mat[i][j]==0){
                    return true;
                }
            }
        }
        //check in rows if two cell cols with same val
        for(int r=0;r<size;r++){
            for(int c=0;c<size-1;c++){
                if(mat[r][c]==mat[r][c+1]){
                    return true;
                }
            }
        }

        //check in cols if two cell rows have same val
        for(int c=0;c<size;c++){
            for(int r=0;r<size-1;r++){
                if(mat[r][c]==mat[r+1][c]){
                    return true;
                }
            }
        }
        return false;
    }
    public void print(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMat() {
        return mat;
    }

    public void setMat(int[][] mat) {
        this.mat = mat;
    }
}
