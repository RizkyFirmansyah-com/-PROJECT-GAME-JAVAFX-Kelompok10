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
        super(imageView); 
        this.speed = speed / 2; 
        this.type = type;

   
        imageView.setFitWidth(30); 
        imageView.setFitHeight(30); 

     
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> updatePosition()));
        timeline.setCycleCount(Timeline.INDEFINITE); 
        timeline.play(); 
    }

    public void updatePosition() {
 
        imageView.setLayoutY(imageView.getLayoutY() + speed);

   
        if (imageView.getLayoutY() > 600) { 
            imageView.setLayoutY(0);
        }
    }

    public String getType() {
        return type;
    }

    public void stopFalling() {
        timeline.stop();
    }
    public void startFalling() {
    timeline.play();
}

}
