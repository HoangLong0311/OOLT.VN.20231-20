package Application.Part;

//Lớp Board biểu diễn bàn cờ
public class Board {
private Square[][] grid;

public Board(int size) {
   grid = new Square[size][size];
   setupBoard();
}

private void setupBoard() {
   // Khởi tạo các ô trống trên bàn cờ
   for (int i = 0; i < grid.length; i++) {
       for (int j = 0; j < grid[i].length; j++) {
           grid[i][j] = new Square();
       }
   }
}

public Square getSquare(int x, int y) {
   return grid[x][y];
}
}