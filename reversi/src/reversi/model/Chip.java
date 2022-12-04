package reversi.model;

public class Chip {
    ChipColor color = ChipColor.empty;

    public Chip(ChipColor color) {
        this.color = color;
    }

    public ChipColor getColor() {
        return color;
    }
}
