package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter4.Launcher;
import se233.chapter4.view.GameStage;

public class GameCharacter extends Pane {
    private static final Logger logger = LogManager.getLogger(GameCharacter.class);
    // Incorporate an additional character into the application. - BEGIN - 1/9
    public int CHARACTER_WIDTH;
    public int CHARACTER_HEIGHT;
    // Incorporate an additional character into the application. - END - 1/9
    private Image gameCharacterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    int xAcceleration;
    int yAcceleration;
    int xMaxVelocity;
    int yMaxVelocity;

    public Image getGameCharacterImg() {
        return gameCharacterImg;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public boolean isFalling() {
        return isFalling;
    }

    // Incorporate an additional character into the application. - BEGIN - 2/9
    public GameCharacter(int CHARACTER_WIDTH, int CHARACTER_HEIGHT, int x, int y, int xAcceleration, int yAcceleration, int xMaxVelocity, int yMaxVelocity, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, Image characterSprite, int count, int columns, int rows, int width, int height) {
        this.CHARACTER_WIDTH = CHARACTER_WIDTH;
        this.CHARACTER_HEIGHT = CHARACTER_HEIGHT;
        this.x = x;
        this.y = y;
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
        this.xMaxVelocity = xMaxVelocity;
        this.yMaxVelocity = yMaxVelocity;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.gameCharacterImg = characterSprite;
        this.imageView = new AnimatedSprite(characterSprite, count, columns, rows, offsetX, offsetY, width, height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }
    // Incorporate an additional character into the application. - END - 2/9

    public void moveLeft() {
        setScaleX(-1);
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight() {
        setScaleX(1);
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }

    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if (isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public void repaint() {
        moveX();
        moveY();
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
            // Display a message when a character moves and collides with the game boundary - 1/2
            logger.info("Game boundary reached.");
        } else if (x + getWidth() >= GameStage.WIDTH) {
            x = GameStage.WIDTH - (int)getWidth();
            // Display a message when a character moves and collides with the game boundary - 2/2
            logger.info("Game boundary reached.");
        }
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if (isFalling && y >= GameStage.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
    }
}
