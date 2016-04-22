package interfaces;

import core.Coordinate;
import core.GamePhase;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.Serializable;

public interface Pawn extends Serializable {
    int getPosition();

    int canMove(int amount);

    Coordinate getCoordinates();

    boolean move(int newPosition);

    //От Вальо: Добавям този метод вместо move(), тъй кто имам нужда да мога директно да поставям позицията на пионките които ползвам
    //аз смятам преместването в класа GamePlayer и от там директно ще сетвам позицията на пионката.
    void setPosition(int position, Coordinate coordinates);

    GamePhase getGamePhase();

    Color getColor();

    void setGamePhase(GamePhase phase);

    ImageView getImage();

    void returnToPlayer();
}
