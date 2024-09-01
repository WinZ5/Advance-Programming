package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.GameCharacter;
import se233.chapter4.model.Keys;

public class GameStage extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Image gameStageImg;
    private GameCharacter mario;
    private GameCharacter rockman;
    private Keys keys;

    public GameStage() {
        keys = new Keys();
        gameStageImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(gameStageImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        // Incorporate an additional character into the application. - BEGIN
        mario = new GameCharacter(32, 64, 30, 30, 1, 1, 7, 17, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W, new Image(Launcher.class.getResourceAsStream("assets/MarioSheet.png")), 4, 4, 1, 16, 32);
        rockman = new GameCharacter(64, 64, 30, 30, 2,2, 14,34,0, 0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, new Image(Launcher.class.getResourceAsStream("assets/rockman.png")), 10, 5, 2, 540, 512);
        getChildren().addAll(backgroundImg, mario, rockman);
        // Incorporate an additional character into the application. - END
    }

    public GameCharacter getMario() { return mario; }
    // Incorporate an additional character into the application.
    public GameCharacter getRockman() { return rockman; }
    public Keys getKeys() { return keys; }
}
