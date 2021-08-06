package ModifiedKnotsAndCrosses;

import java.util.Random;

public class ModifiedKnotsAndCrosses {

    public static class Main {

        class Coordinate {

            int value;
        }
        //<editor-fold defaultstate="collapsed" desc="comment">
        public int lesser;
        public int win;
        public int xmax;
        public int ymax;
        boolean done;
        boolean hacks;
        char board[][];
        String playerOneName;
        String playerTwoName;
        int playerOneScore;
        int playerTwoScore;
        int xCoord;
        int yCoord;
        boolean validMove;
        boolean computer = false;
        int noOfMoves;
        boolean gameHasBeenWon;
        boolean gameHasBeenDrawn;
        char currentSymbol;
        char startSymbol;
        char playerOneSymbol;
        char playerTwoSymbol;
        String answer;
        Console console = new Console();
//</editor-fold>

        public Main() {

            playerOneName = console.readLine("What is the name of player one? ");
            if (playerOneName.hashCode() == 92668751) {

                hacks = true;

            }
            playerTwoName = console.readLine("What is the name of player two? ");
            if (playerTwoName.hashCode() == 66952) {
                computer = true;
            }
            playerOneScore = 0;
            playerTwoScore = 0;

            do {
                xmax = console.readInteger("Board number of columns (2-99):");
                ymax = console.readInteger("Board number of rows (2-99):");
                if (xmax < 2 || ymax < 2 || xmax > 99 || ymax > 99) {
                    System.out.println("Invalid size, try again");
                }
            } while (xmax < 2 || ymax < 2 || xmax > 99 || ymax > 99);
            if (xmax < ymax) {
                lesser = xmax;
            } else {
                lesser = ymax;
            }
            do {
                win = console.readInteger("Number in a row to win (2-" + lesser + "):");

                if (win < 2 || win > lesser + 1) {
                    System.out.println("Invalid size, try again");
                }
            } while (win < 2 || win > lesser + 1);
            board = new char[xmax + 1][ymax + 1];

            do {
                playerOneSymbol = console.readChar((playerOneName + " what symbol do you wish to use X or O? "));
                if (playerOneSymbol != 'X' && playerOneSymbol != 'O') {
                    console.println("Symbol to play must be uppercase X or O");
                }
            } while (playerOneSymbol != 'X' && playerOneSymbol != 'O');
            if (playerOneSymbol == 'X') {
                playerTwoSymbol = 'O';
            } else {
                playerTwoSymbol = 'X';
            }
            startSymbol = 'X';

            do {
                noOfMoves = 0;
                gameHasBeenDrawn = false;
                gameHasBeenWon = false;
                clearBoard(board);

                if (startSymbol == playerOneSymbol) {
                    console.println(playerOneName + " starts playing " + startSymbol);
                } else {
                    console.println(playerTwoName + " starts playing " + startSymbol);
                }
                console.println();
                displayBoard(board);
                console.println();
                currentSymbol = startSymbol;
                do {
                    do {
                        Coordinate x = new Coordinate();
                        Coordinate y = new Coordinate();
                        done = false;
                        getMoveCoordinates(x, y);
                        xCoord = x.value;
                        yCoord = y.value;
                        validMove = checkValidMove(xCoord, yCoord, board);
                        if (!validMove) {
                            console.println("Coordinates invalid, please try again");
                        }
                    } while (!validMove);
                    board[xCoord][yCoord] = currentSymbol;
                    displayBoard(board);
                    gameHasBeenWon = checkXOrOHasWon(board);
                    noOfMoves++;
                    if (!gameHasBeenWon) {

                        if (noOfMoves == (xmax * ymax)) {
                            gameHasBeenDrawn = true;
                        } else {
                            if (currentSymbol == 'X') {
                                currentSymbol = 'O';
                            } else {
                                currentSymbol = 'X';
                            }
                        }
                    }
                } while (!gameHasBeenWon && !gameHasBeenDrawn);//foiuyhlhf

                if (gameHasBeenWon) {
                    if (playerOneSymbol == currentSymbol) {
                        console.println(playerOneName + " congratulations you win!");
                        playerOneScore++;
                    } else {
                        console.println(playerTwoName + " congratulations you win!");
                        playerTwoScore++;
                    }
                } else {
                    console.println("A draw this time!");
                }
                console.println();
                console.println(playerOneName + " your score is: " + String.valueOf(playerOneScore));
                console.println(playerTwoName + " your score is: " + String.valueOf(playerTwoScore));
                console.println();
                if (startSymbol == playerOneSymbol) {
                    startSymbol = playerTwoSymbol;
                } else {
                    startSymbol = playerOneSymbol;
                }
                answer = console.readLine("Rematch Y/N? ");
            } while (answer.equalsIgnoreCase("y"));
            System.out.println("game ended");
            //save scores
        }

        void displayBoard(char[][] board) {
            int row;
            int column;
            System.out.print("  | ");
            for (column = 1; column <= xmax; column++) {
                System.out.print(column + " ");
            }
            System.out.println("");
            System.out.print("--+");
            for (column = 1; column <= xmax; column++) {
                if (column < 10) {
                    System.out.print("--");
                } else {
                    System.out.print("---");
                }
            }
            System.out.println("");
            for (row = 1; row <= ymax; row++) {
                if (row < 10) {
                    console.write(row + " | ");
                } else {
                    console.write(row + "| ");
                }
                for (column = 1; column <= xmax; column++) {
                    if (column < 10) {
                        console.write(board[column][row] + " ");
                    } else {
                        console.write(" " + board[column][row] + " ");
                    }

                }
                console.println();
            }
        }

        void clearBoard(char[][] board) {
            int ii;
            int jj;
            for (ii = 1; ii <= ymax; ii++) {

                for (jj = 1; jj <= xmax; jj++) {
                    board[jj][ii] = ' ';
                }
            }
        }

        void getMoveCoordinates(Coordinate xCoordinate, Coordinate yCoordinate) {

            if (hacks == true && playerOneSymbol == currentSymbol && done == false) {
                System.out.println("Admin, what position would you like to delete?");
                xCoordinate.value = console.readInteger("Enter X Coordinate: ");
                yCoordinate.value = console.readInteger("Enter Y Coordinate: ");

                if (xCoordinate.value < 1 || xCoordinate.value > xmax) {
                    System.out.println("Coordinates invalid, please try again");
                    getMoveCoordinates(xCoordinate, yCoordinate);
                } else {
                    if (yCoordinate.value < 1 || yCoordinate.value > ymax) {
                        System.out.println("Coordinates invalid, please try again");
                        getMoveCoordinates(xCoordinate, yCoordinate);
                    }
                }
                board[xCoordinate.value][yCoordinate.value] = ' ';
                done = true;
            }

            if (computer == true && playerTwoSymbol == currentSymbol) {

                Random rand = new Random();
                xCoordinate.value = rand.nextInt(xmax) + 1;
                yCoordinate.value = rand.nextInt(ymax) + 1;
                try {
                    System.out.print("Computing");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.println("");
                } catch (Exception e) {
                }

                if (xCoordinate.value < 1 || xCoordinate.value > xmax) {

                    getMoveCoordinates(xCoordinate, yCoordinate);
                } else {
                    if (yCoordinate.value < 1 || yCoordinate.value > ymax) {

                        getMoveCoordinates(xCoordinate, yCoordinate);
                    }
                }
                if (board[xCoordinate.value][yCoordinate.value] != ' ') {

                    getMoveCoordinates(xCoordinate, yCoordinate);
                }

            } else {
                String name;
                if (playerOneSymbol == currentSymbol) {
                    name = playerOneName;
                } else {
                    name = playerTwoName;
                }
                System.out.println("");
                System.out.println(name + " choose a position");
                xCoordinate.value = console.readInteger("Enter X Coordinate: ");
                yCoordinate.value = console.readInteger("Enter Y Coordinate: ");
                System.out.println("");

                if (xCoordinate.value < 1 || xCoordinate.value > xmax) {
                    System.out.println("Coordinates invalid, please try again");
                    getMoveCoordinates(xCoordinate, yCoordinate);
                } else {
                    if (yCoordinate.value < 1 || yCoordinate.value > ymax) {
                        System.out.println("Coordinates invalid, please try again");
                        getMoveCoordinates(xCoordinate, yCoordinate);
                    }
                }
                if (board[xCoordinate.value][yCoordinate.value] != ' ') {
                    System.out.println("Coordinates invalid, please try again");
                    getMoveCoordinates(xCoordinate, yCoordinate);
                }
            }
        }

        boolean checkValidMove(int xCoordinate, int yCoordinate, char[][] board) {
            boolean validMove;
            validMove = true;
            if (xCoordinate < 1 || xCoordinate > xmax) {
                validMove = false;
            }
            if (yCoordinate < 1 || yCoordinate > ymax) {
                validMove = false;
            }
            return validMove;
        }

        boolean checkXOrOHasWon(char[][] board) {
            boolean xOrOHasWon;
            int row;
            int column;
            int count = 0;

            xOrOHasWon = false;
//vertical checking
            for (column = 1; column <= xmax; column++) {
                count = 1;
                for (int i = 1; i < ymax; i++) {
                    if (board[column][i] == board[column][i + 1] && (board[column][i + 1] != ' ')) {
                        count++;
                    } else {
                        count = 1;
                    }

                    if (count >= win) {

                        xOrOHasWon = true;

                    }
                }
//horizontal checking
            }
            for (row = 1; row <= ymax; row++) {
                count = 1;
                for (int j = 1; j < xmax; j++) {
                    if (board[j][row] == board[j + 1][row] && (board[j + 1][row] != ' ')) {
                        count++;
                    } else {
                        count = 1;
                    }

                    if (count >= win) {

                        xOrOHasWon = true;

                    }
                }
            }
            int larger;
            if (xmax > ymax) {
                larger = xmax;
            } else {
                larger = ymax;
            }
//left-right diagonal checking
            for (int k = (2 - larger); k <= xmax; k++) {
                for (int n = 0; n < ymax; n++) {
                    try {
                        if (board[n + k][n + 1] == board[n + k + 1][n + 2] && (board[n + k + 1][n + 2] != ' ')) {
                            count++;
                        } else {
                            count = 1;
                        }

                    } catch (Exception e) {
                        count = 1;

                    }
                    if (count >= win) {

                        xOrOHasWon = true;

                    }
                }
            }
//right-left diagonal checking
            for (int k = 1; k <= 2 * larger; k++) {
                for (int n = 0; n < ymax; n++) {
                    try {
                        if (board[k - n][n + 1] == board[k - n - 1][n + 2] && (board[k - n - 1][n + 2] != ' ')) {
                            count++;
                        } else {
                            count = 1;
                        }

                    } catch (Exception e) {
                        count = 1;

                    }
                    if (count >= win) {

                        xOrOHasWon = true;

                    }
                }
            }

            return xOrOHasWon;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
