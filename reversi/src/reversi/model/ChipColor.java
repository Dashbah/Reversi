package reversi.model;

public enum ChipColor {
    white{
        @Override
        public String toString() {
            return "white";
        }
    },
    black{
        @Override
        public String toString() {
            return "black";
        }
    },
    empty
}
