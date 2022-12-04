package reversi.view;

import reversi.model.Chip;
import reversi.model.ChipColor;

import java.util.List;

public class BoardView {
    static String lineOfLetters = "    a  b  c  d  e  f  g  h";
    public static void PrintBoard(List<List<Chip>> board) {
        System.out.print(lineOfLetters); // make it const
        int numOfLine = 0;
        for (var line : board) {
            System.out.println();
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
