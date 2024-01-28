package com.heydiecode.tictactoe.service;

public class TictactoeService {
    private char[][] board;
    private char currentPlayer;

    public TictactoeService(int size) {
        board = new char[size][size];
        currentPlayer = 'X';
        initializedBoard();
    }

    public void initializedBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getWinner() {
        return currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    public char[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {
        if(board[row][col] == '-') {
            board[row][col] = currentPlayer;
            togglePlayer();
        }
    }

    public void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkWinV2() {
        int rows = board.length;
        int cols = board[0].length;

        // Check rows and columns
        for (int i = 0; i < rows; i++) {
            if (checkRowCol(board[i]) || checkRowCol(getColumn(i))) {
                return true;
            }
        }

        // Check diagonals
        if (checkRowCol(getMainDiagonal()) || checkRowCol(getAntiDiagonal())) {
            return true;
        }

        return false;
    }

    private boolean checkRowCol(char[] values) {
        char firstValue = values[0];
        if (firstValue == '-') {
            return false;
        }

        for (char value : values) {
            if (value != firstValue) {
                return false;
            }
        }

        return true;
    }

    private char[] getColumn(int colIndex) {
        char[] column = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            column[i] = board[i][colIndex];
        }
        return column;
    }

    private char[] getMainDiagonal() {
        char[] diagonal = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            diagonal[i] = board[i][i];
        }
        return diagonal;
    }

    private char[] getAntiDiagonal() {
        char[] diagonal = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            diagonal[i] = board[i][board.length - 1 - i];
        }
        return diagonal;
    }

    public boolean checkDraw() {
        // Check if the board is full and there is no winner
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    // Found an empty cell, the game is not a draw
                    return false;
                }
            }
        }

        // If the loop completes, the board is full
        return !checkWinV2(); // Check if there is no winner
    }


}
