package projectgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectGame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Memuat Menu.fxml saat aplikasi pertama kali dijalankan
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Catch Falling Objects");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}