package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Tải file FXML và khởi tạo giao diện
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
            BorderPane root = (BorderPane) loader.load();
            
            // Lấy controller từ loader và khởi tạo các thuộc tính hoặc logic
            GameController controller = loader.getController();
            controller.initialize();

            // Tạo và hiển thị scene
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Cờ Gánh Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
