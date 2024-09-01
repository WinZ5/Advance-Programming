package se233.chapter4.controller;

import se233.chapter4.model.GameCharacter;
import se233.chapter4.view.GameStage;

public class GameLoop implements Runnable {
    private GameStage gameStage;
    private int frameRate;
    private float interval;
    private boolean running;

    public GameLoop(GameStage gameStage) {
        this.gameStage = gameStage;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }

    private void update(GameCharacter gameCharacter) {
        boolean leftPressed = gameStage.getKeys().isPressed(gameCharacter.getLeftKey());
        boolean rightPressed = gameStage.getKeys().isPressed(gameCharacter.getRightKey());
        boolean upPressed = gameStage.getKeys().isPressed(gameCharacter.getUpKey());

        if (leftPressed && rightPressed) {
            gameCharacter.stop();
        } else if (leftPressed) {
            gameCharacter.getImageView().tick();
            gameCharacter.moveLeft();
            gameStage.getMario().trace();
            // Incorporate an additional character into the application. - 5/9
            gameStage.getRockman().trace();
        } else if (rightPressed) {
            gameCharacter.getImageView().tick();
            gameCharacter.moveRight();
            gameStage.getMario().trace();
            // Incorporate an additional character into the application. - 6/9
            gameStage.getRockman().trace();
        } else {
            gameCharacter.stop();
        }

        if (upPressed) {
            gameCharacter.jump();
        }
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(gameStage.getMario());
            // Incorporate an additional character into the application. - 7/9
            update(gameStage.getRockman());
            time = System.currentTimeMillis() - time;
            if (time < interval * 1000) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
