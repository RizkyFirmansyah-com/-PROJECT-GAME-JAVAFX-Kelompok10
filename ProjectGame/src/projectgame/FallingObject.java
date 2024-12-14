// FallingObject Class
package projectgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FallingObject extends GameObject {
    private double speed;
    private String type;
    private Timeline timeline;

    public FallingObject(ImageView imageView, double speed, String type) {
        super(imageView); // Memanggil konstruktor dari GameObject
        this.speed = speed / 2; // Mengurangi kecepatan jatuh
        this.type = type;

        // Atur ukuran objek
        imageView.setFitWidth(30); // Lebar objek
        imageView.setFitHeight(30); // Tinggi objek

        // Inisialisasi timeline untuk animasi
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> updatePosition()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Animasi berjalan terus
        timeline.play(); // Memulai animasi
    }

    public void updatePosition() {
        // Update posisi objek
        imageView.setLayoutY(imageView.getLayoutY() + speed);

        // Opsional: Reset posisi ke atas jika mencapai bawah layar
        if (imageView.getLayoutY() > 600) { // Asumsi tinggi layar 600
            imageView.setLayoutY(0);
        }
    }

    public String getType() {
        return type;
    }

    // Metode untuk menghentikan animasi jika diperlukan
    public void stopFalling() {
        timeline.stop();
    }
    public void startFalling() {
    timeline.play();
}

}
