package com.example.user.myapplication.logic;

/**
 * Created by USER on 11/17/2016.
 */


        import com.example.user.myapplication.logic.GameBoard;
        import com.example.user.myapplication.logic.Player;

        import java.util.Scanner;


public class main {


    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        Scanner s = new Scanner(System.in);
        while(true) {
            gb.consoleDraw();
            System.out.print("Player " + gb.getCurrentPlayer() + "'s move? ");
            String input = s.nextLine();
            input = input.toUpperCase();
            gb.submitMove(input.charAt(0), gb.getCurrentPlayer());
            Player win = gb.checkForWin();
            if(win == Player.NO){
                gb.consoleDraw();
                System.out.println("The two players are tie");
                break;
            }
            if (win != Player.BLANK && win!=Player.NO) {
                gb.consoleDraw();
                if (win ==Player.X){
                    System.out.print(Player.X);
                }
                else if (win ==Player.O){
                    System.out.print(Player.O);
                }
                break;
            }
        }


    }



}
