package reversi.model;

import reversi.controller.Coordinates;

import java.awt.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Chip>> board;

    // = .black
    ChipColor turn = ChipColor.black;
    ChipColor opponent = ChipColor.white;
    int numOfChips = 0;
    enum Mode {
        noob, prof;
    }

    Mode mode = Mode.noob;

    public Board() {
        board = new ArrayList<>(8);
        for (int i = 0; i < 8; ++i) {
            var newLine = new ArrayList<Chip>(8);
            for (int j = 0; j < 8; ++j) {
                newLine.add(new Chip(ChipColor.empty));
            }
            board.add(newLine);
        }
        board.get(3).set(3, new Chip(ChipColor.white));
        board.get(4).set(4, new Chip(ChipColor.white));
        board.get(3).set(4, new Chip(ChipColor.black));
        board.get(4).set(3, new Chip(ChipColor.black));
        // board.get(3).get(3);
    }

    public List<List<Chip>> getBoard() {
        return board;
    }

    public boolean SetChip(Coordinates coordinates) {
        if (IsGoodCell(coordinates.line, coordinates.column) &&
                EvaluationFunc(coordinates.line, coordinates.column) >= 1) {
            board.get(coordinates.line).set(coordinates.column, new Chip(turn));
//            if (turn == ChipColor.white) {
//                turn = ChipColor.black;
//            } else {
//                turn = ChipColor.white;
//            }
            ++numOfChips;
            return true;
        }
        return false;
    }

    boolean StillOnTheBoard(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    int AddToResult(int line, int column, int addToLine, int addToColumn) {
        int add = 0;
        var y = column + addToColumn;
        for (var x = line + addToLine; StillOnTheBoard(x, y); x += addToLine) {
            if (board.get(x).get(y).color == ChipColor.empty) {
                add = 0;
                break;
            }
            if (board.get(x).get(y).color == turn) {
                break;
            } else {
                if (!StillOnTheBoard(x + addToLine, y + addToColumn)) {
                    // if it was the last iteration
                    add = 0;
                    break;
                }
                if (x == 0 || x == 7 || y == 0 || y == 7) {
                    add += 2;
                } else {
                    ++add;
                }
            }
            y += addToColumn;
        }
        return add;
    }

    double EvaluationFunc(int line, int column) {
        double result = 0;
        // here x is the equivalent of line, y - of column
        /*
            0 ------------> y
            |
            |
            |
            x
         */
        result += AddToResult(line, column, -1, -1);
        result += AddToResult(line, column, -1, 0);
        result += AddToResult(line, column, -1, 1);
        result += AddToResult(line, column, 0, -1);
        result += AddToResult(line, column, 0, 1);
        result += AddToResult(line, column, 1, -1);
        result += AddToResult(line, column, 1, 0);
        result += AddToResult(line, column, 1, 1);

        // if uglovaya
        if ((line == 0 || line == 7) && (column == 7 || column == 0)) {
            result += 0.8;
        } else {
            // if kromochnaya
            if (line == 0 || line == 7 || column == 7 || column == 0) {
                result += 0.4;
            }
        }
        return result;
    }

    boolean IsGoodCell(int line, int column) {
        if (board.get(line).get(column).color != ChipColor.empty) {
            return false;
        }
        ChipColor opposite = (turn == ChipColor.white) ? ChipColor.black
                : ChipColor.white;
        if ((line >= 1 && column >= 1 && board.get(line - 1).get(column - 1).color == opposite)
                || (line >= 1 && board.get(line - 1).get(column).color == opposite)
                || (line >= 1 && column <= 6 && board.get(line - 1).get(column + 1).color == opposite)
                || (column >= 1 && board.get(line).get(column - 1).color == opposite)
                || (column <= 6 && board.get(line).get(column + 1).color == opposite)
                || (line <= 6 && column >= 1 && board.get(line + 1).get(column - 1).color == opposite)
                || (line <= 6 && board.get(line + 1).get(column).color == opposite)
                || (line <= 6 && column <= 6 && board.get(line + 1).get(column + 1).color == opposite)
        ) {
            return true;
        }
        return false;
    }

    public Coordinates ChoosePositionNoob() {
        double maxEvaluation = 0;
        var bestPosition = new Coordinates(); // new object but for what?
        for (int line = 0; line < 8; ++line) {
            for (int column = 0; column < 8; ++column) {
                if (IsGoodCell(line, column)) {
                    double evaluation = EvaluationFunc(line, column);
                    if (evaluation > maxEvaluation) {
                        bestPosition.line = line;
                        bestPosition.column = column;
                        maxEvaluation = evaluation;
                    }
                }
            }
        }
        if (maxEvaluation < 1) {
            // System.out.println("You have no move ;(");
            return null;
        }
        return bestPosition;
    }

    void ChangeColorInOneDirection(int line, int column, int addToLine, int addToColumn) {
        var y = column + addToColumn;
        for (var x = line + addToLine; StillOnTheBoard(x, y); x += addToLine) {
            if (board.get(x).get(y).color == ChipColor.empty) {
                break;
            }
            if (board.get(x).get(y).color == turn) {
                // go back to the start
                while (x != line || y != column) {
                    board.get(x).get(y).color = turn;
                    x -= addToLine;
                    y -= addToColumn;
                }
                break;
            } else {
                if (!StillOnTheBoard(x + addToLine, y + addToColumn)) {
                    // if it was the last iteration
                    break;
                }
            }
            y += addToColumn;
        }
    }

    public void ChangeColor(int line, int column) {
        ChangeColorInOneDirection(line, column, -1, -1);
        ChangeColorInOneDirection(line, column, -1, 0);
        ChangeColorInOneDirection(line, column, -1, 1);
        ChangeColorInOneDirection(line, column, 0, -1);
        ChangeColorInOneDirection(line, column, 0, 1);
        ChangeColorInOneDirection(line, column, 1, -1);
        ChangeColorInOneDirection(line, column, 1, 0);
        ChangeColorInOneDirection(line, column, 1, 1);
    }

    public void ChangeTurn() {
        var temp = turn;
        turn = opponent;
        opponent = temp;
    }
    public boolean ComputersTurn() {
        Coordinates coordinates;
//        if (mode == Mode.noob) {
//            coordinates = ChoosePositionNoob();
//        } else {
//            coordinates = ChoosePositionProf();
//        }
        coordinates = ChoosePositionNoob();
        if (coordinates == null) {
            return false;
        }
        board.get(coordinates.line).set(coordinates.column, new Chip(turn));
        ++numOfChips;
        ChangeColor(coordinates.line, coordinates.column);
        return true;
    }

    public boolean NoEmptyCells() {
        return numOfChips == 64;
    }

    public void FinishTheGame() {
        int blackChips = 0;
        int whiteChips = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board.get(i).get(j).color == ChipColor.white) {
                    ++whiteChips;
                } else {
                    ++blackChips;
                }
            }
        }
        System.out.println("Score:");
        System.out.println("black: " + blackChips);
        System.out.println("white: " + whiteChips);
        if (whiteChips > blackChips) {
            System.out.println("White won!");
        } else {
            if (whiteChips == blackChips) {
                System.out.println("Wow! Friendship won");
            } else {
                System.out.println("Black won!");
            }
        }
    }
}