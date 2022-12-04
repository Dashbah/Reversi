package reversi.model;

public enum TypeOfPlayer {
    person1{
        @Override
        public String toString() {
            return "Player1";
        }
    },
    person2{
        @Override
        public String toString() {
            return "Player2";
        }
    },
    computer
}
