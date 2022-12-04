package reversi.model;

public class Chip {
    ChipColor color = ChipColor.empty;

    // int line;
    // int column;

    public Chip(ChipColor color) {
//        this.line = line;
//        this.column = column;
        this.color = color;
    }

    public ChipColor getColor() {
        return color;
    }
}
