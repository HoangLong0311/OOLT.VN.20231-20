package Application;

import Application.Part.Game;

//Lớp GameController biểu diễn điều khiển trò chơi
class GameController {
private Game game;

public GameController() {
   game = new Game();
}

public void initializeGame() {
   // Logic khởi tạo trò chơi
}

public void handlePlayerMove(int fromX, int fromY, int toX, int toY) {
   // Logic xử lý nước đi của người chơi
}
}
