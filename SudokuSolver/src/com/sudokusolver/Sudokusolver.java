package com.sudokusolver;

import java.util.Scanner;

public class Sudokusolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardSize = 10;
        int[][] sudokuBoard = new int[boardSize][boardSize];
        int [][][] sudokuBoard3D;
        String entryMode;
        int tempInput;


        System.out.println("enter board manually? (y/n)");
        entryMode = scanner.nextLine();

        // input initial board data
        if (entryMode.equals("y")) {
            for (int i = 1; i < boardSize; i++) {
                for (int j = 1; j <= boardSize; j++) {
                    tempInput = scanner.nextInt();
                    sudokuBoard[i][j] = tempInput;
                }
            }
        }

        else {
            sudokuBoard = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 0, 0, 0, 0, 0, 0, 1, 5},
                    {0, 1, 0, 0, 3, 0, 0, 0, 0, 0},
                    {0, 7, 0, 0, 0, 0, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 7, 0, 0, 0},
                    {0, 0, 0, 0, 0, 6, 3, 0, 0, 2},
                    {0, 4, 0, 0, 0, 0, 5, 3, 6, 0},
                    {0, 5, 0, 0, 0, 2, 0, 0, 7, 0},
                    {0, 6, 0, 0, 0, 0, 0, 0, 5, 1},
                    {0, 3, 0, 4, 0, 0, 0, 8, 0, 9}
            };
        }


        //place board into a 3D array.
        sudokuBoard3D = board3DArray(sudokuBoard, boardSize);

        //prints the initial input board.
        printBoard(sudokuBoard3D, boardSize);

        //Assign possible answers
        boardAssignPossibleAnswers(sudokuBoard3D, boardSize);

        //solve by backtrack recursion
        solveByRecursion(sudokuBoard3D, boardSize);

        //prints the final board.
        //printBoard(sudokuBoard3D, boardSize);
    }

    /*
    places 2D board array into a 3D board array.
    i if row, j is column
    k = 0 is real answer, k != 0 is possible answer.
    sudokuBoard[1][1][0] = 4 the value that will be printed on the board at row 1 column 1.
    sudokuBoard[1][1][3] = 3 is a possible answer to row 1 column 1.
    */
    public static int[][][] board3DArray(int[][] board, int size) {
        int[][][] sudokuBoard = new int[10][10][10];

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                sudokuBoard[i][j][0] = board[i][j];
            }
        }

        return sudokuBoard;
    }

    /*
prints the current called board.
prints board[i][j][0]
 */
    public static void printBoard(int[][][] board, int size) {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if ((j == 4) || (j == 7)) {
                    System.out.print("|");
                }
                else if ((j == 1) && (i != 1)) {
                    System.out.println("");
                }
                if (((i == 4) && (j == 1) ) || ((i == 7) && (j == 1))) {
                    System.out.println("--- --- ---");
                }
                System.out.print(board[i][j][0]);
            }
        }
        System.out.println();
        System.out.println();
    }

    /*
assigns possible answer to k value.
example: when board[i][j][5] the value 5 will be store in the 5 value of the k array.
 */
    public static int[][][] boardAssignPossibleAnswers(int[][][] board, int size) {
        int num = 9;
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                for (int k = 1; k <= num; k++) {
                    if (emptySquare(board, i, j)) { //checks if square is empty.
                        if (verifyBeforeAssignment(board, i, j, k, size)) {  //
                            board[i][j][k] = k; //save value k as a possible assign
                            //System.out.println("row: " + i + " column: " + j + " possible answer: " + k);
                        }
                        else {
                            board[i][j][k] = 0; // sets possible solution value to zero in not possible answer square
                        }
                    }
                    // if square is filled sets possible answer to zero.
                    if (board[i][j][0] != 0) {
                        board[i][j][k] = 0;
                    }
                }
            }
        }

        return board;
    }

    /*
returns true if the value k is found in the row that is inputted.
 */
    public static boolean checkRow(int[][][] board, int row, int num, int size) {
        for (int j = 1; j < size; j++) {
            if (board[row][j][0] == num) {
                return false;
            }
        }
        return true;
    }

    /*
    return true if the value k is found in the column that is inputted.
     */
    public static boolean checkColumn(int[][][] board, int column, int num, int size) {
        for (int i = 1; i < size; i++) {
            if (board[i][column][0] == num) {
                return false;
            }
        }
        return true;
    }

    /*
    returns true if the value k is found in the square that row and column is inputted.
    */
    public static boolean checkSquare(int[][][] board, int row, int column, int num){

        if ((row <= 3) && (column <= 3)) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 3) && (column <= 6)) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 3) && (column <= 9)) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 6) && (column <= 3)) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 6) && (column <= 6)) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 6) && (column <= 9)) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 9) && (column <= 3)) {
            for (int i = 7; i <= 9; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else if ((row <= 9) && (column <= 6)) {
            for (int i = 7; i <= 9; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        else {
            for (int i = 7; i <= 9; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j][0] == num) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /*
attempts to solve the board by using backtracking recursion. It assign one of the possible right answer.
Then call this function again for the next empty cell.
 */
    public static boolean solveByRecursion(int[][][] board, int size) {

        for (int i = 1; i < size; i++) {      //cycles rows
            for (int j = 1; j < size; j++) {  //cycles columns
                if (emptySquare(board, i, j)) {    //finds empty cell
                    System.out.println("Found empty square @ " + i + " " + j);
                    for (int k = 1; k <= 9; k++) { //cycles 1-9 for answers.
                        if (board[i][j][k] == k && verifyBeforeAssignment(board, i, j, k, size)) {
                            board[i][j][0] = k; //assigns possible answer k to board
                            System.out.println("Assigned " + k + " to row: " + i + " column: " + j);
                            printBoard(board, size);
                            if (solveByRecursion(board, size)) {
                                return true;
                            } else {
                                System.out.println("reseting value : row: " + i + " column: " + j);
                                board[i][j][0] = 0;
                            }
                        }
                    }
                    if (emptySquare(board, i, j)) {
                        return false;
                    }
                } else if (fullBoard(board)) {
                    return true;
                }

            }

        }
        // If it finds the board full with all correct answers.
        if (checkAnswer(board, size)) {
            System.out.println("Corrected answer found");
            printBoard(board, size);
            return true;
        } else {
            System.out.println("failed");
            printBoard(board, size);
            return false;
        }
    }



    /*
    checks if they is only one match of number in a row, column,
    and square for each num. calls solve board if not correct.
     */
    public static boolean checkAnswer(int[][][] board, int size) {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                //System.out.println("test 1 checkAnswer " + i + " " + j);
                if (board[i][j][0] == 0) {
                    //System.out.println("test 2 checkAnswer " + i + " " + j);
                    return false;
                }
                else {
                    if (!checkRow(board, i, board[i][j][0], size)
                            || !checkColumn(board, j, board[i][j][0], size)
                            || !checkSquare(board, i, j, board[i][j][0])) {
                        //System.out.println("test 3 checkAnswer " + board[i][j][0]);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean verifyBeforeAssignment(int[][][] board, int row, int column, int num, int size) {
        if (checkRow(board, row, num, size)
                && checkColumn(board, column, num, size)
                && checkSquare(board, row, column, num)) {
            //System.out.println("valid placement found");
            return true;
        }

        return false;
    }

    public static boolean emptySquare(int[][][] board, int row, int column) {
        return board[row][column][0] == 0;
    }

    public static boolean fullBoard(int[][][] board) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                //check for empty square.
                if (board[i][j][0] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}