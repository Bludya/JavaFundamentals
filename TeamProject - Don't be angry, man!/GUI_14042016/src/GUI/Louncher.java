package GUI;

import core.Coordinate;
import core.FieldPositions;
import core.GamePhase;
import interfaces.Engine;
import interfaces.Pawn;
import interfaces.Player;
import interfaces.State;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.util.Duration;
import models.GameEngine;
import models.GamePlayer;
import models.GameState;

import java.util.ArrayList;
import java.util.List;

public class Louncher extends Application {

    private Engine gameEngine;
    private FieldPositions fieldPositions;

    private Group root;
    private int diceAmount;

    private final Font DEFAULT_FONT = Font.font("Times New Roman", FontWeight.BOLD, 20);

    private Button btnGreenPawn;
    private Button btnRedPawn;
    private Button btnYellowPawn;
    private Button btnBluePawn;

    private Button dice;
    private Button play;
    private Button newGame;
    private  Label playerWithTurn;
    private GraphicsContext graphicsContext;


    public Louncher() {

        State gameState = new GameState();
        this.fieldPositions = new FieldPositions();
        this.gameEngine = new GameEngine(gameState, this.fieldPositions);

        this.diceAmount = 0;
    }

    DropShadow shadow = new DropShadow();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) {

        theStage.setTitle("Don't be angry!");
        this.root = new Group();
        Scene theScene = new Scene(root);

        theStage.setScene(theScene);

        Canvas canvas = new Canvas(800, 600);
        this.root.getChildren().add(canvas);

        this.graphicsContext = canvas.getGraphicsContext2D();

        theScene.setFill(Color.LIGHTSLATEGRAY);
        this.graphicsContext.setFill(Color.BLACK);
        this.graphicsContext.setStroke(Color.BLACK);
        this.graphicsContext.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        this.graphicsContext.setFont(theFont);
        this.graphicsContext.fillText("Don't be angry!", 230, 60);
        this.graphicsContext.strokeText("Don't be angry!", 230, 60);

        Image board = new Image("Board.png");
        this.graphicsContext.drawImage(board, 200, 100);

        this.dice = new Button("", new ImageView(new Image("oneDice.png")));
        btnMouseEvent(dice,shadow);
        this.dice.setLayoutX(350);
        dice.setLayoutY(500);
        this.root.getChildren().add(dice);
        this.dice.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    int tempResult = throwDice();
                    this.diceAmount = tempResult%10;
                    updateDiceResult(diceAmount);
                    if(tempResult>6){
                        dice.setMouseTransparent(false);
                    }
                });
        this.dice.setVisible(false);

        this.setCurrentPlayerLabel();

        this.play = new Button();
        this.play.setText("PLAY");
        this.play.setPrefSize(100,40);
        this.play.setStyle("-fx-text-fill: black; -fx-padding: 10px;");
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 20);
        this.play.setFont(font);
        btnMouseEvent(play,shadow);
        this.play.setLayoutX(650);
        this.play.setLayoutY(200);
        this.root.getChildren().add(play);
        this.play.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> clickPlay());

        this.newGame = new Button();
        this.newGame.setText("NEW GAME");
        this.newGame.setPrefSize(100,40);
        this.newGame.setStyle("-fx-text-fill: black; -fx-padding: 10px;");
        font = Font.font("Times New Roman", FontWeight.BOLD, 12);
        this.newGame.setFont(font);
        btnMouseEvent(this.newGame,this.shadow);
        this.newGame.setLayoutX(650);
        this.newGame.setLayoutY(250);
        this.root.getChildren().add(this.newGame);
        this.newGame.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> clickNewGame());


        //The blue pawn also has the shadow effect. This effect could be set when an exact player is on turn.
        // To be put the final pawn class:

        this.btnRedPawn = new Button("", new ImageView("redPawn.png"));
        btnMouseEvent(this.btnRedPawn,this.shadow);
        this.btnRedPawn.setLayoutX(100);
        this.btnRedPawn.setLayoutY(180); //300
        this.btnRedPawn.setOnMouseClicked(event -> {
            Player redPlayer = new GamePlayer("Red Player",Color.RED);
            this.gameEngine.getGameState().addPlayerToGame(redPlayer);
            placePawns(redPlayer);
            btnRedPawn.setVisible(false);
        });
        this.root.getChildren().add(btnRedPawn);

        this.btnBluePawn = new Button("", new ImageView("bluePawn.png"));
        btnMouseEvent(btnBluePawn,shadow);
        this.btnBluePawn.setLayoutX(100);
        this.btnBluePawn.setLayoutY(240);
        this.btnBluePawn.setOnMouseClicked(event -> {
            Player bluePlayer = new GamePlayer("Blue Player",Color.BLUE);
            this.gameEngine.getGameState().addPlayerToGame(bluePlayer);
            placePawns(bluePlayer);
            btnBluePawn.setVisible(false);
        });
        this.root.getChildren().add(btnBluePawn);

        this.btnGreenPawn = new Button("", new ImageView("greenPawn.png"));
        btnMouseEvent(btnGreenPawn,shadow);
        this.btnGreenPawn.setLayoutX(100);
        this.btnGreenPawn.setLayoutY(300);
        this.btnGreenPawn.setOnMouseClicked(event -> {
            Player greenPlayer = new GamePlayer("Green Player",Color.GREEN);
            this.gameEngine.getGameState().addPlayerToGame(greenPlayer);
            placePawns(greenPlayer);
            this.btnGreenPawn.setVisible(false);
        });
        this.root.getChildren().add(btnGreenPawn);

        this.btnYellowPawn = new Button("", new ImageView("yellowPawn.png"));
        btnMouseEvent(this.btnYellowPawn, this.shadow);
        this.btnYellowPawn.setLayoutX(100);
        this.btnYellowPawn.setLayoutY(360);
        this.btnYellowPawn.setOnMouseClicked(event -> {
            Player yellowPlayer = new GamePlayer("Yellow Player",Color.YELLOW);
            this.gameEngine.getGameState().addPlayerToGame(yellowPlayer);
            placePawns(yellowPlayer);
            this.btnYellowPawn.setVisible(false);
        });
        root.getChildren().add(btnYellowPawn);

        theStage.show();
    }

    private void placePawns(Player player) {
        for(Pawn pawn : player.getPawns().values()){
            root.getChildren().add(pawn.getImage());
        }
    }


    public static void btnMouseEvent(Button  btn, DropShadow shadow) {
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> {
                    btn.setEffect(shadow);
                    shadow.setOffsetX(15.0);
                    shadow.setOffsetY(2.0);
                    btn.setEffect(shadow);
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> btn.setEffect(null));

    }

    private void updateDiceResult(int diceValue){

        switch (diceValue){
            case 1: dice.setGraphic(new ImageView(new Image("oneDice.png")));
                break;
            case 2: dice.setGraphic(new ImageView(new Image("twoDice.png")));
                break;
            case 3: dice.setGraphic(new ImageView(new Image("threeDice.png")));
                break;
            case 4: dice.setGraphic(new ImageView(new Image("fourDice.png")));
                break;
            case 5: dice.setGraphic(new ImageView(new Image("fiveDice.png")));
                break;
            case 6: dice.setGraphic(new ImageView(new Image("sixDice.png")));
                break;
        }
    }

    private int throwDice() {
        int diceResult = this.gameEngine.throwDice();

        //unblocking pawns so they can move.
        gameEngine.getGameState()
                .getCurrentPlayer()
                .blockPawns(false);

        dice.setMouseTransparent(true);

        this.displayCurrentPlayer();

        return diceResult;
    }

    private  void clickPlay(){
        State gameState = this.gameEngine.getGameState();

        if (gameState.getAllPlayers().size() < 2) {
            return;
        }

        for(Player player : gameState.getAllPlayers()){
            for(Pawn pawn : player.getPawns().values()){
                pawn.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            Player currentPlayer = gameState.getCurrentPlayer();


                            if(player == currentPlayer) {
                                if (pawn.getGamePhase() == GamePhase.OutOfGame && this.diceAmount == 6) {
                                    pawn.setGamePhase(GamePhase.InGame);


                                    this.gameEngine.movePawn(pawn, 1);

                                    //blocking the pawns so they can't be moved. Unblocking happens in throwDice();
                                    player.blockPawns(true);

                                    //unblocking the dice
                                    dice.setMouseTransparent(false);
                                }

                                else if (pawn.getGamePhase() == GamePhase.InGame || pawn.getGamePhase() == GamePhase.InnerFields) {
                                    if(this.gameEngine.movePawn(pawn, pawn.getPosition() + this.diceAmount) == true){

                                        //blocking the pawns so they can't be moved.
                                        player.blockPawns(true);

                                        //unblocking the dice.
                                        this.dice.setMouseTransparent(false);
                                    }
                                    else{
                                        //implemented so a player isn't switched if pawn isn't moved.
                                        return;
                                    }

                                }
                                else{
                                    return;
                                }


                                checkForClashes(pawn);

                                // switch players unless 6 was thrown
                                if (this.diceAmount != 6) {
                                    this.gameEngine
                                            .getGameState()
                                            .changeCurrentPlayer();
                                }

                                for (Pawn currentPlayerPawn : currentPlayer.getPawns().values()) {
                                    currentPlayerPawn.getImage().setEffect(null);
                                }

                                Color winnerColor = getWinnerColor();
                                if (winnerColor != null) {
                                    displayWinner(winnerColor);
                                    root.getChildren().remove(dice);
                                }
                            }
                        }
                );
            }
        }
        play.setVisible(false);
        btnBluePawn.setVisible(false);
        btnGreenPawn.setVisible(false);
        btnRedPawn.setVisible(false);
        btnYellowPawn.setVisible(false);
        dice.setVisible(true);

        gameState.setGamePhase("started");
        gameState.changeCurrentPlayer();
    }

    private void displayStackedPawns(Pawn pawn, int numberOfPawns) {

        Coordinate textPosition = pawn.getCoordinates();
        final Label textField = new Label ();
        textField.setText(String.format("%d", numberOfPawns));
        textField.setFont(this.DEFAULT_FONT);
        textField.setLayoutX(textPosition.getPositionX()+8);
        textField.setLayoutY(textPosition.getPositionY()+15);
        textField.setVisible(true);
        final EventDispatcher initial = textField.getEventDispatcher();
        textField.setEventDispatcher((event, tail) -> {
            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent)event;
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.isControlDown()) {
                    event.consume();
                }
            }
            return initial.dispatchEvent(event, tail);
        });

        textField.setOnMouseClicked(event -> {
            if(textField.getText().equals("2")){
                root.getChildren().remove(textField);
            }
            else{
                textField.setText(String.format("%d",Integer.parseInt(textField.getText())-1));
            }
        });
        root.getChildren().add(textField);
    }

    private  void clickNewGame(){
        btnBluePawn.setVisible(true);
        btnGreenPawn.setVisible(true);
        btnRedPawn.setVisible(true);
        btnYellowPawn.setVisible(true);
        play.setVisible(true);
        dice.setVisible(false);

        for(Player player : this.gameEngine.getGameState().getAllPlayers()){
            for(Pawn pawn : player.getPawns().values()){
                root.getChildren().removeAll(pawn.getImage());
            }
        }
        this.gameEngine.setGameState(new GameState());
    }

    private Color getWinnerColor() {
        List<Player> allPlayers = this.gameEngine.getGameState().getAllPlayers();

        boolean isPlayerWon = allPlayers
                .stream().anyMatch(
                        player -> player.getPawns().values().stream().allMatch(
                                pawn -> pawn.getGamePhase() == GamePhase.ThroughGame
                        ));

        if (isPlayerWon) {
            Player playerWithPawnsThrough = allPlayers.stream()
                    .filter(
                            player -> player.getPawns().values().stream()
                                    .allMatch(
                                            pawn -> pawn.getGamePhase() == GamePhase.ThroughGame))
                    .findFirst()
                    .get();

            return playerWithPawnsThrough.getColor();
        }

        return null;
    }

    private void displayWinner(Color winnerColor){
        String winnerColorString;
        if (winnerColor == Color.RED) {
            winnerColorString = "red";
        }
        else if (winnerColor == Color.BLUE) {
            winnerColorString = "blue";
        }
        else if (winnerColor == Color.YELLOW) {
            winnerColorString = "yellow";
        }
        else {
            winnerColorString = "green";
        }

        Label label = new Label("The winner is " + winnerColorString + " player!");
        label.setStyle("-fx-text-fill: blue; -fx-padding: 10px;");
        Font otherFont = Font.font("Times New Roman", FontWeight.BOLD, 30);
        label.setFont(otherFont);
        label.setLayoutX(210);
        label.setLayoutY(60);

        Timeline blinker = createBlinker(label);
        SequentialTransition labelBlink = new SequentialTransition(
                label,
                blinker
        );

        root.getChildren().remove(this.playerWithTurn);
        root.getChildren().add(label);

        labelBlink.play();
    }

    public Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(
                                node.opacityProperty(),
                                0,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                )
        );
        blink.setCycleCount(6);

        return blink;
    }

    private String getCurrentPlayerColorAsString() {
        Color currentPlayerColor = this.gameEngine.getGameState().getCurrentPlayer().getColor();

        String colorString;
        if (currentPlayerColor == Color.BLUE) {
            colorString = "blue";
        }
        else if (currentPlayerColor == Color.RED){
            colorString = "red";
        }
        else if (currentPlayerColor == Color.GREEN) {
            colorString = "green";
        }
        else {
            colorString = "yellow";
        }

        return colorString;
    }

    private void setCurrentPlayerLabel() {
        this.playerWithTurn = new Label("");
        this.playerWithTurn.setStyle("-fx-text-fill: blue; -fx-padding: 10px;");
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 30);
        this.playerWithTurn.setFont(font);
        this.playerWithTurn.setLayoutX(300);
        this.playerWithTurn.setLayoutY(60);
        this.playerWithTurn.setVisible(true);

        this.root.getChildren().add(this.playerWithTurn);
    }

    private void displayCurrentPlayer() {
        String currentPlayerString = this.getCurrentPlayerColorAsString();

        this.playerWithTurn.setText(currentPlayerString + "'s turn");
    }

    private void checkForClashes(Pawn pawn) {
        // pawns which the moving pawn would clash with
        ArrayList<Pawn> clashingPawns = (ArrayList) this.gameEngine.getGameState()
                .getAllPawnsAtPosition(pawn.getCoordinates());

        clashingPawns.remove(pawn);


        if (clashingPawns.size() > 0) {
            Pawn pawnAtPosition = clashingPawns.stream().findFirst().get();

            // check if pawn at position belongs to current player
            if (this.gameEngine.getGameState().getCurrentPlayer().getPawns().containsValue(pawnAtPosition)) {
                // buggy:
                displayStackedPawns(pawn, clashingPawns.size() + 1);
            } else {
                if (clashingPawns.size() == 1) {

                    pawnAtPosition.setGamePhase(GamePhase.OutOfGame);
                    pawnAtPosition.returnToPlayer();

                } else if (clashingPawns.size() > 1) {
                    pawn.returnToPlayer();
                }
            }
        }
    }
}