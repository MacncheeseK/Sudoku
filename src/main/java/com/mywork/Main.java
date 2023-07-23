package com.mywork;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        generateBorad();
    }

    // Creates the Sudoku Board
    public static void generateBorad(){
       int[][] sudokuBoard = board(30);
        for(int Row=0; Row<9;Row++) {
            if(Row%3==0 && Row!=0) {
                System.out.print("---------+---------+---------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) System.out.print("|");
                System.out.print(" "+ sudokuBoard[Row][col]+" ");
            }
            System.out.println();
        }
    }

    public static int[][] board(int Difficulty){
        int[][] grid =new int[9][9];
        int row,col,genNum;
        for(int intrations =0; intrations<Difficulty;intrations++){
            do{
                row= (int)(Math.random()*9);
                col= (int)(Math.random()*9);
            }while(grid[row][col]!=0);
            do{
                genNum=(int)(1+ Math.random()*9);
            }while(!(checkGrid(grid,genNum,row,col) ));



            grid[row][col]=genNum;

        }

        return grid;
    }

    public static boolean checkRow(int[][] grid, int num, int row){
        for(int col=0 ;col<9;col++){
            if(grid[row][col]==num) return false;

        }
        return true;
    }
    public static boolean checkCol(int[][] grid, int num, int col) {
        for(int row=0;row<9;row++){
            if(grid[row][col]==num) return false;

        }
        return true;
    }
    public static boolean checkSquare(int[][] grid, int num, int row, int col){
        int rowSquare = (Math.floorDiv(row,3))*3;
        int colSquare = (Math.floorDiv(col,3))*3 ;
        for(int i=0;i<3;i++){
            for(int j =0; j<3;j++){
                if(grid[rowSquare+i][colSquare+j] == num) return false;
            }
        }
        return true;
    }
    public static boolean checkGrid(int[][] grid, int num,int row, int col){
        return checkCol(grid,num,col) && checkRow(grid, num,row) && checkSquare(grid,num,row,col);
    }
}