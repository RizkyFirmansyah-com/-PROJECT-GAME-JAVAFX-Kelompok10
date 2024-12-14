//MenuController
package projectgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController {

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;
    
    private MediaPlayer menuBgmPlayer;
    
    private void playMenuBgm() {
    String path = getClass().getResource("/projectgame/GUI/bgmMenu.mp3").toString();
    Media menuBgm = new Media(path);
    menuBgmPlayer = new MediaPlayer(menuBgm);
    menuBgmPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Musik di-loop
    menuBgmPlayer.play();
}

private void stopMenuBgm() {
    if (menuBgmPlayer != null) {
        menuBgmPlayer.stop();
    }
}
    
    private void playSound(String fileName) {
    String path = getClass().getResource("/projectgame/GUI/" + fileName).toString();
    Media sound = new Media(path);
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
}

    @FXML
public void initialize() {
    playMenuBgm(); // Mulai musik menu
}
    
    // Menangani klik tombol Start
    @FXML
    public void handleStartButton(MouseEvent event) {
        stopMenuBgm();
        playSound("bubleClickButton.mp3"); // Tambahkan efek suara klik button
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
        stopMenuBgm();
        playSound("bubleClickButton.mp3"); // Tambahkan efek suara klik button
        System.out.println("Quit button clicked");  // Debugging, pastikan klik terdeteksi
        System.exit(0); // Keluar dari aplikasi
    }
}