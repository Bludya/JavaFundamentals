package models;

import core.Coordinate;
import core.FieldPositions;
import core.GamePhase;
import interfaces.Engine;
import interfaces.Pawn;
import interfaces.State;
import javafx.scene.effect.Glow;

import java.util.Map;
import java.util.Random;

/**
 * Created by Zerzolar on 13.4.2016 г..
 */
public class GameEngine implements Engine {
    private State gameState;
    private FieldPositions fieldPositions;

    public GameEngine(State gameState, FieldPositions fieldPositions) {

        this.gameState = gameState;
        this.fieldPositions = fieldPositions;
    }

    @Override
    public State getGameState() {
        return this.gameState;
    }

    @Override
    public int throwDice() {
        int dice = new Random().nextInt(6) + 1;  //throws the dice 1-6

        Map<Pawn,Integer> movablePawns =  this.gameState.updateDiceResult(dice);
        if(movablePawns==null){

            gameState.changeCurrentPlayer();
            //used for dice unblocking in Launcher.
            return dice+10;
        }

        if(movablePawns!=null){
            for(Pawn pawn : movablePawns.keySet()){
                pawn.getImage().setEffect(new Glow(20));
            }
        }
        return dice;

    }

    @Override
    public boolean movePawn(Pawn pawn, int position) {
        boolean isPawnMoved = pawn.move(position);
        System.out.println("pawn.move returns " + isPawnMoved);
        if (!isPawnMoved) {
            System.out.println("GameEngine.movePawn returns false");
            return false;
        }


        if (pawn.getGamePhase() == GamePhase.OutOfGame) {
            return false;
        }
        if(pawn.getGamePhase() == GamePhase.ThroughGame){
            return true;
        }

        Coordinate newPawnPosition = null;
        if (pawn.getGamePhase() == GamePhase.InGame || pawn.getGamePhase() == GamePhase.InnerFields) {
            newPawnPosition = FieldPositions
                    .getFieldsByPlayerColor(
                            this.gameState.getCurrentPlayer().getColor())
                    .get(position);
        }

        if (newPawnPosition == null) {
            System.out.println("GameEngine.movePawn returns false");
            return false;
        }

        this.setPawnGraphicalLocation(pawn, newPawnPosition);
        System.out.println("GameEngine.movePawn returns true");
        return true;
    }

    @Override
    public void setGameState(State state) {
        this.gameState = state;
    }

    private void setPawnGraphicalLocation(Pawn pawn, Coordinate newLocation) {
        pawn.getImage().setLayoutX(newLocation.getPositionX());
        pawn.getImage().setLayoutY(newLocation.getPositionY());
    }
}
