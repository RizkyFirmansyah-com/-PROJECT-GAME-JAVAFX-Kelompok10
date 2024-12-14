// Abstract Class for GameObject
package projectgame;

import javafx.scene.image.ImageView;

public abstract class GameObject {
    protected ImageView imageView;

    private static int objectCount = 0;

    public GameObject(ImageView imageView) {
        this.imageView = imageView;
        objectCount++; 
    }


    public static int getObjectCount() {
        return objectCount;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public abstract void updatePosition();
}
