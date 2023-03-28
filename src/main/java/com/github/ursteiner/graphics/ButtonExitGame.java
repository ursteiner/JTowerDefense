package com.github.ursteiner.graphics;

import com.github.ursteiner.model.GameData;

import java.awt.*;

public class ButtonExitGame extends AbstractButton {

    public ButtonExitGame(GameData gameData) {
        super(gameData);
        this.position = new Point(360 * GameData.ZOOM, 125 * GameData.ZOOM);
        this.width = 105 * GameData.ZOOM;
        this.height = 15 * GameData.ZOOM;
    }
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void paintButton(Graphics g) {
        if(isButtonVisible()) {
            TowerDefenseGraphics.paintMenuButton(g, getPosition(), "Exit", isMouseOver());
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return true;
    }

    @Override
    public boolean isButtonVisible() {
        return getGameData().isInMenu();
    }
}
