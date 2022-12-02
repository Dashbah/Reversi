package reversi.model;

public class Chip {
    ChipColor color = ChipColor.empty;

    Chip(ChipColor color) {
        this.color = color;
    }

    public ChipColor getColor() {
        return color;
    }
}
