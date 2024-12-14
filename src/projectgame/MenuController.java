package projectgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class MenuController {

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    // Menangani klik tombol Start
    @FXML
    public void handleStartButton(MouseEvent event) {
        System.out.println("Start button clicked");  // Debugging, pastikan klik terdeteksi
        try {
            // Memuat tampilan Gameplay.fxml
            Parent root = FXMLLoader.load(getClass().getResource("Gameplay.fxml"));
            Scene gameplayScene = new Scene(root);
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(gameplayScene);  // Mengubah scene menjadi gameplay
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Menangani klik tombol Quit
    @FXML
    public void handleQuitButton(MouseEvent event) {
        System.out.println("Quit button clicked");  // Debugging, pastikan klik terdeteksi
        System.exit(0); // Keluar dari aplikasi
    }
}