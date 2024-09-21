package se233.chapter6.view;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;
import se233.chapter6.model.SpecialFood;

public class GameStage extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;
    // Introduce a scoring mechanism - 2/10
    private Score score;

    public GameStage() {
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        this.getChildren().add(canvas);
        // Introduce a scoring mechanism - 3/10
        score = new Score(GameStage.WIDTH - 10, GameStage.HEIGHT);
        this.getChildren().add(score);
    }
    public void render(Snake snake, Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });
        gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    // introduce special food items worth five points - 2/9
    public void renderSpecialFood(SpecialFood specialFood) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(specialFood.getPosition().getX() * TILE_SIZE, specialFood.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    // create a popup window to display a “Game Over” message upon the snake character’s death. - START - 1/2
    // collided_snakeHitBorder_shouldDie() in GameLoopTest also has to be modify it somehow lead to error that i can't fix
    public void showDeathMessage() {
        Platform.runLater(() -> {
            Alert popup = new Alert(Alert.AlertType.INFORMATION);
            popup.setTitle("Game Over");
            popup.setHeaderText(null);
            popup.setContentText("Game Over");
            popup.showAndWait();
            Platform.exit();
        });
    }
    // create a popup window to display a “Game Over” message upon the snake character’s death. - END - 1/2

    // create a popup window to display a “Game Over” message upon the snake character’s death. - END - 1/2
    public KeyCode getKey() { return key; }
    public void setKey(KeyCode key) { this.key = key; }
    // Introduce a scoring mechanism - 4/10
    public Score getScore() {
        return score;
    }
}
