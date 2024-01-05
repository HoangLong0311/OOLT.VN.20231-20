package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameController {

    @FXML
    private GridPane board;

    @FXML
    private MenuItem exitMenu;

    private boolean isRedTurn = true; // Màu đỏ đi trước
    private int redCount = 25; // Số quân cờ màu đỏ
    private int blueCount = 25; // Số quân cờ màu xanh

    @FXML
    public void initialize() {
        exitMenu.setOnAction(e -> exitGame());
        initializeBoard();
    }

    private void initializeBoard() {
        // Tạo các ô trống cho bàn cờ
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Circle circle = new Circle(20, Color.TRANSPARENT);
                circle.setStroke(Color.BLACK);
                int row = i, col = j;
                circle.setOnMouseClicked(event -> handleMove(circle, row, col));
                board.add(circle, j, i);
            }
        }
    }

    private void handleMove(Circle circle, int row, int col) {
        if (circle.getFill() == Color.TRANSPARENT) {
            if (isRedTurn) {
                circle.setFill(Color.RED);
                redCount--;
            } else {
                circle.setFill(Color.BLUE);
                blueCount--;
            }
            checkWinCondition();
            isRedTurn = !isRedTurn;
        }
    }

    private void checkWinCondition() {
        if (redCount == 0) {
            endGame("Màu xanh chiến thắng!");
        } else if (blueCount == 0) {
            endGame("Màu đỏ chiến thắng!");
        }
    }

    private void endGame(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kết thúc trò chơi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    private void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có muốn thoát khỏi trò chơi không?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }
}
