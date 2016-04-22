package interfaces;


import core.Coordinate;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Map;

public interface Player extends Serializable {

    //Използвам мап, тъй като е много по - лесен достъпа до пионката.
    Map<Integer,Pawn> getPawns();

    void addPoints();

    //Used to block/unblock pawns so player can move only 1 at a dice throw.
    void blockPawns(boolean block);

    // Боби: Добавям getter за името на играча, за да може да се търсят по име
    String getName();

    // remove pawn at designated position and return it to OutOfGame GamePhase
    //това важи само за пионки в GamePhase.InGame, така че само позиция ми е досатъчна.
    void removePawn(int position);

    //Това не знам за какво би било нужно, н съм го направил да връща булев масив който указва дали е заето или не полето
    //с даден индекс.
    Map<Integer, Boolean> getInnerFields();

    void takeInnerField(int position);

    void leaveInnerField(int position);

    Color getColor();

    Map<Integer, Coordinate> getInnerCoordinates();

    boolean[] getOuterFields();

    void takeOuterField(int position);

    void leaveOuterField(int position);

    Map<Integer,Coordinate> getOuterCoordinates();

    Map<Pawn,Integer> movablePawns(int diceResult);

}
