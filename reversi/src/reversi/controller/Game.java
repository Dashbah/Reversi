package reversi.controller;

import reversi.model.Board;
import reversi.view.BoardView;
import reversi.view.Instructions;

import java.util.Scanner;



public class Game {

    Board board;
    Scanner input = new Scanner(System.in);

    // ChipColor turn = ChipColor.white;

    public Game() {
        board = new Board();
        // maybe startGame here
    }

    public void StartGame() {
        Instructions.PrintFirstInstructions();
        BoardView.PrintBoard(board.getBoard());
    }

    Coordinates ParseCommand() {
         while (true) {
              var command = input.nextLine();
              try {
                   int line = Integer.parseInt(command.substring(0, 1)) - 1;
                   int column = command.charAt(2) - 'a';
                   return new Coordinates(line, column);
              } catch (Exception e) {
                  //NumberFormatException | IndexOutOfBoundsException e
                   System.out.println("Incorrect command. Try again");
              }
         }
    }

    void UserTurn() {
        System.out.println("Type your command (in num letter format)");
        // TypeVariants();
        while(true) {
            var coordinates = ParseCommand();
            if (board.SetChip(coordinates)) {
                board.ChangeColor(coordinates.line, coordinates.column);
                break;
            } else {
                System.out.println("Can't paste here, try again:");
            }
        }

    }

    public void StartTheGame() {
        while (true) {
            if (board.NoEmptyCells()) {
                return;
            }
            UserTurn();
            board.ChangeTurn();
            BoardView.PrintBoard(board.getBoard());
            board.ComputersTurn();
            board.ChangeTurn();
            BoardView.PrintBoard(board.getBoard());
        }
        // move turn to Game
         // var line = parsedCommand.line;
         // var column = parsedCommand.column;
        // change turn
         // if there are empty lines
        // Console.clear
    }


}