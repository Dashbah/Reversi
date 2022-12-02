package reversi.controller;

import reversi.model.Board;
import reversi.view.BoardView;
import reversi.view.Instructions;

import java.util.Scanner;

public class Game {

     Board board;
     Scanner input = new Scanner(System.in);
     public Game() {
          board = new Board();
          // maybe start the game here
     }

     public void StartGame() {
          Instructions.PrintFirstInstructions();
          BoardView.PrintBoard(board.getBoard());
     }

     public void GetCommand() {
          System.out.println("Type your command (in num letter format");

     }
}
