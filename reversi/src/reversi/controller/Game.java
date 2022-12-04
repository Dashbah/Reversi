package reversi.controller;

import reversi.model.*;
import reversi.view.BoardView;
import reversi.view.Instructions;

import java.util.Scanner;

public class Game {
    Board board;
    static Scanner input = new Scanner(System.in);

    public Game() {
        board = new Board();
    }

    // returns Coordinates(-1, -1) to cancel the last move
    public static Coordinates ParseCommand() {
        while (true) {
            var command = input.nextLine();
            try {
                if (command.startsWith("-1")) {
                    return new Coordinates(-1, -1);
                }
                int line = Integer.parseInt(command.substring(0, 1)) - 1;
                if (line < 0 || line > 7) {
                    throw new Exception();
                }
                int column;
                if (command.charAt(2) >= 'a' && command.charAt(2) <= 'h') {
                    column = command.charAt(2) - 'a';
                } else {
                    throw new Exception();
                }
                return new Coordinates(line, column);
            } catch (Exception e) {
                System.out.println("Incorrect command. Try again");
            }
        }
    }

    void ChooseMode() {
        System.out.println("for person-person game enter 1");
        System.out.println("for person-computer game enter 2");
        while (true) {
            int typeOfPlayers;
            try {
                typeOfPlayers = input.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect input! Try again:");
                input.nextLine();
                continue;
            }
            if (typeOfPlayers == 1) {
                board.SetSecondPlayer(TypeOfPlayer.person2);
                input.nextLine();
                return;
            }
            if (typeOfPlayers == 2) {
                break;
            }
            System.out.println("Incorrect typeOfPlayers! Try again:");
        }
        input.nextLine();
    }

    public void StartTheGame() {
        ChooseMode();
        Instructions.PrintFirstInstructions();
        BoardView.PrintBoard(board.getBoard());
        var skips = 0;
        while (true) {
            if (board.NoEmptyCells()) {
                break;
            }
            try {
                if (!board.Turn()) {
                    if (skips == 1) {
                        System.out.println("Also no move...");
                        break;
                    }
                    skips = 1;
                    System.out.println("No move...");
                } else {
                    skips = 0;
                    BoardView.PrintBoard(board.getBoard());
                }
                board.ChangeTurn();
            } catch (CancelException e) {
                // when user enters -1 to cancel the move
                BoardView.PrintBoard(board.getBoard());
            }
        }
        board.FinishTheGame();
    }


}