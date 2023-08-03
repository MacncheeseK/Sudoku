package com.DM;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SudokuGame {

    private static Board board;
    public static void main(String[] args) {
        SudokuGame sudokuGame= new SudokuGame();
        board = new Board();
        sudokuGame.play();


    }
    public static void play(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please type your next step");
            System.out.println("1.Start the game\n2.Validate board\n3.Auto solve\n4.Finish\n5.Exit");
            int userInput = input.nextInt();
            if(userInput==1){
                board.cellInsetion();
                board.printBoard();
            }else if(userInput ==2){
                board.validateBoard();
            }else if(userInput ==3){
                board.solve(0,0);
                board.printBoard();
            }else if(userInput ==4){
                if(board.finish()){
                    break;
                }
            }else if(userInput ==5){
                break;
            }

        }
    }

}