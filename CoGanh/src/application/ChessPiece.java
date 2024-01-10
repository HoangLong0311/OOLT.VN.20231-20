package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ChessPiece {
    private Circle circle;
    private Color color;

    public ChessPiece(Circle circle, Color color) {
        this.circle = circle;
        this.color = color;
        this.circle.setFill(color);
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.circle.setFill(color);
    }
}
