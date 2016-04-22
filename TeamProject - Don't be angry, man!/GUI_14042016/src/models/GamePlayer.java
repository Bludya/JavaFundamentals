package models;

import core.Coordinate;
import core.FieldPositions;
import interfaces.Pawn;
import interfaces.Player;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class GamePlayer implements Player {
    private String name;
    private Color color;
    private int points;         //пионки закарани до края на играта.
    private Map<Integer,Pawn> pawns;
    private Map<Integer, Boolean> innerFields;
    private boolean[] outerFields;
    private Map<Integer, Coordinate> outerCoordinates;
    private Map<Integer, Coordinate> innerCoordinates;
    private FieldPositions fieldPositions;

    public GamePlayer(String name, Color color){  //Създава нов играч с даденото име и цвят.
        this.name = name;
        this.color = color;
        this.points = 0;
        this.pawns = new HashMap<>();
        fieldPositions = new FieldPositions();

        this.outerCoordinates = fieldPositions.getOuterFieldCoordinates(this.color);
        this.innerCoordinates = fieldPositions.getInnerFieldCoordinates(this.color);

        this.addPawns();

        this.innerFields = new HashMap<>();
        this.outerFields = new boolean[4];
    }


    @Override
    public Map<Integer,Pawn> getPawns() {    //Връща пионките на играча мапнати към позицията им. Така лесно ще се достъпват.
        return this.pawns;
    }

    @Override
    public void addPoints(){
        this.points++;
    }

    @Override
    public void blockPawns(boolean block){
        for(Pawn pawn : this.pawns.values()){
            pawn.getImage()
                    .setMouseTransparent(block);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void removePawn(int position) {
        this.pawns.remove(position);
    }

    @Override
    public Map<Integer, Boolean> getInnerFields() {
        return this.innerFields;
    }

    @Override
    public void takeInnerField(int position){
        this.innerFields.put(position, true);
    }

    @Override
    public void leaveInnerField(int position){
        this.innerFields.put(position, false);
    }

    @Override
    public Map<Integer,Coordinate> getInnerCoordinates(){
        return this.innerCoordinates;
    }

    @Override
    public boolean[] getOuterFields(){
        return this.outerFields;
    }

    @Override
    public void takeOuterField(int position){
        this.outerFields[position] = true;
    }

    @Override
    public void leaveOuterField(int position){
        this.outerFields[position] = false;
    }

    @Override
    public Map<Integer,Coordinate> getOuterCoordinates(){
        return this.outerCoordinates;
    }

    @Override
    public Map<Pawn,Integer> movablePawns(int amount){
        Map<Pawn,Integer> movablePawns = new HashMap<>();
        this.getPawns().forEach((key, value) -> {
            int move = value.canMove(amount);
            if(move!=-1){
                movablePawns.put(value,move);
            }
        });

        if(movablePawns.isEmpty()){
            return null;
        }
        else{
            return movablePawns;
        }
    }

    public Color getColor() {
        return this.color;
    }

    private void addPawns() {
        for (int index = 1; index < 5; index++) {
            Pawn pawn = new GamePawn(this,this.outerCoordinates.get(index));
            this.pawns.put(index, pawn);
        }
    }
}
