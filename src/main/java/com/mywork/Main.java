package com.mywork;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GenerateBorad();
    }

    // Creates the Soduku Board
    public static void GenerateBorad(){
        int row=9, col=9;
        for(int i=0; i<col;i++) {
            if(i%3==0 && i!=0) {
                System.out.print("---------|---------|---------\n");
            }
            for (int j = 0; j < row; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("|");
                System.out.print(" x ");
            }
            System.out.println();
        }
    }
}