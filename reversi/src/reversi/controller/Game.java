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

    boolean UserTurn() {
        if (board.ChoosePositionNoob() == null) {
            // no move
            return false;
        }
        System.out.println("Type your command (in num letter format)");
        System.out.println("You are black ;)");
        // TypeVariants();
        while (true) {
            var coordinates = ParseCommand();
            if (board.SetChip(coordinates)) {
                board.ChangeColor(coordinates.line, coordinates.column);
                break;
            } else {
                System.out.println("Can't paste here, try again:");
            }
        }
        return true;
    }

    public void StartTheGame() {
        var skips = 0;
        while (true) {
            if (board.NoEmptyCells()) {
                break;
            }
            if (!UserTurn()) {
                if (skips == 1) {
                    System.out.println("You also have no move...");
                    break;
                }
                skips = 1;
                System.out.println("You have no move!");
            } else {
                skips = 0;
                BoardView.PrintBoard(board.getBoard());
            }
            board.ChangeTurn();

            if (board.NoEmptyCells()) {
                break;
            }
            // sleep here
            if (!board.ComputersTurn()) {
                if (skips == 1) {
                    System.out.println("Computer also has no move...");
                    break;
                }
                skips = 1;
                System.out.println("Computer has no move. Your turn!");
            } else {
                skips = 0;
                System.out.println("Computer's move:");
                BoardView.PrintBoard(board.getBoard());
            }
            board.ChangeTurn();
        }
        board.FinishTheGame();
        // move turn to Game
        // var line = parsedCommand.line;
        // var column = parsedCommand.column;
        // change turn
        // if there are empty lines
        // Console.clear
    }


}