package projectgame;

import javafx.scene.image.ImageView;

public class Character {
    private ImageView characterImage;

    public Character(ImageView imageView) {
        this.characterImage = imageView;
    }

    public ImageView getCharacterImage() {
        return characterImage;
    }

//   public void moveLeft() {
//   
//    double newX = characterImage.getLayoutX() - 10;
//    if (newX >= 0) {
//        characterImage.setLayoutX(newX);
//        System.out.println("New X Position: " + characterImage.getLayoutX());
//    }
//}
//
//public void moveRight() {
//   
//    if (characterImage.getParent() != null) {
//        double maxWidth = characterImage.getParent().getLayoutBounds().getWidth() - characterImage.getFitWidth();
//        double newX = characterImage.getLayoutX() + 10;
//        if (newX <= maxWidth) {
//            characterImage.setLayoutX(newX);
//            System.out.println("New X Position: " + characterImage.getLayoutX());
//        }
//    }
//}
}