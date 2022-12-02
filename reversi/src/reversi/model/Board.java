package reversi.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Chip>> board;

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
}