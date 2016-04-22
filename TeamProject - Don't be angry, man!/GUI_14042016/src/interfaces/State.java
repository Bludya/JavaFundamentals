package interfaces;

import core.Coordinate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface State extends Serializable {

    Player getCurrentPlayer();

    void addPlayerToGame(Player player);

    List<Player> getAllPlayers();

    void setGamePhase(String phase);

    Map<Pawn, Integer> updateDiceResult(int result);

    void changeCurrentPlayer();

    Map<Pawn,Integer> getMovablePawns();

    List<Pawn> getAllPawnsAtPosition(Coordinate coordinates);
}
