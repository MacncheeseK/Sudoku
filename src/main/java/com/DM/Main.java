package com.DM;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int[][] sudokuBoard = new int [9][9];
    public static int[][] solvedSudokuBoard= new int[9][9];
    public static Integer[] values = {1,2,3,4,5,6,7,8,9};
    public static void main(String[] args) {
        generateBoard();
        chooseDifficulty();

    }

    // instead of creating a half completed board create a full completed  board then take away the cells

    // Creates the Sudoku Board
    public static void printBoard(){
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
    public static void gui(){





    }

    public static void validateBoard(){
        int num =0;
        for(int row =0; row<9;row++){
            for(int col =0; col<9; col++){
                if(sudokuBoard[row][col] !=0){
                    if(solvedSudokuBoard[row][col] != sudokuBoard[row][col]){
                        sudokuBoard[row][col]= 0;
                        num++;
                    }
                }
            }
        }
        printBoard();
        if(num>0) {
            System.out.println("You had "+ num +" cell in the wrong place and are now reverted back.");
        }else{
            System.out.println("Your board is valid.");
        }
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
                 for(int j = 0; j < 9; j++){
                     if(checkGrid(sudokuBoard,values[j],row,col)){
                         sudokuBoard[row][col]=values[j];
                         if(!checkEmpty(sudokuBoard)||generateBoard()){
                             return true;
                         }
                     }
                 }
                 break;
             }
         }
         sudokuBoard[row][col]=0;
        return false;
    }
    public static void chooseDifficulty(){
        copyBoard();
        Scanner input = new Scanner(System.in);
        System.out.println("Please type your number for difficulty.");
        System.out.println("1. Easy\n2. Medium\n3. Hard\n4.Exit");

        int userInput =input.nextInt();
        if(userInput!=4) {
            createDifficulty(userInput);
            printBoard();
            sudokuStart();
        }


    }

    public static void createDifficulty( int difficulty) {

        switch(difficulty){
            case(1):
                cellDeletion((int) (30+ Math.random()*5));
                break;
            case(2):
                cellDeletion((int) (35+ Math.random()*5));
                break;
            case(3):
                cellDeletion((int) (45+ Math.random()*5));
                break;
            default:
                cellDeletion((int) (20+ Math.random()*5));
                break;
        }
    }

    public static void cellDeletion(int numOfDeletion ){
        int row,col;
        for(int i=0; i<= numOfDeletion;i++){
            do{
                row = (int)(Math.random()*9);
                col = (int)(Math.random()*9);
            }while(sudokuBoard[row][col] ==0);

            sudokuBoard[row][col]=0;
        }
    }

    public static void sudokuStart(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please type your next step");
            System.out.println("1. Start the game\n2.Validate board\n3.Auto solve\n4.Exit");
            int userInput = input.nextInt();
            if(userInput==1){
                cellInsetion();
                printBoard();
            }else if(userInput ==2){
                validateBoard();
            }else if(userInput ==3){
                solve(0,0);
                printBoard();
            }else if(userInput ==4){
                break;
            }

        }
    }
    public static void cellInsetion(){
        Scanner input = new Scanner(System.in);
        int rowInput,colInput;
        System.out.println("Pick a cell to input a number");
        do {
            System.out.println("First pick the Row");
             rowInput = input.nextInt();
            System.out.println("Second pick the Col");
             colInput = input.nextInt();
        }while(!(0 <=rowInput && rowInput<9 && 0<=colInput && colInput<9));
        System.out.println("What number would you like to add at "+ rowInput +" , "+colInput+".");
        int userNumber = input.nextInt();
        while(!(0<userNumber && userNumber<=9)){
            System.out.println("Error Please enter a number from 1-9.");
            userNumber=input.nextInt();
        }
        sudokuBoard[rowInput][colInput] = userNumber;
    }

    public static void copyBoard(){
        for(int row=0; row<9; row++){
            for(int col=0; col<9;col++){
                solvedSudokuBoard[row][col]=sudokuBoard[row][col];
            }
        }
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