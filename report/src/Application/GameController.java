package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameController {

    @FXML
    private Button startButton;

    @FXML
    private GridPane chessBoard;

    private Game game; // Tham chiếu đến lớp Game

    // Hàm khởi tạo của GameController, nhận một tham chiếu đến Game
    public GameController(Game game) {
        this.game = game;
    }

    @FXML
    private void initialize() {
        // Thực hiện các thiết lập ban đầu, nếu cần
    }

    @FXML
    private void handleStartButtonAction() {
        // Xử lý sự kiện khi nút Start được nhấn
        game.startNewGame();
        // Cập nhật giao diện người dùng, nếu cần
    }

    // Các phương thức và sự kiện khác có thể được thêm vào để tương tác với lớp Game

    // Ví dụ:
    // @FXML
    // private void handleChessboardClick(MouseEvent event) {
    //     // Xử lý sự kiện khi người chơi nhấp vào bàn cờ
    // }

    // @FXML
    // private void handleMoveButtonAction() {
    //     // Xử lý sự kiện khi nút di chuyển được nhấn
    // }

    // Và các phương thức/sự kiện khác tùy thuộc vào yêu cầu của trò chơi

}

