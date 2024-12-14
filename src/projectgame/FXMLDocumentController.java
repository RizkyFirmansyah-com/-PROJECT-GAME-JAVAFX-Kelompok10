// FXMLDocumentController.java
package projectgame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController {

    @FXML
    private Pane gameArea;

    @FXML
    private ImageView karakter;

    @FXML
    private Label scoreLabel;

    @FXML
    private Pane LivesBox;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button quitButton1;

    private List<FallingObject> fallingObjects;
    private Character player;
    private int score;
    private int lives;
    private Random random;

    private AnimationTimer gameLoop;
    private boolean isPaused;

    @FXML
    public void initialize() {
        random = new Random();
        resetGame();
    }

    private void resetGame() {
    // Reset game state
    score = 0;
    lives = 3;
    fallingObjects = new ArrayList<>();
    isPaused = false;

    // Set up game area
    gameArea.setFocusTraversable(true);
    gameArea.requestFocus(); // Fokuskan ulang

    // Reset karakter ke posisi awal
    resetCharacterPosition();

    // Hide game over UI elements
    gameOverLabel.setVisible(false);
    restartButton.setVisible(false);
    quitButton1.setVisible(false);

    // Update tampilan
    updateScoreDisplay();
    updateLivesDisplay();

    // Mulai ulang game loop
    startGameLoop();

    // Pasang kembali event handler untuk key press
    gameArea.setOnKeyPressed(this::handleKeyPressed);
}


    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused) {
                    spawnFallingObjects();
                    updateGameObjects();
                    checkCollisions();
                }
            }
        };
        gameLoop.start();
    }

    private long lastSpawnTime = 0;
    private static final long SPAWN_INTERVAL = 1_000_000_000; // 1 detik dalam nanodetik

    private void spawnFallingObjects() {
        long now = System.nanoTime();
        if (fallingObjects.size() >= 10 || (now - lastSpawnTime < SPAWN_INTERVAL)) {
            return;
        }

        if (random.nextInt(100) < 5) { // 5% peluang spawn per frame
            String[] objectTypes = {"apel", "pisang", "mangga", "nanas", "bom"};
            String selectedType = objectTypes[random.nextInt(objectTypes.length)];

            ImageView objectImage = new ImageView(new Image("/projectgame/GUI/" + selectedType + ".png"));
            objectImage.setLayoutX(random.nextInt((int) (gameArea.getPrefWidth() - 50)));
            objectImage.setLayoutY(0);

            double speed = random.nextDouble() * 2.0 + 1.0;
            FallingObject object = new FallingObject(objectImage, speed, selectedType);
            fallingObjects.add(object);
            gameArea.getChildren().add(objectImage);

            lastSpawnTime = now;
        }
    }

    private void updateGameObjects() {
        Iterator<FallingObject> iterator = fallingObjects.iterator();
        while (iterator.hasNext()) {
            FallingObject obj = iterator.next();
            obj.updatePosition();

            // Jika objek keluar dari area game
            if (obj.getImageView().getLayoutY() > gameArea.getHeight()) {
                gameArea.getChildren().remove(obj.getImageView());
                iterator .remove();
               
                 // Kurangi nyawa hanya jika objek adalah buah
            if (!obj.getType().equals("bom")) {
                lives--;
                updateLivesDisplay();
                
                if (lives <= 0) {
                    gameOver();
                    return; // Hentikan update setelah game over
                }
            }
        }
    }
    }
    
    private void checkCollisions() {
        Iterator<FallingObject> iterator = fallingObjects.iterator();
        while (iterator.hasNext()) {
            FallingObject obj = iterator.next();

            if (obj.getImageView().getBoundsInParent().intersects(karakter.getBoundsInParent())) {
                gameArea.getChildren().remove(obj.getImageView());
                iterator.remove();

                if (obj.getType().equals("bom")) {
                    lives--;
                    updateLivesDisplay();
                    if (lives <= 0) {
                        gameOver();
                    }
                } else {
                    score++;
                    updateScoreDisplay();
                }
            }
        }
    }

    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + score);
    }

    private void updateLivesDisplay() {
        LivesBox.getChildren().clear();
        for (int i = 0; i < lives; i++) {
            ImageView heart = new ImageView(new Image("/projectgame/GUI/nyawa.png"));
            heart.setFitWidth(20);
            heart.setFitHeight(20);
            LivesBox.getChildren().add(heart);
        }
    }

    private void gameOver() {
        gameLoop.stop();
        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
        quitButton1.setVisible(true);

        for (FallingObject obj : fallingObjects) {
            gameArea.getChildren().remove(obj.getImageView());
        }

        fallingObjects.clear();
        gameArea.setFocusTraversable(false);
    }

    @FXML
    public void handleRestartButton(MouseEvent event) {
        resetGame(); // Panggil metode resetGame untuk memulai ulang permainan
    }

    private void resetCharacterPosition() {
        karakter.setLayoutX(gameArea.getPrefWidth() / 2 - karakter.getFitWidth() / 2);
        karakter.setLayoutY(gameArea.getPrefHeight() - karakter.getFitHeight() - 20);
    }

    private void pauseOrResumeGameObjects(boolean isPaused) {
        for (FallingObject obj : fallingObjects) {
            if (isPaused) {
                obj.stopFalling();
            } else {
                obj.startFalling();
            }
        }
    }

    @FXML
public void handlePauseButton() {
    isPaused = !isPaused;
    pauseButton.setText(isPaused ? "Resume" : "Pause");
    pauseOrResumeGameObjects(isPaused);

    // Pastikan gameArea tetap fokus saat resume
    if (!isPaused) {
        gameArea.setFocusTraversable(true); // Pastikan bisa menerima input
        gameArea.requestFocus();          // Fokuskan kembali
        gameArea.setOnKeyPressed(this::handleKeyPressed); // Pastikan key listener tetap aktif
    }
}


    @FXML
    public void handleQuitButton1() {
        System.exit(0);
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        
        switch (event.getCode()) {
            case LEFT:
                if (karakter.getLayoutX() > 0) {
                    karakter.setLayoutX(karakter.getLayoutX() - 10);
                }
                break;
            case RIGHT:
                double maxX = gameArea.getPrefWidth() - 100;
                if (karakter.getLayoutX() < maxX) {
                    karakter.setLayoutX(karakter.getLayoutX() + 10);
                }
                break;
        }
    }
}