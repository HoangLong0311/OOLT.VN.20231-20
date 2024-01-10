package Application.Part;

//Lớp Game biểu diễn trạng thái và logic của trò chơi
public class Game {
private Board board;
private Player currentPlayer;
private GameState gameState;

public Game() {
   board = new Board(8); // Giả sử bàn cờ có kích thước 8x8
   currentPlayer = new Player();
   gameState = GameState.RUNNING; // Giả sử trò chơi bắt đầu ở trạng thái chạy
}

public void startNewGame() {
    // Đặt lại bàn cờ với các ô trống
    grid = new Square[5][5];
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = new Square();
        }
    }

    // Khởi tạo người chơi và gán màu cho họ
    Player player1 = new Player();
    player1.chooseColor(Color.WHITE);
    Player player2 = new Player();
    player2.chooseColor(Color.BLACK);

    // Gán người chơi hiện tại là người chơi 1
    currentPlayer = player1;

    // Đặt lại trạng thái của trò chơi
    gameState = GameState.RUNNING;

    // Đặt các quân cờ vào vị trí ban đầu của chúng
    for (int i = 0; i < 5; i++) {
        board.getSquare(4, i).occupy(new Piece(Color.WHITE));
        board.getSquare(0, i).occupy(new Piece(Color.BLACK));
    }

    // Bạn có thể thêm các quy tắc khởi đầu khác tùy thuộc vào luật của trò chơi bạn đang phát triển
    // ...
}

public boolean makeMove(Move move) {
   // Logic thực hiện nước đi
   return true; // Trả về true nếu nước đi hợp lệ
}

public void endGame() {
   // Logic kết thúc trò chơi
}
}