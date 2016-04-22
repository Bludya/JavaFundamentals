package interfaces;

public interface Engine {

    State getGameState();

    void setGameState(State state);

    int throwDice();

    boolean movePawn(Pawn pawn, int position);
}
