package com.sudokusolver;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Sudokusolver {
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((j == 3) || (j == 6)) {
                    System.out.print("|");
                }
                else if ((j == 0) && (i != 0)) {
                    System.out.println("");
                }
                if (((i == 3) && (j == 0) ) || ((i == 6) && (j == 0))) {
                    System.out.println("--- --- ---");
                }
                System.out.print(board[i][j]);
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void checkAnswer(int[][] board) {
        int blankAnswers = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    blankAnswers += 1;
                }
            }
        }
        if (blankAnswers == 0) {
            printBoard(board);
        }
        else{
            solveBoard(board);
        }
    }

    public static void solveBoard(int[][] board) {
        int boardSolved[][] = new int[9][9];
        Random rand = new Random();
        int r;
        int fullBoard = 81;

        while (fullBoard > 0) {
            boardSolved = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);

            for (int k = 0; k < 81; k++) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (boardSolved[i][j] == 0) {
                            r = (rand.nextInt(9) + 1);
                            if ((rowMatches(boardSolved, i, r) == 0) && (columnMatches(boardSolved, j, r) == 0) && (squareMatches(boardSolved, i, j, r) == 0)) {
                                boardSolved[i][j] = r;
                            }
                        }
                    }
                }
            }
            printBoard(boardSolved);
            fullBoard = 81;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (boardSolved[i][j] != 0) {
                        fullBoard -= 1;
                    }
                }
            }
        }
        //printBoard(board);
        checkAnswer(boardSolved);
    }

    public static int rowMatches(int[][] board, int row, int num) {
        int matches = 0;
        for (int i = 0; i < 9; i++)
            if (board[row][i] == num) {
                matches += 1;
            }
        return matches;
    }

    public static int columnMatches(int[][] board, int column, int num) {
        int matches = 0;
        for (int i = 0; i < 9; i++)
            if (board[i][column] == num) {
                matches += 1;
            }
        return matches;
    }

    public static int squareMatches(int[][] board, int row, int column, int num) {
        int matches = 0;
        if ((row < 3) && (column < 3)) {
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 3) && (column < 6)) {
            for (int i = 0; i < 3; i++){
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 3) && (column < 9)) {
            for (int i = 0; i < 3; i++){
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 6) && (column < 3)) {
            for (int i = 3; i < 6; i++){
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 6) && (column < 6)) {
            for (int i = 3; i < 6; i++){
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 6) && (column < 9)) {
            for (int i = 3; i < 6; i++){
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 9) && (column < 3)) {
            for (int i = 6; i < 9; i++){
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else if ((row < 9) && (column < 6)) {
            for (int i = 6; i < 9; i++){
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        else {
            for (int i = 6; i < 9; i++){
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == num) {
                        matches += 1;
                    }
                }
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sudokuBoard[][] = new int[][]{
            { 0, 0, 0, 0, 0, 8, 0, 0, 0 },
            { 0, 0, 7, 0, 0, 0, 3, 8, 0 },
            { 0, 9, 2, 3, 7, 0, 0, 5, 0 },
            { 0, 0, 6, 0, 9, 0, 0, 0, 3 },
            { 0, 0, 1, 5, 0, 2, 8, 0, 0 },
            { 4, 0, 0, 0, 3, 0, 6, 0, 0 },
            { 0, 3, 0, 0, 8, 9, 4, 6, 0 },
            { 0, 2, 4, 0, 0, 0, 5, 0, 0 },
            { 0, 0, 0, 6, 0, 0, 0, 0, 0 }
        };


        int tempInput;

        // input initial board data.
        /*System.out.println("Enter number top to bottom, left to right (blanks are 0):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tempInput = scanner.nextInt();
                sudokuBoard[i][j] = tempInput;
            }
        }*/

        //print input board.
        printBoard(sudokuBoard);

        //solve board
        solveBoard(sudokuBoard);
    }
}
