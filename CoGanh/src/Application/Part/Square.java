package Application.Part;

//Lớp Square biểu diễn một ô trên bàn cờ
public class Square {
private Piece piece;

public void occupy(Piece piece) {
   this.piece = piece;
}

public void release() {
   this.piece = null;
}

public Piece getPiece() {
   return piece;
}
}