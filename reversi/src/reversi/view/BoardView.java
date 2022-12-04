package reversi.view;

import reversi.model.Chip;
import reversi.model.ChipColor;

import java.util.List;

public class BoardView {
    //   a b c d e f h
    // 1
    // ...
    // 8

    public static void PrintBoard(List<List<Chip>> board) {
        System.out.print("    a  b  c  d  e  f  g  h"); // make it const
        int numOfLine = 0;
        for (var line : board) {
            System.out.println();
            // System.out.println("_ _ _ _ _ _ _ _ _ _ _");
            System.out.print(++numOfLine);
            System.out.print(" | ");
            for (var chip : line) {
                System.out.print(GetSymbolOfChip(chip));
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    static char GetSymbolOfChip(Chip chip) {
        if (chip.getColor() == ChipColor.white) {
            return SymbolOfWhite;
        }
        if (chip.getColor() == ChipColor.black) {
            return SymbolOfBlack;
        } else {
            return EmptySym;
        }
    }

    static char SymbolOfWhite = '●';
    static char SymbolOfBlack = '◯';
    static char EmptySym = ' ';

}
