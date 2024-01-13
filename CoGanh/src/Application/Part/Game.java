package Application.Part;

import javax.swing.JOptionPane;

//Lớp Game biểu diễn trạng thái và logic của trò chơi
public class Game {
	 	private Player whitePlayer;
	    private Player blackPlayer;
	    private Board board;
	    private Player currentPlayer;
	    private GameState gameState;

public Game() {
   board = new Board(5); // Giả sử bàn cờ có kích thước 5x5
   whitePlayer = new Player();
   blackPlayer = new Player();
   currentPlayer = chooseFirstPlayer();
   gameState = GameState.RUNNING; // Giả sử trò chơi bắt đầu ở trạng thái chạy
}
private Player chooseFirstPlayer() {
    // Logic để cho phép người chơi chọn màu
    // Ví dụ: bạn có thể sử dụng giao diện người dùng hoặc nhập liệu để lấy lựa chọn
    // Giả sử người chơi chọn màu trắng
    whitePlayer.setColor(Color.WHITE);
    blackPlayer.setColor(Color.BLACK);
    return whitePlayer; // Người chơi chọn màu trắng sẽ đi trước
}
public void startNewGame() {
    // Đặt lại bàn cờ với các ô trống
	board = new Board(5);
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
    int fromX = move.getFromX();
    int fromY = move.getFromY();
    int toX = move.getToX();
    int toY = move.getToY();

    // Kiểm tra nước đi có hợp lệ không
    if (!isValidMove(fromX, fromY, toX, toY)) {
        return false;
    }

    // Thực hiện nước đi
    Piece piece = board.getSquare(fromX, fromY).getPiece();
    board.getSquare(fromX, fromY).release();
    board.getSquare(toX, toY).occupy(piece);

    // Kiểm tra và thực hiện gánh hoặc chẹt
    performCapture(toX, toY);

    // Cập nhật người chơi hiện tại
    switchCurrentPlayer();

    // Kiểm tra trạng thái kết thúc trò chơi
    checkGameState();

    return true;
}

private boolean isValidMove(int fromX, int fromY, int toX, int toY) {
    // Kiểm tra xem có quân cờ tại vị trí bắt đầu không
    Piece piece = board.getSquare(fromX, fromY).getPiece();
    if (piece == null) {
        return false; // Không có quân cờ để di chuyển
    }

    // Kiểm tra xem quân cờ có phải là của người chơi hiện tại không
    if (piece.getColor() != currentPlayer.getColor()) {
        return false; // Quân cờ không thuộc về người chơi hiện tại
    }

    // Kiểm tra xem vị trí đích có hợp lệ không (trong phạm vi bàn cờ và không bị chiếm đóng bởi quân cờ cùng màu)
    if (toX < 0 || toX >= board.getSize() || toY < 0 || toY >= board.getSize() ||
        board.getSquare(toX, toY).getPiece() != null && board.getSquare(toX, toY).getPiece().getColor() == currentPlayer.getColor()) {
        return false; // Nước đi không hợp lệ do vị trí đích
    }

    // Kiểm tra di chuyển ngang
    if (fromY == toY && fromX != toX) {
        // Di chuyển ngang hợp lệ
        return true;
    }

    // Kiểm tra di chuyển dọc
    if (fromX == toX && fromY != toY) {
        // Di chuyển dọc hợp lệ
        return true;
    }

    // Kiểm tra di chuyển chéo
    if (Math.abs(toX - fromX) == Math.abs(toY - fromY)) {
        // Di chuyển chéo hợp lệ
        return true;
    }

    // Nếu không phải di chuyển ngang, dọc hoặc chéo, nước đi không hợp lệ
    return false;
}


private void performCapture(int x, int y) {
    // Lấy quân cờ tại vị trí hiện tại
    Piece piece = board.getSquare(x, y).getPiece();
    if (piece == null) {
        return; // Không có quân cờ để gánh hoặc chẹt
    }

    // Lấy màu của quân cờ hiện tại
    Color pieceColor = piece.getColor();

    // Kiểm tra gánh hoặc chẹt theo hàng ngang, dọc và chéo
    checkCaptureInDirection(x, y, 1, 0, pieceColor); // Hàng ngang
    checkCaptureInDirection(x, y, 0, 1, pieceColor); // Hàng dọc
    checkCaptureInDirection(x, y, 1, 1, pieceColor); // Đường chéo xuống
    checkCaptureInDirection(x, y, 1, -1, pieceColor); // Đường chéo lên

    // Cập nhật bàn cờ sau khi gánh hoặc chẹt
    updateBoard();
}

private void checkCaptureInDirection(int x, int y, int dx, int dy, Color pieceColor) {
    int adjacentX = x + dx;
    int adjacentY = y + dy;
    int oppositeX = x + 2 * dx;
    int oppositeY = y + 2 * dy;
    // Kiểm tra xem có thể gánh hoặc chẹt theo hướng đang xét không
    if (isValidPosition(adjacentX, adjacentY) && isValidPosition(oppositeX, oppositeY)) {
        Piece adjacentPiece = board.getSquare(adjacentX, adjacentY).getPiece();
        Piece oppositePiece = board.getSquare(oppositeX, oppositeY).getPiece();
        // Nếu hai quân cờ đối diện có màu khác và quân cờ giữa có màu giống quân cờ hiện tại, thực hiện gánh hoặc chẹt
        if (adjacentPiece != null && oppositePiece != null &&
            adjacentPiece.getColor() != pieceColor && oppositePiece.getColor() == pieceColor) {
            // Đổi màu quân cờ bị gánh hoặc chẹt
            adjacentPiece.setColor(pieceColor);
        }
    }
}

private boolean isValidPosition(int x, int y) {
    return x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize();
}

private void updateBoard() {
    // Duyệt qua tất cả các ô trên bàn cờ
    for (int x = 0; x < board.getSize(); x++) {
        for (int y = 0; y < board.getSize(); y++) {
            Square square = board.getSquare(x, y);
            Piece piece = square.getPiece();
            // Cập nhật trạng thái của ô nếu có quân cờ
            if (piece != null) {
                // Cập nhật trạng thái dựa trên màu sắc của quân cờ
                // Ví dụ: bạn có thể thay đổi biểu tượng hoặc thuộc tính của ô tại đây
                // ...
            }
        }
    }
 // Kiểm tra điều kiện kết thúc trò chơi
    if (checkWinCondition()) {
        gameState = GameState.ENDED;
        // Xác định người chơi chiến thắng dựa trên màu sắc còn lại trên bàn cờ
        Color winnerColor = determineWinnerColor();
        // Thông báo người chơi chiến thắng hoặc kết quả hòa
        announceWinner(winnerColor);
    } else {
        // Nếu trò chơi chưa kết thúc, tiếp tục cập nhật trạng thái hoặc chuẩn bị cho lượt tiếp theo
        switchCurrentPlayer();
    }
}

private boolean checkWinCondition() {
    Color remainingColor = null;
    boolean multipleColors = false;

    // Duyệt qua tất cả các ô trên bàn cờ để kiểm tra màu sắc của quân cờ
    for (int x = 0; x < board.getSize(); x++) {
        for (int y = 0; y < board.getSize(); y++) {
            Piece piece = board.getSquare(x, y).getPiece();
            if (piece != null) {
                if (remainingColor == null) {
                    // Ghi nhận màu sắc đầu tiên
                    remainingColor = piece.getColor();
                } else if (piece.getColor() != remainingColor) {
                    // Nếu có quân cờ màu khác, trò chơi chưa kết thúc
                    multipleColors = true;
                    break;
                }
            }
        }
        if (multipleColors) {
            break;
        }
    }

    // Trả về true nếu trên bàn cờ chỉ còn một màu quân duy nhất
    return !multipleColors;
}

private Color determineWinnerColor() {
    for (int x = 0; x < board.getSize(); x++) {
        for (int y = 0; y < board.getSize(); y++) {
            Piece piece = board.getSquare(x, y).getPiece();
            if (piece != null) {
                return piece.getColor(); // Trả về màu sắc của quân cờ đầu tiên tìm thấy
            }
        }
    }
    return null; // Trường hợp này không xảy ra nếu trò chơi đã kết thúc với một người chiến thắng
}

private void announceWinner(Color winnerColor) {
    if (winnerColor != null) {
        // Thông báo người chơi sở hữu màu sắc này là người chiến thắng
        System.out.println("Chúc mừng! Người chơi với màu " + winnerColor + " là người chiến thắng!");
    } else {
        // Thông báo kết quả hòa nếu cần
        //System.out.println("Trò chơi kết thúc với kết quả hòa!");
    }
}


private void switchCurrentPlayer() {
    // Đổi lượt chơi cho người chơi tiếp theo
    currentPlayer = (currentPlayer.getColor() == Color.WHITE) ? blackPlayer : whitePlayer;
    // Bạn cần đảm bảo rằng bạn đã khởi tạo blackPlayer và whitePlayer trước đó
}
private void checkGameState() {
    // Kiểm tra xem trò chơi có kết thúc không và cập nhật trạng thái
    // ...
}


//Hàm endGame kiểm tra trạng thái của trò chơi
public void endGame() {
 // Kiểm tra xem có người chơi nào chiến thắng hay không
 /*Player winner = checkWin();
 if (winner != null) {
     // Nếu có, hiển thị thông báo và kết thúc trò chơi
     JOptionPane.showMessageDialog(null, "Người chơi " + winner.getColor() + " đã chiến thắng!", "Kết thúc", JOptionPane.INFORMATION_MESSAGE);
     gameState = GameState.ENDED;
     return;
 }*/
 // Kiểm tra xem người chơi có chọn thoát game hay không
 int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát game không?", "Thoát", JOptionPane.YES_NO_OPTION);
 if (option == JOptionPane.YES_OPTION) {
     // Nếu có, hiển thị thông báo và kết thúc trò chơi
     JOptionPane.showMessageDialog(null, "Bạn đã thoát game, cảm ơn đã chơi!", "Thoát", JOptionPane.INFORMATION_MESSAGE);
     gameState = GameState.ENDED;
     return;
 }
 // Nếu không, tiếp tục trò chơi
}

}