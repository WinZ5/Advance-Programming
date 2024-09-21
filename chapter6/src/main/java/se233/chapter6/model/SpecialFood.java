package se233.chapter6.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

// introduce special food items worth five points - 1/9
public class SpecialFood extends Food {
    private static final int POINTS = 5;
    private static final Color COLOR = Color.GREEN;

    public SpecialFood(Point2D position) {
        super(position);
    }

    public SpecialFood() {
        super();
    }
}

