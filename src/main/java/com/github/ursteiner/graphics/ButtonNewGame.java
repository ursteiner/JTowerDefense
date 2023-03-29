package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonNewGame extends AbstractButton {

    public ButtonNewGame(GameData gameData){
        super(gameData);
        this.position = new Point(360 * GameData.ZOOM, 45 * GameData.ZOOM);
        this.width = 105 * GameData.ZOOM;
        this.height = 15 * GameData.ZOOM;
    }
    @Override
    public void execute() {
        if(isButtonVisible()) {
            getGameData().setInMenu(false);
            getGameData().initGame();
            getGameData().setGameStarted(true);
        }
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "New Game", isMouseOver());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return false;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isInMenu();
    }
}
