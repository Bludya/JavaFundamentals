package core;

import javafx.scene.paint.Color;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldPositions implements Serializable {
    private Map<Integer, Coordinate> gameFields;

    private static Map<Integer, Coordinate> blueOuterFields;
    private static Map<Integer, Coordinate> yellowOuterFields;
    private static Map<Integer, Coordinate> redOuterFields;
    private static Map<Integer, Coordinate> greenOuterFields;

    private static Map<Integer, Coordinate> blueInnerFields;
    private static Map<Integer, Coordinate> yellowInnerFields;
    private static Map<Integer, Coordinate> redInnerFields;
    private static Map<Integer, Coordinate> greenInnerFields;

    private int fieldCounter;
    private int blueFieldCounter;
    private int yellowFieldCounter;
    private int redFieldCounter;
    private int greenFieldCounter;

    private int blueInnerFieldCounter;
    private int yellowInnerFieldCounter;
    private int redInnerFieldCounter;
    private int greenInnerFieldCounter;

    private static Map<Integer, Coordinate> redPlayerFields;
    private static Map<Integer, Coordinate> yellowPlayerFields;
    private static Map<Integer, Coordinate> greenPlayerFields;
    private static Map<Integer, Coordinate> bluePlayerFields;

    ///needs choosing and adding the actual ones.
    private static final double redWinYPosition = 20;
    private static final double blueWinYPosition = 40;
    private static final double greenWinYPosition = 60;
    private static final double yellowWinYPosition = 80;

    private static double redWinXIndex = 1;
    private static double yellowWinXIndex = 1;
    private static double greenWinXIndex = 1;
    private static double blueWinXIndex = 1;

    private static double pawnWinXSpacing = 20;

    public static final int LAST_GAME_FIELD_POSITION = 40;
    public static final int LAST_FIELD_BEFORE_EXIT = 45;

    public static final int blueStartPosition = 11;
    public static final int yellowStartPosition = 31;
    public static final int redStartPosition = 1;
    public static final int greenStartPosition = 21;

    public FieldPositions() {
        this.gameFields = new HashMap<>();
        this.fieldCounter = 1;

        blueOuterFields = new HashMap<>();
        yellowOuterFields = new HashMap<>();
        redOuterFields = new HashMap<>();
        greenOuterFields = new HashMap<>();

        blueInnerFields = new HashMap<>();
        yellowInnerFields = new HashMap<>();
        redInnerFields = new HashMap<>();
        greenInnerFields = new HashMap<>();

        fieldCounter = 1;

        blueFieldCounter = 1;
        yellowFieldCounter = 1;
        redFieldCounter = 1;
        greenFieldCounter = 1;

        blueInnerFieldCounter = 41;
        yellowInnerFieldCounter = 41;
        redInnerFieldCounter = 41;
        greenInnerFieldCounter = 41;

        this.addAllFields();
    }

    public static Coordinate getWinPosition(Color color){
        if(color == Color.RED){
            Coordinate winPositionCoordinate = new Coordinate(
                    FieldPositions.redWinXIndex * FieldPositions.pawnWinXSpacing,
                    redWinYPosition);

            FieldPositions.redWinXIndex++;

            return winPositionCoordinate;
        }
        else if(color == Color.BLUE){
            Coordinate winPositionCoordinate = new Coordinate(
                    FieldPositions.blueWinXIndex * FieldPositions.pawnWinXSpacing,
                    blueWinYPosition);

            FieldPositions.blueWinXIndex++;

            return winPositionCoordinate;
        }
        else if(color == Color.GREEN){
            Coordinate winPositionCoordinate = new Coordinate(
                    FieldPositions.greenWinXIndex * FieldPositions.pawnWinXSpacing,
                    greenWinYPosition);

            FieldPositions.greenWinXIndex++;

            return winPositionCoordinate;
        }
        else{
            Coordinate winPositionCoordinate = new Coordinate(
                    FieldPositions.yellowWinXIndex * FieldPositions.pawnWinXSpacing,
                    yellowWinYPosition);

            FieldPositions.yellowWinXIndex++;

            return winPositionCoordinate;
        }
    }

    private void addAllFields() {

        double currentY = 225;
        this.addField(211, currentY);
        this.addField(240, currentY);
        this.addField(270, currentY);
        this.addField(304, currentY);
        this.addField(343, currentY);

        double currentX = 343;
        this.addField(currentX, 190);
        this.addField(currentX, 155);
        this.addField(currentX, 120);
        this.addField(currentX, 90);

        currentY = 90;
        this.addField(378, currentY);
        this.addField(412, currentY);

        currentX = 412;
        this.addField(currentX, 122);
        this.addField(currentX, 152);
        this.addField(currentX, 185);
        this.addField(currentX, 225);

        currentY = 225;
        this.addField(442, currentY);
        this.addField(476, currentY);
        this.addField(510, currentY);
        this.addField(542, currentY);

        currentX = 542;
        this.addField(currentX, 260);
        this.addField(currentX, 295);

        currentY = 295;
        this.addField(510, currentY);
        this.addField(476, currentY);
        this.addField(442, currentY);
        this.addField(412, currentY);

        currentX = 412;
        this.addField(currentX, 327);
        this.addField(currentX, 359);
        this.addField(currentX, 392);
        this.addField(currentX, 427);

        currentY = 427;
        this.addField(379, currentY);
        this.addField(343, currentY);

        currentX = 343;
        this.addField(currentX, 392);
        this.addField(currentX, 359);
        this.addField(currentX, 327);
        this.addField(currentX, 295);

        currentY = 295;
        this.addField(304, currentY);
        this.addField(271, currentY);
        this.addField(240, currentY);
        this.addField(211, currentY);

        this.addField(211, 262);

        this.addBlueOuterField(507,88);
        this.addBlueOuterField(542.5,88);
        this.addBlueOuterField(507,123);
        this.addBlueOuterField(542.5,123);

        this.addYellowOuterField(209,392);
        this.addYellowOuterField(243,392);
        this.addYellowOuterField(209,430);
        this.addYellowOuterField(243,430);

        this.addRedOuterField(208,90);
        this.addRedOuterField(243,90);
        this.addRedOuterField(208,123);
        this.addRedOuterField(243,123);

        this.addGreenOuterField(508,392);
        this.addGreenOuterField(543,392);
        this.addGreenOuterField(508,430);
        this.addGreenOuterField(543,430);

        this.addBlueInnerField(376.5,124);
        this.addBlueInnerField(376.5,157);
        this.addBlueInnerField(376.5,190);
        this.addBlueInnerField(376.5,223);

        this.addYellowInnerField(377,395);
        this.addYellowInnerField(377,361);
        this.addYellowInnerField(377,327);
        this.addYellowInnerField(377,295);

        this.addRedInnerField(242,260);
        this.addRedInnerField(276,260);
        this.addRedInnerField(310,260);
        this.addRedInnerField(342,260);

        this.addGreenInnerField(511,260);
        this.addGreenInnerField(479,260);
        this.addGreenInnerField(445,260);
        this.addGreenInnerField(411,260);

        FieldPositions.yellowPlayerFields = this.createPlayerFields(this.yellowStartPosition);
        FieldPositions.bluePlayerFields = this.createPlayerFields(this.blueStartPosition);
        FieldPositions.redPlayerFields = this.createPlayerFields(this.redStartPosition);
        FieldPositions.greenPlayerFields = this.createPlayerFields(this.greenStartPosition);
    }

    private HashMap<Integer, Coordinate> createPlayerFields(int startIndex) {
        int playerIndex = 1;
        HashMap<Integer, Coordinate> playerFields = new HashMap<>();
        for (int index = startIndex; index <= FieldPositions.LAST_GAME_FIELD_POSITION; index++) {
            playerFields.put(playerIndex, this.gameFields.get(index));

            playerIndex++;
        }

        for (int index = 1; index < startIndex; index++) {
            playerFields.put(playerIndex, this.gameFields.get(index));

            playerIndex++;
        }

        return playerFields;
    }

    public static Map<Integer, Coordinate> getFieldsByPlayerColor(Color color) {
        if (color == Color.BLUE) {
            Map<Integer, Coordinate> allBluePlayerFields = FieldPositions.bluePlayerFields;
            allBluePlayerFields.putAll(FieldPositions.blueInnerFields);
            return allBluePlayerFields;
        }
        else if (color == Color.RED) {
            Map<Integer, Coordinate> allRedPlayerFields = FieldPositions.redPlayerFields;
            allRedPlayerFields.putAll(FieldPositions.redInnerFields);
            return allRedPlayerFields;
        }
        else if (color == Color.YELLOW) {
            Map<Integer, Coordinate> allYellowPlayerFields = FieldPositions.yellowPlayerFields;
            allYellowPlayerFields.putAll(FieldPositions.yellowInnerFields);
            return allYellowPlayerFields;
        }
        else {
            Map<Integer, Coordinate> allGreenPlayerFields = FieldPositions.greenPlayerFields;
            allGreenPlayerFields.putAll(FieldPositions.greenInnerFields);
            return allGreenPlayerFields;
        }
    }

    private void addField(double x, double y) {
        this.gameFields.put(this.fieldCounter, new Coordinate(x, y));

        this.fieldCounter++;
    }

    private void addBlueOuterField(double x, double y){
        this.blueOuterFields.put(this.blueFieldCounter,new Coordinate(x,y));
        this.blueFieldCounter++;
    }

    private void addYellowOuterField(double x, double y){
        this.yellowOuterFields.put(this.yellowFieldCounter,new Coordinate(x,y));
        this.yellowFieldCounter++;
    }

    private void addRedOuterField(double x, double y){
        this.redOuterFields.put(this.redFieldCounter,new Coordinate(x,y));
        this.redFieldCounter++;
    }

    private void addGreenOuterField(double x, double y){
        this.greenOuterFields.put(this.greenFieldCounter,new Coordinate(x,y));
        this.greenFieldCounter++;
    }

    private void addBlueInnerField(double x, double y){
        this.blueInnerFields.put(this.blueInnerFieldCounter,new Coordinate(x,y));
        this.blueInnerFieldCounter++;
    }

    private void addYellowInnerField(double x, double y){
        this.yellowInnerFields.put(this.yellowInnerFieldCounter,new Coordinate(x,y));
        this.yellowInnerFieldCounter++;
    }

    private void addRedInnerField(double x, double y){
        this.redInnerFields.put(this.redInnerFieldCounter,new Coordinate(x,y));
        this.redInnerFieldCounter++;
    }

    private void addGreenInnerField(double x, double y){
        this.greenInnerFields.put(this.greenInnerFieldCounter,new Coordinate(x,y));
        this.greenInnerFieldCounter++;
    }

    public Map<Integer,Coordinate> getOuterFieldCoordinates(Color color){
        if(color == Color.BLUE){
            return blueOuterFields;
        }
        else if(color == Color.YELLOW){
            return yellowOuterFields;
        }
        else if(color == Color.RED){
            return redOuterFields;
        }
        else if(color == Color.GREEN){
            return greenOuterFields;
        }
        return null;
    }

    public Map<Integer, Coordinate> getInnerFieldCoordinates(Color color) {
        if(color == Color.BLUE){
            return blueInnerFields;
        }
        else if(color == Color.YELLOW){
            return yellowInnerFields;
        }
        else if(color == Color.RED){
            return redInnerFields;
        }
        else if(color == Color.GREEN){
            return greenInnerFields;
        }
        return null;
    }
}
