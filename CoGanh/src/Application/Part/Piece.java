package Application.Part;

//Lớp Piece biểu diễn một quân cờ
public class Piece {
	private Color color;

	public Piece(Color color) {
		this.color = color;
		}

	public Color getColor() {
		return color;
		}
	public void setColor(Color newColor) {
		this.color = newColor;
	}
}