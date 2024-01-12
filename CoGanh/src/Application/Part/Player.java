package Application.Part;

//Lớp Player biểu diễn người chơi
public class Player {
private Color color;

public void chooseColor(Color color) {
   this.color = color;
}

public Color getColor() {
   return color;
}
}