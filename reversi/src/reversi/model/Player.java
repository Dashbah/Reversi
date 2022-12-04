package reversi.model;

public class Player {
    TypeOfPlayer typeOfPlayer;
    ChipColor color;

    protected Player(TypeOfPlayer typeOfPlayer, ChipColor color) {
        this.typeOfPlayer = typeOfPlayer;
        this.color = color;
    }
}
