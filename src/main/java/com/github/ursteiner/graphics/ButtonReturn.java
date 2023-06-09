package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonReturn extends AbstractButton {

    public ButtonReturn(GameData gameData) {
        super(gameData);
        this.position = new Point(360 * GameData.ZOOM, 65 * GameData.ZOOM);
        this.width = 105 * GameData.ZOOM;
        this.height = 15 * GameData.ZOOM;
    }
    @Override
    public void execute() {
        if(isButtonVisible()){
            if (getGameData().isGameStarted() && !getGameData().isGameOver()) {
                getGameData().setPause(false);
                getGameData().setInMenu(false);
            }
        }
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "Return", isMouseOver());
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
