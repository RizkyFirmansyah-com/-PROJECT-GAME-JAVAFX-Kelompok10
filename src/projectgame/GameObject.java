// Abstract Class for GameObject
package projectgame;

import javafx.scene.image.ImageView;

public abstract class GameObject {
    protected ImageView imageView;

    // Tambahkan atribut statis untuk menghitung jumlah objek
    private static int objectCount = 0;

    public GameObject(ImageView imageView) {
        this.imageView = imageView;
        objectCount++; // Tingkatkan jumlah objek setiap kali konstruktor dipanggil
    }

    // Tambahkan metode statis untuk mengakses jumlah objek
    public static int getObjectCount() {
        return objectCount;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public abstract void updatePosition();
}