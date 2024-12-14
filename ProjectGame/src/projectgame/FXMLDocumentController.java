//Yang Mengerjakan : Muhammad Hafiz Adam





//Yang mengerjakan : Rizky Firmansyah
@FXML
    public void handleRestartButton(MouseEvent event) {
        playSound("bubleClickButton.mp3");
        resetGame();
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
        playSound("bubleClickButton.mp3");
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Resume" : "Pause");
        pauseOrResumeGameObjects(isPaused);

        if (!isPaused) {
            gameArea.setFocusTraversable(true);
            gameArea.requestFocus();
            gameArea.setOnKeyPressed(this::handleKeyPressed);
        }
    }

    @FXML
    public void handleQuitButton1() {
        stopGameBgm();
        playSound("bubleClickButton.mp3");
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
                double maxX = gameArea.getPrefWidth() - 50;
                if (karakter.getLayoutX() < maxX) {
                    karakter.setLayoutX(karakter.getLayoutX() + 10);
                }
                break;
        }
    }
}
