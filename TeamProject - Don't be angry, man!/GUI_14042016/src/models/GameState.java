package models;

import core.Coordinate;
import interfaces.Pawn;
import interfaces.State;
import interfaces.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GameState implements State {
    private Map<String, Player> playersInGame;

    String phase;
    int diceResult;
    Player currentPlayer;

    Map<Pawn, Integer> movablePawns;
    private List<Player> playersInOrder;
    private Integer nextPlayerIndex;

    public GameState() {
        this.phase = "preparing";
        this.movablePawns = new HashMap<>();
        this.playersInGame = new HashMap<>();
        this.playersInOrder = new ArrayList<>();
        this.nextPlayerIndex = 0;
    }

    @Override
    public Map<Pawn,Integer> getMovablePawns(){
        return this.movablePawns;
    }

    @Override
    public void addPlayerToGame(Player player) {
        this.playersInGame.put(player.getName(), player);


        // добавяне на играчите и в подредения списък
        // подредбата е по реда, в който са вкарани
        this.playersInOrder.add(player);
    }


    @Override
    public void setGamePhase(String phase){
        this.phase = phase;
    }

    @Override
    public List<Player> getAllPlayers(){
       return this.playersInOrder;
    }

    @Override
    public Map<Pawn, Integer> updateDiceResult(int diceResult){
        this.diceResult = diceResult;
        this.movablePawns = this.currentPlayer.movablePawns(diceResult);
        return movablePawns;
    }

    @Override
    public List<Pawn> getAllPawnsAtPosition(Coordinate coordinates) {
        List<Pawn> pawnsAtPosition = new ArrayList<>();

        // collects all pawns at the provided position
        for (Player player :
                this.playersInOrder) {
            pawnsAtPosition.addAll(
                    player
                            .getPawns()
                            .values()
                            .stream()
                            .filter(
                                    pawn -> pawn.getCoordinates() == coordinates)
                            .collect(Collectors.toList()));
        }

        return pawnsAtPosition;
    }

    @Override
    public void changeCurrentPlayer(){
        this.currentPlayer = this.getNextPlayer();
    }

    @Override
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    private Player getNextPlayer() {
        Player nextPlayer = playersInOrder.get(this.nextPlayerIndex);

        this.setNewNextPlayerIndex();

        return nextPlayer;
    }

    private void setNewNextPlayerIndex() {
        // индексът на следващия играч се увеличава
        // ако последния играч си е изиграл хода, се започва пак от първия
        if (this.nextPlayerIndex >= this.playersInOrder.size() - 1) {
            this.nextPlayerIndex = 0;
        }

        else {
            this.nextPlayerIndex++;
        }
    }
}
