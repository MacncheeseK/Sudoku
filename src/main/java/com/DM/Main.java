package com.DM;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int[][] sudokuBoard = new int [9][9];
    public static Integer[] values = {1,2,3,4,5,6,7,8,9};
    public static void main(String[] args) {
        System.out.println("Hello world!");
        generateBoard();
        printBorad();

    }

    // instead of creating a half completed board create a full completed  board then take away the cells

    // Creates the Sudoku Board
    public static void printBorad(){
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
        System.out.println();
    }

    public static boolean generateBoard(){
         int row = 0,col=0;
         for(int i=0; i<81;i++){
             row = Math.floorDiv(i,9);
             col=i%9;
             if(sudokuBoard[row][col]==0){
                 List<Integer> listValues = Arrays.asList(values);
                 Collections.shuffle(listValues);
                 listValues.toArray(values);
                 for(int j=0;j<9;j++){
                     if(checkGrid(sudokuBoard,values[j],row,col)){
                         sudokuBoard[row][col]=values[j];
                         if(!checkEmpty(sudokuBoard)||generateBoard()){
                             return true;
                         }
                     }
                     break;
                 }

             }
         }
         sudokuBoard[row][col]=0;
        return false;
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
    public static  boolean checkEmpty(int[][] grid){
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                if(grid[row][col] ==0){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean solve(int row, int col){

        if(col == sudokuBoard[row].length){
            col=0;
            row++;
        }
        if(row ==sudokuBoard.length){
            return true;
        }
        if (sudokuBoard[row][col] != 0) {
            return solve(row,col+1);
        }
        for (int num = 1; num <= 9; num++) {
            if (checkGrid(sudokuBoard, num, row, col)) {
                sudokuBoard[row][col] = num;
                if (solve(row, col + 1)) {
                    return true;
                }else{
                    sudokuBoard[row][col] = 0;
                }
            }
        }
        return false;
    }

}