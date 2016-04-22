package models;

import core.Coordinate;
import core.FieldPositions;
import core.GamePhase;
import interfaces.Pawn;
import interfaces.Player;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

public class GamePawn implements Pawn {
    private GamePhase gamePhase;
    private Color color;
    private int position;
    private Player owningPlayer;


    private ImageView pawnImage;
    private Coordinate outerFieldCoordinates;
    private Coordinate coordinates;


    public GamePawn(Player owningPlayer, Coordinate coordinates) {

        this.gamePhase = GamePhase.OutOfGame;
        this.owningPlayer = owningPlayer;
        this.color = this.owningPlayer.getColor();

        if(color == BLUE) {
            pawnImage = new ImageView("bluePawn.png");
        }
        else if(color == YELLOW) {
            pawnImage = new ImageView("yellowPawn.png");
        }
        else if(color == RED) {
            pawnImage = new ImageView("redPawn.png");
        }
        else if(color == GREEN) {
            pawnImage = new ImageView("greenPawn.png");
        }

        this.outerFieldCoordinates = coordinates;

        this.pawnImage.setLayoutX(this.outerFieldCoordinates.getPositionX());
        this.pawnImage.setLayoutY(this.outerFieldCoordinates.getPositionY());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Coordinate getCoordinates(){
        return this.coordinates;
    }

    @Override
    public boolean move(int newPosition){

        if (this.gamePhase == GamePhase.OutOfGame ||
                this.gamePhase == GamePhase.ThroughGame) {
            return false;
        }
        if(newPosition <= FieldPositions.LAST_FIELD_BEFORE_EXIT){
            this.position = newPosition;
            this.updateImage();
            return true;
        }
        return false;
    }

    private void updateImage() {
        Coordinate newCoordinate;

        if(this.position == FieldPositions.LAST_FIELD_BEFORE_EXIT){
            newCoordinate = FieldPositions.getWinPosition(this.owningPlayer.getColor());
            this.gamePhase = GamePhase.ThroughGame;
            this.pawnImage.setLayoutX(newCoordinate.getPositionX());
            this.pawnImage.setLayoutY(newCoordinate.getPositionY());
        }
        else{
            newCoordinate = FieldPositions.getFieldsByPlayerColor(this.color).get(this.position);

            this.coordinates = newCoordinate;
            this.pawnImage.setLayoutX(newCoordinate.getPositionX());
            this.pawnImage.setLayoutY(newCoordinate.getPositionY());
        }

    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int canMove(int amount){
        int move = this.position + amount;

        if(this.gamePhase == GamePhase.OutOfGame && amount == 6){
            return 1;
        }
        else if(this.gamePhase == GamePhase.InGame || this.gamePhase == GamePhase.InnerFields){
            if(move <= 45){
                return move;
            }
        }

        return -1;
    }

    @Override
    public void setPosition(int position,Coordinate coordinates) {
        this.position = position;

        this.pawnImage.setLayoutX(coordinates.getPositionX());
        this.pawnImage.setLayoutY(coordinates.getPositionY());
    }

    @Override
    public GamePhase getGamePhase() {
        return this.gamePhase;
    }

    @Override
    public void setGamePhase(GamePhase phase) {
        this.gamePhase = phase;
    }

    @Override
    public ImageView getImage(){
        return this.pawnImage;
    }

    @Override
    public void returnToPlayer() {
        this.pawnImage.setLayoutX(this.outerFieldCoordinates.getPositionX());
        this.pawnImage.setLayoutY(this.outerFieldCoordinates.getPositionY());
    }

    private boolean moveInInnerFields(int innerFieldMove) {
        if (this.gamePhase != GamePhase.InnerFields) {
            this.gamePhase = GamePhase.InnerFields;
        }
        if(!this.owningPlayer.getInnerFields().containsKey(innerFieldMove)){
            this.position = innerFieldMove;
            return true;
        }
        if (this.owningPlayer.getInnerFields().get(innerFieldMove) == true) {
            return false;
        }

        if (innerFieldMove > this.owningPlayer.getInnerFields().size()) {
            return false;
        }

        this.position = innerFieldMove;
        return true;
    }
}
