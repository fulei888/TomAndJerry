package com.example.user.myapplication.logic;

import com.example.user.myapplication.logic.Player;

/**
 * Created by USER on 11/17/2016.
 */



public class GameBoard {

    private Player[][] grid;
    private final int DIM = 5;
    Player whoseTurnIsIt;
    //private char[] rowLetters = {'A','B','C','D','E' };

    public GameBoard() {
        grid = new Player[DIM][DIM];
        for (int i=0; i<DIM; ++i) {
            for (int j=0; j<DIM; ++j) {
                grid[i][j] = Player.BLANK;
            }
        }
        whoseTurnIsIt = Player.X;
    }
    /**make the o x move
     *
     * @param move
     * @param p
     */
    public void submitMove(char move, Player p) {
        if (move >= '1' && move <= '5') {
            //vertical move, move stuff down
            int col = Integer.parseInt(""+move)-1;
            Player newVal = p;
            for (int i=0; i<DIM; ++i) {
                if (grid[i][col] == Player.BLANK) {
                    grid[i][col] = newVal;
                    break;
                } else {
                    Player tmp = grid[i][col];
                    grid[i][col] = newVal;
                    newVal = tmp;
                }
            }

        } else { //A-E
            //horizontal move, move stuff right
            int row = (int)(move - 'a');
            Player newVal = p;
            for (int i=0; i<DIM; ++i) {
                if (grid[row][i] == Player.BLANK) {
                    grid[row][i] = newVal;
                    break;
                } else {
                    Player tmp = grid[row][i];
                    grid[row][i] = newVal;
                    newVal = tmp;
                }
            }
        }
        if (whoseTurnIsIt == Player.X) {
            whoseTurnIsIt = Player.O;
        } else {
            whoseTurnIsIt = Player.X;
        }
    }
    //check who can win or tie
    /**
     * a
     * a
     *
     * @return
     */
    public Player checkForWin() {
        Player winner = Player.BLANK;
        Player winner1 = Player.BLANK;
        for (int i=0; i<DIM; ++i) {
            if (grid[i][0] != Player.BLANK) {
                winner = grid[i][0];
                for (int j=0; j<DIM; ++j) {
                    if (grid[i][j] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    if(i<(DIM-1)){
                        if (grid[i+1][0] != Player.BLANK) {
                            winner1 = grid[i+1][0];
                            for (int j=0; j<DIM; ++j) {
                                if (grid[i+1][j] != winner1) {
                                    winner1 = Player.BLANK;
                                    break;
                                }
                            }

                            if (winner1 != Player.BLANK) {
                                winner = Player.NO;
                            }

                        }

                    }
                    return winner; //5 in a row!
                }
            }
        }

		/*check all columns
		 *
		 */
        for (int i=0; i<DIM; ++i) {
            if (grid[0][i] != Player.BLANK) {
                winner = grid[0][i];
                for (int j=0; j<DIM; ++j) {
                    if (grid[j][i] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    if(i<(DIM-1)){
                        if (grid[0][i+1] != Player.BLANK) {
                            winner1 = grid[0][i+1];
                            for (int j=0; j<DIM; ++j) {
                                if (grid[j][i+1] != winner1) {
                                    winner1 = Player.BLANK;
                                    break;
                                }
                            }
                            if (winner1 != Player.BLANK) {
                                winner = Player.NO;
                            }

                        }
                    }
                    return winner; //5 in a column!
                }
            }
        }

		/*check top-left -> bottom-right diagonal
		 *
		 */
        if (grid[0][0] != Player.BLANK) {
            winner = grid[0][0];
            for (int i=0; i<DIM; ++i) {
                if (grid[i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                return winner; //5 in a diagonal!
            }
        }

		/*check bottom-left -> top-right diagonal
		 *
		 */
        if (grid[DIM-1][0] != Player.BLANK) {
            winner = grid[DIM-1][0];
            for (int i=0; i<DIM; ++i) {
                if (grid[DIM-1-i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                return winner; //5 in a diagonal!
            }
        }

        return winner;
    }
    /*draw every thing on the screen
     *
     */
    public void consoleDraw() {
        System.out.print("  ");
        for (int i=0; i<DIM; ++i) {
            System.out.print(i+1);
        }
        System.out.println();
        System.out.print(" /");
        for (int i=0; i<DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("\\");
        for (int i=0; i<DIM; ++i) {
            System.out.print(((char)('A'+i)) + "|");
            for (int j=0; j<DIM; ++j) {
                if (grid[i][j] == Player.BLANK) {
                    System.out.print(" ");
                } else {
                    if (grid[i][j] ==Player.X){
                        System.out.print(Player.X);
                    }
                    else if (grid[i][j] ==Player.O){
                        System.out.print(Player.O);
                    }
                }
            }
            System.out.println("|");
        }
        System.out.print(" \\");
        for (int i=0; i<DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("/");

    }
    /*see who's turn to place the x or o
     *
     */
    public Player getCurrentPlayer() {
        return whoseTurnIsIt;
    }
}
